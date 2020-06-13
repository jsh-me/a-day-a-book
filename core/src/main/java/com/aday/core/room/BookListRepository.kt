package com.aday.core.room

import com.aday.model.room.BookListEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface BookListRepository {
    fun insert(bookListEntity: BookListEntity)

    fun update(bookListEntity: BookListEntity)

    fun delete(bookListEntity: BookListEntity)

    fun getBookListByDate(clickedDate: String): Observable<BookListEntity>
}