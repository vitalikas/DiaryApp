package lt.vitalijus.diaryapp.presentation.screens.auth

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import lt.vitalijus.diaryapp.presentation.screens.auth.components.AuthContent
import lt.vitalijus.diaryapp.presentation.screens.auth.components.AuthLauncher
import lt.vitalijus.diaryapp.presentation.screens.auth.components.OneTapSignIn

@Composable
fun AuthScreen(
    authViewModel: AuthViewModel = hiltViewModel()
) {

    AuthContent(
        onButtonClicked = {
            authViewModel.oneTapSignIn()
        }
    )

    OneTapSignIn(
        launch = { result ->
            AuthLauncher(signInResult = result)
        }
    )
}
