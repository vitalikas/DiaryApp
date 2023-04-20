package lt.vitalijus.diaryapp.presentation.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lt.vitalijus.diaryapp.domain.Response
import lt.vitalijus.diaryapp.domain.repository.AuthRepository
import lt.vitalijus.diaryapp.domain.repository.OneTapSignInResponse
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val oneTapClient: SignInClient,
    private val repo: AuthRepository
) : ViewModel() {

    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Response.Pending)
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        oneTapSignInResponse = Response.Loading
        oneTapSignInResponse = repo.oneTapSignInWithGoogle()
    }
}
