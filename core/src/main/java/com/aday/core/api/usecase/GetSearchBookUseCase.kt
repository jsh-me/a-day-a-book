package com.aday.core.api.usecase

import com.aday.core.api.service.BookService
import com.aday.core.dagger.annotation.qualifier.api.ForUserAPI
import com.aday.model.response.SearchBookResponse
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class GetSearchBookUseCase @Inject constructor(@ForUserAPI retrofit: Retrofit) {
    private val mAuthService = retrofit.create(BookService::class.java)

    fun getSearchBook(query: String): Single<SearchBookResponse> {
        return mAuthService.getSearchBook(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}