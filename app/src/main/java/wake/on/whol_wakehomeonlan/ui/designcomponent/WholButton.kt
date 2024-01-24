package wake.on.whol_wakehomeonlan.ui.designcomponent


import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonColors
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme.typography
import androidx.tv.material3.Text
import wake.on.whol_wakehomeonlan.ui.theme.Blue300
import wake.on.whol_wakehomeonlan.ui.theme.Brown300
import wake.on.whol_wakehomeonlan.ui.theme.CoolRed
import wake.on.whol_wakehomeonlan.ui.theme.HSpacer
import wake.on.whol_wakehomeonlan.ui.theme.LightYellow


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun WholButton(
    modifier: Modifier = Modifier,
    action: (() -> Unit)? = null,
    isEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(8.dp),
    @ColorRes actionButtonColor: Color = CoolRed,
    @ColorRes actionButtonDisabledColor: Color = CoolRed,
    actionButtonText: String,
    @DrawableRes buttonTrailingIcon: Int? = null,
    @ColorRes textColor: Color = LightYellow,
) {
    SingleTapButton(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        enabled = isEnabled,
        shape = shape,
        colors = ButtonDefaults.colors(
            containerColor = actionButtonColor,
            contentColor = actionButtonDisabledColor
        ),
        onClick = {
            action?.invoke()
        },
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = actionButtonText,
                style = typography.bodyMedium.copy(color = textColor),
            )
            if (buttonTrailingIcon != null) {
                Image(painter = painterResource(id = buttonTrailingIcon), contentDescription = "")
            }
        }
    }
}


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SingleTapButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(4.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: ButtonColors = ButtonDefaults.colors(
        containerColor = Blue300,
        contentColor = Color.White,
        focusedContainerColor = Blue300,
        focusedContentColor = Color.White,
    ),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {

    Button(
        modifier = modifier
            .clip(shape),
        enabled = enabled,
        interactionSource = interactionSource,
        colors = colors,
        contentPadding = contentPadding,
        onClick = {
                onClick()
        },
    ) {

        content()
    }
}