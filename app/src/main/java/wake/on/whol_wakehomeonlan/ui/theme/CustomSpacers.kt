package wake.on.whol_wakehomeonlan.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * used to define vertical spacing between composables
 * courtesy of [@phong-shape]
 */
@Composable
fun VSpacer(height: Dp, modifier: Modifier =Modifier){
    Spacer(
        Modifier.height(height).then(modifier)
    )
}

/**
 * Horizontal spacer that stretches horizontally
 */

@Composable
fun HSpacer(width:Dp,modifier:Modifier = Modifier) {
    Spacer(Modifier.width(width).then(modifier))
}