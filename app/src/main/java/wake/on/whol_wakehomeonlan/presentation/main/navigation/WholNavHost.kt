package wake.on.whol_wakehomeonlan.presentation.main.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import wake.on.whol_wakehomeonlan.presentation.devices.devicesNavigationGraph
import wake.on.whol_wakehomeonlan.presentation.devices.navigateToDevices
import wake.on.whol_wakehomeonlan.presentation.sendwol.LoginStartScreenNavGraph
import wake.on.whol_wakehomeonlan.presentation.sendwol.SendWoLViewModel
import wake.on.whol_wakehomeonlan.presentation.sendwol.sendWholRoute

@Composable
fun WholNavhost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = sendWholRoute,
        enterTransition = { fadeIn(animationSpec = tween(200)) },
        exitTransition = { fadeOut(animationSpec = tween(200)) },
    ) {

        LoginStartScreenNavGraph(
            onAction = {
                navController.navigateToDevices()
            },
        )

        devicesNavigationGraph(
            onAction = {
                navController.popBackStack()
            },
        )
    }

}