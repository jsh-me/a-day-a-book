package com.aday.core.api.usecase

import com.aday.core.room.BookListRepository
import javax.inject.Inject

class GetAllDataUseCase @Inject constructor(private val bookListRepository: BookListRepository){
    fun getAllData() = bookListRepository.getAllData()
}