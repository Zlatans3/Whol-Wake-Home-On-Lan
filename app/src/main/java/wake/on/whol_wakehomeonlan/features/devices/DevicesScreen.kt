package wake.on.whol_wakehomeonlan.features.devices

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import wake.on.whol_wakehomeonlan.designcomponent.WholButton

@Composable
fun DevicesScreenRoute(
    devicesViewModel: DevicesViewModel = hiltViewModel(),
    backnavigationAction: () -> Unit,
) {


    DevicesScreen(
        navigationAction = {
            backnavigationAction()
        }
    )
}

@Composable
fun DevicesScreen(
    navigationAction: () -> Unit,
) {

    WholButton(
        actionButtonText = "back",
        action = {
            navigationAction()
        }
    )


}