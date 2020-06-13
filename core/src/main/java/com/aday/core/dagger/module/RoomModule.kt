package com.aday.core.dagger.module

import android.app.Application
import androidx.room.Room
import com.aday.core.room.AppDataBase
import com.aday.core.room.BookListDao
import com.aday.core.room.BookListRepository
import com.aday.core.room.BookListRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule{
    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java,
            "database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBookListDao(appDatabase: AppDataBase): BookListDao {
        return appDatabase.bookListDao()
    }

    @Singleton
    @Provides
    fun bookListRepository(bookListDao: BookListDao): BookListRepository {
        return BookListRepositoryImpl(bookListDao)
    }

}