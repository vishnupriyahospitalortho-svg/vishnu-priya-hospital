package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = MedicalTealPrimary,
    onPrimary = Color.White,
    primaryContainer = MedicalTealContainer,
    onPrimaryContainer = OnMedicalTealContainer,
    secondary = ClinicalNavy,
    onSecondary = Color.White,
    secondaryContainer = ClinicalNavyContainer,
    onSecondaryContainer = OnClinicalNavyContainer,
    tertiary = HealingAmber,
    onTertiary = Color.White,
    tertiaryContainer = HealingAmberContainer,
    background = BackgroundLight,
    onBackground = TextPrimaryLight,
    surface = Color.White,
    onSurface = TextPrimaryLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = TextSecondaryLight,
    error = EmergencyRed,
    errorContainer = EmergencyRedContainer,
    onError = OnEmergencyRed,
    outline = CardBorderLight
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4DD0E1),
    onPrimary = Color(0xFF00363A),
    primaryContainer = MedicalTealDark,
    onPrimaryContainer = MedicalTealContainer,
    secondary = Color(0xFF90CAF9),
    onSecondary = Color(0xFF0D3B66),
    secondaryContainer = ClinicalNavyDark,
    onSecondaryContainer = ClinicalNavyContainer,
    tertiary = Color(0xFFFFB74D),
    onTertiary = Color(0xFF4E2600),
    background = BackgroundDark,
    onBackground = Color(0xFFE2E8F0),
    surface = SurfaceDark,
    onSurface = Color(0xFFE2E8F0),
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = Color(0xFF94A3B8),
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF93000A),
    onError = Color(0xFF690005),
    outline = Color(0xFF334155)
)

@Composable
fun VishnuPriyaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Keep custom medical brand colors by default for strong healthcare identity
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Backward compatibility alias for tests
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    VishnuPriyaTheme(darkTheme = darkTheme, dynamicColor = dynamicColor, content = content)
}
