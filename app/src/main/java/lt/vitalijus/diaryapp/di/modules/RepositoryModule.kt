package lt.vitalijus.diaryapp.di.modules

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lt.vitalijus.diaryapp.core.Constants.SIGN_IN_REQUEST
import lt.vitalijus.diaryapp.core.Constants.SIGN_UP_REQUEST
import lt.vitalijus.diaryapp.data.repository.AuthRepositoryImpl
import lt.vitalijus.diaryapp.domain.repository.AuthRepository
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesAuthRepository(
        oneTapClient: SignInClient,
        @Named(SIGN_IN_REQUEST)
        signInRequest: BeginSignInRequest,
        @Named(SIGN_UP_REQUEST)
        signUpRequest: BeginSignInRequest,
    ): AuthRepository = AuthRepositoryImpl(
        oneTapClient = oneTapClient,
        signInRequest = signInRequest,
        signUpRequest = signUpRequest
    )
}
