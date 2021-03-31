package com.zcf.testapp.di

import com.zcf.testapp.web.TypicodeApi
import dagger.Binds
import dagger.Module
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [NetworkModule::class]
)
abstract class FakeNetworkModule {

    @Binds
    abstract fun bindTypicodeApiService(
        fakeTypicodeApi: FakeTypicodeApi
    ): TypicodeApi
}