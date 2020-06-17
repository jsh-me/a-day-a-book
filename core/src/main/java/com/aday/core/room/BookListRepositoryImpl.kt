package com.aday.core.room

import android.annotation.SuppressLint
import com.aday.model.entity.BookInfo
import com.aday.model.room.BookListEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class BookListRepositoryImpl(private val bookListDao: BookListDao)
    : BookListRepository {
    @SuppressLint("CheckResult")
    override fun insert(bookListEntity: BookListEntity) {
        bookListDao.insert(bookListEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.e("insert success")
            },{
                Timber.e(it.localizedMessage)
            })
    }

    @SuppressLint("CheckResult")
    override fun update(bookListEntity: BookListEntity) {
        bookListDao.update(bookListEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.e("update success")
            },{
                Timber.e(it.localizedMessage)
            })
    }

    @SuppressLint("CheckResult")
    override fun delete(bookListEntity: BookListEntity) {
        bookListDao.delete(bookListEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.e("delete success")
            },{
                Timber.e(it.localizedMessage)
            })
    }

    override fun loadAllDate(): Observable<List<String>> = bookListDao.loadAllDates()


    override fun getBookListByDate(clickedDate: String): Observable<BookListEntity>
            = bookListDao.getBookListByDate(clickedDate)

    override fun getAllData(): Observable<List<BookListEntity>> = bookListDao.getAllData()
}