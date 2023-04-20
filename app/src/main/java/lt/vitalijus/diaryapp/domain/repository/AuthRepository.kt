package lt.vitalijus.diaryapp.domain.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import lt.vitalijus.diaryapp.domain.Response

typealias OneTapSignInResponse = Response<BeginSignInResult>
typealias SignInWithGoogleResponse = Response<Boolean>

interface AuthRepository {

    suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse
}
