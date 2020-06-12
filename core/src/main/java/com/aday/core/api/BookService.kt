package com.aday.core.api

import com.aday.model.response.SearchBookResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("search/book.json")
    fun getSearchBook(@Query("query") query: String) : Single<SearchBookResponse>
}