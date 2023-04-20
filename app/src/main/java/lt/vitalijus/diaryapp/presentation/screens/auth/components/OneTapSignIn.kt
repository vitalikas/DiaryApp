package lt.vitalijus.diaryapp.presentation.screens.auth.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import lt.vitalijus.diaryapp.domain.Response
import lt.vitalijus.diaryapp.presentation.screens.auth.AuthViewModel

@Composable
fun OneTapSignIn(
    viewModel: AuthViewModel = hiltViewModel(),
    launch: @Composable (result: BeginSignInResult) -> Unit
) {
    when (val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is Response.Pending -> {

        }

        is Response.Loading -> {
//            ProgressIndicatorBlur()
        }

        is Response.Success -> oneTapSignInResponse.data?.let { result ->
            launch(result)
        }

        is Response.Failure -> oneTapSignInResponse.e.printStackTrace()
    }
}
