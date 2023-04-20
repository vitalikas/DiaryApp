package lt.vitalijus.diaryapp.presentation.screens.auth.components

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import lt.vitalijus.diaryapp.presentation.screens.auth.AuthViewModel

@Composable
fun AuthLauncher(
    authViewModel: AuthViewModel = hiltViewModel(),
    signInResult: BeginSignInResult
) {

    // Google Identity Services (GIS)
    val authLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    val signInCredentials =
                        authViewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                    val googleIdToken = signInCredentials.googleIdToken
                    val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                    Log.d("Auth", "googleCredentials = $googleCredentials")
                } catch (e: ApiException) {
                    e.printStackTrace()
                }
            }
        }
    )

    SideEffect {
        val intent =
            IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        authLauncher.launch(intent)
    }
}
