package io.openflux.android.core

import io.openflux.desktop.model.Profile
import io.openflux.desktop.model.TransportType
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

/** A profile in the form the gomobile core's Start calls take. */
internal object CoreSpecs {
    /** A resolved script carrier: the on-disk file, its pinned key, its id. */
    data class ScriptCarrier(val path: String, val pubkeyHex: String, val name: String)

    /**
     * The Session transport list for StartSession / StartSessionProxy /
     * StartSessionExit, named like the CLI (type, then type-2…) so they match
     * the exit's. An exit with a Direct transport listens on [directPort] instead of
     * dialing, like the desktop's exit .conf. [resolveScript] maps a carrier's
     * scriptId to its on-disk file and pinned key (null when missing).
     */
    fun session(
        profile: Profile,
        exit: Boolean,
        directPort: Int,
        resolveScript: (String) -> ScriptCarrier? = { null },
    ): String {
        val specs = profile.sessionSpecs().filterNot { exit && it.type == TransportType.DIRECT }
        val transports = buildJsonArray {
            for (spec in specs) add(spec(spec.name, spec.type, spec.value, spec.uid, spec.priority, spec.scriptId, resolveScript))
            // The exit listens for direct only when the profile has it.
            val direct = profile.carriers.firstOrNull { it.type == TransportType.DIRECT }
            if (exit && direct != null) {
                add(buildJsonObject {
                    put("name", "direct")
                    put("type", "direct")
                    put("url", "")
                    put("priority", direct.priority)
                    put("params", buildJsonObject { put("listen", "0.0.0.0:$directPort") })
                })
            }
        }
        if (profile.context.isBlank()) return transports.toString()
        return buildJsonObject {
            put("context", profile.context)
            put("transports", transports)
        }.toString()
    }

    private fun spec(
        name: String,
        type: TransportType,
        value: String,
        uid: String,
        priority: Int,
        scriptId: String,
        resolveScript: (String) -> ScriptCarrier?,
    ): JsonObject = buildJsonObject {
        val script = if (type == TransportType.SCRIPT) resolveScript(scriptId) else null
        // A script carrier keeps the core's generic "script" type; its id is only
        // a local pointer to the pinned file, so name it script / script-N too.
        put("name", if (type == TransportType.SCRIPT) name.replace(TransportType.SCRIPT.cliName, "script") else name)
        put("type", type.cliName)
        put("url", if (type == TransportType.DIRECT || type == TransportType.ONEME) "" else value)
        put("priority", priority)
        put("params", buildJsonObject {
            when (type) {
                TransportType.DIRECT -> put("dial", value)
                TransportType.ONEME -> {
                    put("token", value)
                    put("uid", uid)
                }
                TransportType.SCRIPT -> if (script != null) {
                    put("path", script.path)
                    put("pubkey", script.pubkeyHex)
                    put("name", script.name)
                }
                else -> Unit
            }
        })
    }

    /** Classic mode's arguments: (type, document URL, MAX token, MAX uid). */
    fun classic(profile: Profile): List<String> {
        val value = profile.value.trim()
        return if (profile.transport == TransportType.ONEME) {
            listOf(profile.transport.cliName, "", value, profile.uid.trim())
        } else {
            listOf(profile.transport.cliName, value, "", "")
        }
    }
}
