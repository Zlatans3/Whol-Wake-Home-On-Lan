package wake.on.whol_wakehomeonlan.features.sendwol

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val sendWholRoute = "send_whol"

fun NavController.navigateToSendWhol(navOptions: NavOptions? = null) {
    this.navigate(sendWholRoute, navOptions)
}

fun NavGraphBuilder.LoginStartScreenNavGraph(
    onAction: () -> Unit, // Should use?
) {
    composable(route = sendWholRoute) {
        SendWholScreenAppRoute(
            onNavigationTest = {
                onAction()
            }
        )
    }
}
