package com.accenture.hiltapplication.di

import com.accenture.hiltapplication.database.entity.UserEntity
import com.accenture.hiltapplication.repository.user.Repository
import com.accenture.hiltapplication.repository.user.UserRepository
import com.accenture.hiltapplication.source.user.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        @DataSourceModule.UserDataSourceRemote remoteUserDataSource: UserDataSource,
        @DataSourceModule.UserDataSourceLocal localUserDataSource: UserDataSource
    ): Repository<UserEntity> {
        return UserRepository(
            remoteUserDataSource, localUserDataSource
        )
    }

}