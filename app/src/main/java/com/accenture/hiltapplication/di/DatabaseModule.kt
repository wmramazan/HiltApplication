package com.accenture.hiltapplication.di

import android.content.Context
import androidx.room.Room
import com.accenture.hiltapplication.database.HiltDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HiltDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HiltDatabase::class.java,
            "HiltDatabase.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: HiltDatabase) = database.userDao

}