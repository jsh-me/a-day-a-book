package com.aday.core.api.usecase

import com.aday.core.room.BookListRepository
import javax.inject.Inject

class LoadAllDateUseCase @Inject constructor(private val bookListRepository: BookListRepository){
    fun allLoadDates() = bookListRepository.loadAllDate()
}