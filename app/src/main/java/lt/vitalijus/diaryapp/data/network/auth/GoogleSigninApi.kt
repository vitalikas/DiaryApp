package lt.vitalijus.diaryapp.data.network.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface GoogleSigninApi {

    fun getGoogleSignInClient(): GoogleSignInClient
    fun getGoogleSignInContract(): ActivityResultContract<Int, Task<GoogleSignInAccount>?>
    fun getLastSignedInAccount(): GoogleSignInAccount?

    class Base @Inject constructor(
        @ApplicationContext val context: Context
    ) : GoogleSigninApi {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(GoogleSignInConstants.CLIENT_ID)
            .requestEmail()
            .build()

        override fun getGoogleSignInClient(): GoogleSignInClient =
            GoogleSignIn.getClient(context, gso)

        override fun getGoogleSignInContract(): ActivityResultContract<Int, Task<GoogleSignInAccount>?> =
            object : ActivityResultContract<Int, Task<GoogleSignInAccount>?>() {
                override fun createIntent(context: Context, input: Int): Intent =
                    GoogleSignIn
                        .getClient(context, gso)
                        .signInIntent

                override fun parseResult(
                    resultCode: Int,
                    intent: Intent?
                ): Task<GoogleSignInAccount>? = when (resultCode) {
                    Activity.RESULT_OK -> GoogleSignIn.getSignedInAccountFromIntent(intent)
                    else -> null
                }
            }

        override fun getLastSignedInAccount(): GoogleSignInAccount? =
            GoogleSignIn.getLastSignedInAccount(context)
    }
}