@file:OptIn(ExperimentalTvMaterial3Api::class)

package wake.on.whol_wakehomeonlan.ui.theme

import androidx.compose.runtime.Composable
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme

@OptIn(ExperimentalTvMaterial3Api::class)
private val darkColorScheme @Composable get() = darkColorScheme(
    primary = Coral,
    onPrimary = LightYellow,
    primaryContainer = Red300,
    onPrimaryContainer = Pink300,
    secondary = Purple300,
    onSecondary = DeepPurple300,
    secondaryContainer = Indigo300,
    onSecondaryContainer = Blue300,
    tertiary = LightBlue300,
    onTertiary = Cyan300,
    tertiaryContainer = Teal300,
    onTertiaryContainer = Green300,
    background = LightGreen300,
    onBackground = Placeholder,
    surface = Yellow300,
    onSurface = ready,
    surfaceVariant = Orange300,
    onSurfaceVariant = DeepOrange300,
    error = Brown300,
    onError = CoolRed,
    errorContainer = BlueGray300,
    onErrorContainer = BlueGray300,
    border = BlueGray300,
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun WholTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = darkColorScheme,
        shapes = MaterialTheme.shapes,
        typography = Typography,
        content = content
    )
}