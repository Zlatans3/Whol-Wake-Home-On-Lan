package wake.on.whol_wakehomeonlan.features.devices

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val sendDevicesRoute = "whol_devices"

fun NavController.navigateToDevices(navOptions: NavOptions? = null) {
    this.navigate(sendDevicesRoute, navOptions)
}

fun NavGraphBuilder.devicesNavigationGraph(
    onAction: () -> Unit, // Should use?
) {
    composable(route = sendDevicesRoute) {
        DevicesScreenRoute {
            onAction()
        }
    }
}