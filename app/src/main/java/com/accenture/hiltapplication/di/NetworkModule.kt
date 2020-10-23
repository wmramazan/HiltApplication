package com.accenture.hiltapplication.di

import com.accenture.hiltapplication.network.NetworkService
import com.accenture.hiltapplication.util.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.Multibinds
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class NetworkModule {

    @Multibinds
    abstract fun interceptorsSet(): Set<Interceptor>

    companion object {
        @Singleton
        @Provides
        fun provideClient(interceptors: Set<@JvmSuppressWildcards Interceptor>) =
            OkHttpClient.Builder().apply {
                interceptors.forEach { addInterceptor(it) }
            }.build()

        @Singleton
        @Provides
        fun provideMoshi() = Moshi.Builder().build()

        @Singleton
        @Provides
        fun provideRetrofit(client: OkHttpClient, moshi: Moshi) = Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        @Singleton
        @Provides
        fun provideNetworkService(retrofit: Retrofit) = retrofit.create(NetworkService::class.java)
    }

}