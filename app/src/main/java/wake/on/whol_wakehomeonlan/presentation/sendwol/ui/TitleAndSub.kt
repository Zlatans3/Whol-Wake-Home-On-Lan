package wake.on.whol_wakehomeonlan.presentation.sendwol.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import wake.on.whol_wakehomeonlan.ui.theme.LightYellow
import wake.on.whol_wakehomeonlan.ui.theme.VSpacer

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TitleAndSub() {
    Text(
        text = "Hello!",
        modifier = Modifier,
        style = MaterialTheme.typography.displayMedium.copy(
            color = LightYellow
        )
    )

    VSpacer(height = 16.dp)

    Text(
        text = "start by entering your ip address",
        modifier = Modifier,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = LightYellow
        )
    )
}

@Preview
@Composable
fun TitleAndSubPreview() {
    TitleAndSub()
}