package lt.vitalijus.diaryapp.data.repository

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.tasks.await
import lt.vitalijus.diaryapp.core.Constants.SIGN_IN_REQUEST
import lt.vitalijus.diaryapp.core.Constants.SIGN_UP_REQUEST
import lt.vitalijus.diaryapp.domain.Response
import lt.vitalijus.diaryapp.domain.repository.AuthRepository
import lt.vitalijus.diaryapp.domain.repository.OneTapSignInResponse
import javax.inject.Inject
import javax.inject.Named

class AuthRepositoryImpl @Inject constructor(
    private val oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private val signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private val signUpRequest: BeginSignInRequest
) : AuthRepository {

    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse =
        try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Response.Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Response.Success(signUpResult)
            } catch (e: Exception) {
                e.printStackTrace()
                Response.Failure(e)
            }
        }
}
