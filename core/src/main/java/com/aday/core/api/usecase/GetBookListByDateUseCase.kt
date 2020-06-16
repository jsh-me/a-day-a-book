package com.aday.core.api.usecase

import com.aday.core.room.BookListRepository
import javax.inject.Inject

class GetBookListByDateUseCase @Inject constructor(private val bookListRepository: BookListRepository){
    fun getBookListByDate(date: String) = bookListRepository.getBookListByDate(date)
}