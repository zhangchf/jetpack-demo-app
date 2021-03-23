package com.zcf.testapp.di

import com.zcf.testapp.web.TypicodeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    fun providesTypicodeApiService(): TypicodeApi {

        return Retrofit.Builder()
            .baseUrl(TypicodeApi.URL_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TypicodeApi::class.java)
    }
}