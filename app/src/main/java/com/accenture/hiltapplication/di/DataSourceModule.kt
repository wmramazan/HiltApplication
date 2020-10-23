package com.accenture.hiltapplication.di

import com.accenture.hiltapplication.database.dao.UserDao
import com.accenture.hiltapplication.network.NetworkService
import com.accenture.hiltapplication.source.user.LocalUserDataSource
import com.accenture.hiltapplication.source.user.RemoteUserDataSource
import com.accenture.hiltapplication.source.user.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class UserDataSourceRemote

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class UserDataSourceLocal

    @Singleton
    @UserDataSourceRemote
    @Provides
    fun provideRemoteUserDataSource(
        networkService: NetworkService,
        ioDispatcher: CoroutineDispatcher
    ): UserDataSource {
        return RemoteUserDataSource(networkService, ioDispatcher)
    }

    @Singleton
    @UserDataSourceLocal
    @Provides
    fun provideLocalUserDataSource(
        userDao: UserDao,
        ioDispatcher: CoroutineDispatcher
    ): UserDataSource {
        return LocalUserDataSource(
            userDao, ioDispatcher
        )
    }

}