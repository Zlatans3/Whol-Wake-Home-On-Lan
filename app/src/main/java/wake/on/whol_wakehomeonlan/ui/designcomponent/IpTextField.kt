package wake.on.whol_wakehomeonlan.ui.designcomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import wake.on.whol_wakehomeonlan.R
import wake.on.whol_wakehomeonlan.ui.theme.CoolRed
import wake.on.whol_wakehomeonlan.ui.theme.LightYellow
import wake.on.whol_wakehomeonlan.ui.theme.Placeholder

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun WoLTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean? = null,
    isEnabled: Boolean = true,
    placeholderText: String = "Enter text here",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,

    ) {

    val painter =
        when (isValid) {
            true -> painterResource(id = R.drawable.baseline_check_circle_24)
            false -> painterResource(
                id = R.drawable.baseline_cross_circle_24,
            )

            null -> painterResource(id = R.drawable.baseline_circle_24)
        }
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = isEnabled,
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = LightYellow),
        decorationBox = { innerTextField ->
            Row {
                Box(
                    modifier = modifier
//                        .shadow(4.dp, RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
//                        .border(
//                            1.dp,
//                            CoolRed,
//                            RoundedCornerShape(12.dp)
//                        )
                        .background(CoolRed)
                        .weight(1.3f)
                        .padding(16.dp)
                ) {
                    if (value.isEmpty()) {
                        Text(placeholderText, color = Placeholder)
                    }
                    innerTextField()
                }


                Image(
                    modifier = Modifier.weight(0.2f).align(Alignment.CenterVertically),
                    painter = painter,
                    contentDescription = "",
                    alignment = Alignment.CenterEnd,
                )
            }
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,

        )


}