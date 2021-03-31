package com.zcf.testapp.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.zcf.testapp.db.AppDatabase
import com.zcf.testapp.db.PostDao
import com.zcf.testapp.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class FakeDatabaseModule {

    /**
     * We don't actually need this. The real DB works just fine.
     */
    @Provides
    @Singleton
    fun provideAppDatabase() : AppDatabase {
        val context = ApplicationProvider.getApplicationContext<Context>()
        return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun providePostDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }

}