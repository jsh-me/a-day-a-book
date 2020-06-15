package com.aday.core.room

import androidx.room.*
import com.aday.model.room.BookListEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface BookListDao {
    @Insert
    fun insert(bookList: BookListEntity): Completable

    @Update
    fun update(bookList: BookListEntity): Completable

    @Delete
    fun delete(bookList: BookListEntity): Completable

    @Query("SELECT * FROM book_list_table WHERE date LIKE :clickedDate LIMIT 1")
    fun getBookListByDate(clickedDate: String): Observable<BookListEntity>
}