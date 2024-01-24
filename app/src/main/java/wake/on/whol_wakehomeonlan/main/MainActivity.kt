@file:OptIn(ExperimentalTvMaterial3Api::class)

package wake.on.whol_wakehomeonlan.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import wake.on.whol_wakehomeonlan.features.sendwol.SendWholScreenAppRoute
import wake.on.whol_wakehomeonlan.main.navigation.WholNavhost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navHostController = rememberNavController()

            Box(modifier = Modifier.fillMaxSize()) {
                WholNavhost(
                    navController = navHostController,

                )
            }
        }
    }
}
