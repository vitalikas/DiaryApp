package lt.vitalijus.diaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import lt.vitalijus.diaryapp.navigation.SetupNavGraph
import lt.vitalijus.diaryapp.presentation.screens.splash.SplashViewModel
import lt.vitalijus.diaryapp.ui.theme.DiaryAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            // Show splash with combination of predicate
            setKeepOnScreenCondition { viewModel.isLoading.value }
        }

        setContent {
            DiaryAppTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController
                )
            }
        }
    }
}
