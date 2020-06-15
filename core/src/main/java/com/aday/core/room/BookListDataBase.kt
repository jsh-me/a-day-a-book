package com.aday.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aday.model.room.BookListEntity

@Database(entities = [BookListEntity::class], version = 2)
abstract class AppDataBase: RoomDatabase(){
    abstract fun bookListDao(): BookListDao
}