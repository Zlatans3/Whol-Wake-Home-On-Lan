package wake.on.whol_wakehomeonlan.features.sendwol.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import wake.on.whol_wakehomeonlan.designcomponent.WoLTextField
import wake.on.whol_wakehomeonlan.ui.theme.VSpacer

@Composable
fun SendWoLTextFields(
    modifier: Modifier = Modifier,
    ipAddressValue: String,
    macAddressValue: String,
    portNumberValue: String,
    onIpTextFieldChanged: (String) -> Unit,
    onMacTextFieldChanged: (String) -> Unit,
    onPortTextFieldChanged: (String) -> Unit,
    onWakeOnLanClicked: () -> Unit,
    isIpValid: Boolean? = null,
    isMacValid: Boolean? = null,
) {
    val focusManager = LocalFocusManager.current
    Column(modifier = modifier) {
        WoLTextField(
            value = ipAddressValue,
            onValueChange = { onIpTextFieldChanged(it) },
            placeholderText = "Enter your ip address",
            isValid = isIpValid,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        VSpacer(height = 16.dp)

        WoLTextField(
            value = macAddressValue,
            onValueChange = { onMacTextFieldChanged(it) },
            placeholderText = "Enter your mac address",
            isValid = isMacValid,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        VSpacer(height = 16.dp)

        WoLTextField(
            modifier = Modifier.padding(end = 100.dp),
            value = portNumberValue,
            onValueChange = { onPortTextFieldChanged(it) },
            isValid = portNumberValue.isNotEmpty(),
            placeholderText = "port number",
            keyboardActions = KeyboardActions(
                onGo = {
                    onWakeOnLanClicked()
                }
            ),
        )
    }
}