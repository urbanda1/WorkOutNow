package cz.uhk.workOutNow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import cz.uhk.workOutNow.ui.AppContainer
import cz.uhk.workOutNow.ui.theme.UMTETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UMTETheme {
                AppContainer(
                    controller = rememberNavController()
                )
            }
        }
    }
}
