package io.openflux.desktop.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * The OpenFlux palette, taken from the Android app: the same accent, the
 * same neutral surfaces in light and dark, and the same status colors.
 */
@Immutable
data class AppColors(
    val isDark: Boolean,
    val background: Color,
    val surface: Color,
    /** Hover and selected rows; a tonal step above [surface]. */
    val surfaceTonal: Color,
    val sidebar: Color,
    val border: Color,
    val text: Color,
    val textSecondary: Color,
    val textHint: Color,
    val accent: Color,
    val accentPressed: Color,
    val accentSoft: Color,
    val onAccent: Color,
    val success: Color,
    val warning: Color,
    val warningPressed: Color,
    val danger: Color,
    val dangerPressed: Color,
    val dangerSoft: Color,
    val logText: Color,
    val scrim: Color,
)

val LightColors = AppColors(
    isDark = false,
    background = Color(0xFFF8FAFC),
    surface = Color(0xFFFFFFFF),
    surfaceTonal = Color(0xFFF0F9FF),
    sidebar = Color(0xFFF1F5F9),
    border = Color(0xFFE2E8F0),
    text = Color(0xFF0F172A),
    textSecondary = Color(0xFF475569),
    textHint = Color(0xFF94A3B8),
    accent = Color(0xFF0284C7),
    accentPressed = Color(0xFF0369A1),
    accentSoft = Color(0x180284C7),
    onAccent = Color.White,
    success = Color(0xFF059669),
    warning = Color(0xFFD97706),
    warningPressed = Color(0xFFB45309),
    danger = Color(0xFFE11D48),
    dangerPressed = Color(0xFFBE123C),
    dangerSoft = Color(0x14E11D48),
    logText = Color(0xFF1E293B),
    scrim = Color(0x66000000),
)

val DarkColors = AppColors(
    isDark = true,
    background = Color(0xFF090D16),
    surface = Color(0xFF111726),
    surfaceTonal = Color(0xFF192238),
    sidebar = Color(0xFF0D121F),
    border = Color(0xFF1E283D),
    text = Color(0xFFF8FAFC),
    textSecondary = Color(0xFF94A3B8),
    textHint = Color(0xFF64748B),
    accent = Color(0xFF00D2FF),
    accentPressed = Color(0xFF009FD6),
    accentSoft = Color(0x2400D2FF),
    onAccent = Color(0xFF04121F),
    success = Color(0xFF10B981),
    warning = Color(0xFFF59E0B),
    warningPressed = Color(0xFFD97706),
    danger = Color(0xFFF43F5E),
    dangerPressed = Color(0xFFE11D48),
    dangerSoft = Color(0x22F43F5E),
    logText = Color(0xFFE2E8F0),
    scrim = Color(0xAA000000),
)

/** Type scale: Android's sizes, one step denser for a desktop window. */
@Immutable
data class AppTypography(
    val pageTitle: TextStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, lineHeight = 30.sp),
    val sectionTitle: TextStyle = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.SemiBold, lineHeight = 22.sp),
    val label: TextStyle = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.9.sp),
    val body: TextStyle = TextStyle(fontSize = 14.sp, lineHeight = 20.sp),
    val bodyStrong: TextStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, lineHeight = 20.sp),
    val bodySmall: TextStyle = TextStyle(fontSize = 12.sp, lineHeight = 17.sp),
    val caption: TextStyle = TextStyle(fontSize = 11.sp, lineHeight = 15.sp),
    val metric: TextStyle = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold),
    val mono: TextStyle = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.Monospace, lineHeight = 18.sp),
)

@Immutable
data class AppSpacing(
    val xxs: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val s: Dp = 8.dp,
    val m: Dp = 12.dp,
    val l: Dp = 16.dp,
    val xl: Dp = 20.dp,
    val xxl: Dp = 24.dp,
    val xxxl: Dp = 32.dp,
    /** Side margin of a page, as on Android. */
    val page: Dp = 24.dp,
)

@Immutable
data class AppShapes(
    val small: RoundedCornerShape = RoundedCornerShape(10.dp),
    val field: RoundedCornerShape = RoundedCornerShape(12.dp),
    val card: RoundedCornerShape = RoundedCornerShape(16.dp),
    val button: RoundedCornerShape = RoundedCornerShape(12.dp),
    val dialog: RoundedCornerShape = RoundedCornerShape(20.dp),
    val pill: RoundedCornerShape = RoundedCornerShape(50),
)

@Immutable
data class AppDimens(
    val sidebarWidth: Dp = 232.dp,
    val sidebarCompactWidth: Dp = 72.dp,
    val bottomBarHeight: Dp = 64.dp,
    val buttonHeight: Dp = 40.dp,
    val fieldHeight: Dp = 44.dp,
    val iconButton: Dp = 36.dp,
    val icon: Dp = 20.dp,
    val iconBubble: Dp = 36.dp,
    val listRow: Dp = 60.dp,
    val connectButton: Dp = 150.dp,
    val connectRingOuter: Dp = 214.dp,
    val connectRingInner: Dp = 182.dp,
    val masterPaneWidth: Dp = 340.dp,
    val settingsNavWidth: Dp = 240.dp,
    val dialogWidth: Dp = 480.dp,
)

private val LocalColors = staticCompositionLocalOf { LightColors }
private val LocalTypography = staticCompositionLocalOf { AppTypography() }
private val LocalSpacing = staticCompositionLocalOf { AppSpacing() }
private val LocalShapes = staticCompositionLocalOf { AppShapes() }
private val LocalDimens = staticCompositionLocalOf { AppDimens() }

/** Access point for the design system: `AppTheme.colors.accent`. */
object AppTheme {
    val colors: AppColors @Composable get() = LocalColors.current
    val typography: AppTypography @Composable get() = LocalTypography.current
    val spacing: AppSpacing @Composable get() = LocalSpacing.current
    val shapes: AppShapes @Composable get() = LocalShapes.current
    val dimens: AppDimens @Composable get() = LocalDimens.current
}

/** Android's own sizes: the desktop scale is one step denser for a window. */
private val TouchTypography = AppTypography(
    pageTitle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, lineHeight = 30.sp),
    sectionTitle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, lineHeight = 24.sp),
    label = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.9.sp),
    body = TextStyle(fontSize = 15.sp, lineHeight = 21.sp),
    bodyStrong = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold, lineHeight = 21.sp),
    bodySmall = TextStyle(fontSize = 13.sp, lineHeight = 18.sp),
    caption = TextStyle(fontSize = 12.sp, lineHeight = 16.sp),
    metric = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold),
    mono = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.Monospace, lineHeight = 18.sp),
)

/** Finger-sized targets and a phone's page margin. */
private val TouchDimens = AppDimens(buttonHeight = 48.dp, fieldHeight = 52.dp, iconButton = 44.dp, listRow = 68.dp)
private val TouchSpacing = AppSpacing(page = 16.dp)

@Composable
fun OpenFluxTheme(dark: Boolean, touch: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (dark) DarkColors else LightColors
    val material = if (dark) {
        darkColorScheme(
            primary = colors.accent, onPrimary = colors.onAccent, background = colors.background,
            onBackground = colors.text, surface = colors.surface, onSurface = colors.text,
            surfaceVariant = colors.surfaceTonal, onSurfaceVariant = colors.textSecondary,
            outline = colors.border, error = colors.danger,
        )
    } else {
        lightColorScheme(
            primary = colors.accent, onPrimary = colors.onAccent, background = colors.background,
            onBackground = colors.text, surface = colors.surface, onSurface = colors.text,
            surfaceVariant = colors.surfaceTonal, onSurfaceVariant = colors.textSecondary,
            outline = colors.border, error = colors.danger,
        )
    }
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides if (touch) TouchTypography else AppTypography(),
        LocalSpacing provides if (touch) TouchSpacing else AppSpacing(),
        LocalShapes provides AppShapes(),
        LocalDimens provides if (touch) TouchDimens else AppDimens(),
    ) {
        MaterialTheme(colorScheme = material, content = content)
    }
}
