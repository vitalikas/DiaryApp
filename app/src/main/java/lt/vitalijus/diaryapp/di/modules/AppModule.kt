package lt.vitalijus.diaryapp.di.modules

import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lt.vitalijus.diaryapp.core.Constants.SERVER_CLIENT_ID
import lt.vitalijus.diaryapp.core.Constants.SIGN_IN_REQUEST
import lt.vitalijus.diaryapp.core.Constants.SIGN_UP_REQUEST
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesOneTapClient(
        @ApplicationContext context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Named(SIGN_IN_REQUEST)
    fun providesSignInRequest() = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(SERVER_CLIENT_ID) // web client id from Firebase Console
                .setFilterByAuthorizedAccounts(false) // show only the accounts previously used to sign in
                .build()
        )
        .setAutoSelectEnabled(true) // automatically sign in when exactly one credential is retrieved
        .build()

    @Provides
    @Named(SIGN_UP_REQUEST)
    fun providesSignUpRequest() = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(SERVER_CLIENT_ID) // web client id from Firebase Console
                .setFilterByAuthorizedAccounts(false) // show all available accounts
                .build()
        )
        .build()
}
