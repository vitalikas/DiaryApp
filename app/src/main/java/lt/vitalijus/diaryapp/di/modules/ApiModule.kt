package lt.vitalijus.diaryapp.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lt.vitalijus.diaryapp.data.network.auth.GoogleSigninApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Binds
    @Singleton
    abstract fun providesGoogleApiService(impl: GoogleSigninApi.Base): GoogleSigninApi
}
