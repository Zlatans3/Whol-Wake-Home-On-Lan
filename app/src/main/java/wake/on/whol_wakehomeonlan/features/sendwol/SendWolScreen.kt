package wake.on.whol_wakehomeonlan.features.sendwol

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import wake.on.whol_wakehomeonlan.main.MainActivityActions
import wake.on.whol_wakehomeonlan.R
import wake.on.whol_wakehomeonlan.designcomponent.WholButton
import wake.on.whol_wakehomeonlan.features.sendwol.ui.SendWoLTextFields
import wake.on.whol_wakehomeonlan.features.sendwol.ui.TitleAndSub
import wake.on.whol_wakehomeonlan.ui.theme.BlueGray300
import wake.on.whol_wakehomeonlan.ui.theme.LightYellow
import wake.on.whol_wakehomeonlan.ui.theme.VSpacer

@Composable
fun SendWholScreenAppRoute(
    viewModel: SendWoLViewModel = hiltViewModel(),
    onNavigationTest: () -> Unit
) {
    val nameTextFieldQuery by viewModel.ipAddressTextFieldQuery.collectAsStateWithLifecycle()
    val emailTextFieldQuery by viewModel.macAddressTextFieldQuery.collectAsStateWithLifecycle()
    val passWordTextFieldQuery by viewModel.portTextFieldQuery.collectAsStateWithLifecycle()

    val IpAddressvalue = nameTextFieldQuery
    val macAddressValue = emailTextFieldQuery
    val portNumberValue = passWordTextFieldQuery

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SendWoLScreen(
        modifier = Modifier,
        ipAddressValue = IpAddressvalue,
        macAddressValue = macAddressValue,
        portNumberValue = portNumberValue,
        onIpTextFieldChanged = { viewModel.onAction(MainActivityActions.OnIpTextFieldChanged(it)) },
        onMacTextFieldChanged = {
            viewModel.onAction(
                MainActivityActions.OnMacAddressTextFieldChanged(
                    it
                )
            )
        },
        onPortTextFieldChanged = { viewModel.onAction(MainActivityActions.OnPortTextFieldChanged(it)) },
        onWakeOnLanClicked = { viewModel.onAction(MainActivityActions.OnWakeOnLanClicked) },
        isValid = uiState.IsMacValid,
        onDeviceSaved = { viewModel.onAction(MainActivityActions.OnDeviceSaved) },
        isDeviceSaved = uiState.deviceSaved,
        onNavigationTest = { onNavigationTest() },
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SendWoLScreen(
    modifier: Modifier = Modifier,
    ipAddressValue: String,
    macAddressValue: String,
    portNumberValue: String,
    onIpTextFieldChanged: (String) -> Unit = {},
    onMacTextFieldChanged: (String) -> Unit = {},
    onPortTextFieldChanged: (String) -> Unit = {},
    isValid: Boolean? = null,
    onWakeOnLanClicked: () -> Unit = {},
    onDeviceSaved: () -> Unit = {},
    isDeviceSaved: Boolean = false,
    onNavigationTest: () -> Unit = {},
) {
    val isKeyboardOpen by keyboardAsState()

    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = isKeyboardOpen) {
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(BlueGray300)
            .padding(horizontal = 50.dp, vertical = 50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
            /**
             * Left side of the screen
             */
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .padding(top = 16.dp)
            ) {
                TitleAndSub()

                VSpacer(height = 40.dp)

                SendWoLTextFields(
                    modifier = Modifier,
                    ipAddressValue = ipAddressValue,
                    macAddressValue = macAddressValue,
                    portNumberValue = portNumberValue,
                    onIpTextFieldChanged = { onIpTextFieldChanged(it) },
                    onMacTextFieldChanged = { onMacTextFieldChanged(it) },
                    onPortTextFieldChanged = { onPortTextFieldChanged(it) },
                    onWakeOnLanClicked = { onWakeOnLanClicked() },
                    isMacValid = isValid,
                )

                VSpacer(height = 30.dp)

                Row {
                    WholButton(
                        modifier = Modifier.weight(0.8f),
                        actionButtonText = "Send WoL!",
                        action = { onWakeOnLanClicked() },
                        isEnabled = true,
                    )

                    Spacer(modifier = Modifier.weight(0.1f))

                    WholButton(
                        modifier = Modifier
                            .weight(0.2f),
                        buttonTrailingIcon = if (isDeviceSaved) R.drawable.baseline_bookmark_24 else R.drawable.baseline_bookmark_border_24,
                        actionButtonText = "",
                        action = {
                            onDeviceSaved()
                        },
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.2f))

            /**
             * Right side of the screen
             */
            Column(
                modifier = Modifier
                    .weight(0.4f)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = ""
                )

                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleLarge.copy(color = LightYellow)
                )
                VSpacer(height = 16.dp)

                Text(
                    text = stringResource(id = R.string.side_bar_title),
                    style = MaterialTheme.typography.bodyLarge.copy(color = LightYellow)
                )

                VSpacer(height = 16.dp)

                Text(
                    text = stringResource(id = R.string.side_bar_under_sub),
                    style = MaterialTheme.typography.bodyMedium.copy(color = LightYellow)
                )

                VSpacer(height = 16.dp)

                Text(
                    text = stringResource(id = R.string.side_bar_description_title),
                    style = MaterialTheme.typography.bodyLarge.copy(color = LightYellow)
                )

                VSpacer(height = 16.dp)

                Text(
                    text = stringResource(id = R.string.side_bar_description),
                    style = MaterialTheme.typography.bodyMedium.copy(color = LightYellow)
                )

                WholButton(actionButtonText = "NavigationTest", action = { onNavigationTest() })
            }
        }
    }
}

/**
 * We use this to get the keyboard state since it is not possible to get the keyboard state in compose yet.
 */
@Composable
private fun keyboardAsState(): State<Boolean> {
    val ime = WindowInsets.ime
    val density = LocalDensity.current
    return remember(ime, density) {
        derivedStateOf {
            ime.getBottom(density) > 0
        }
    }
}