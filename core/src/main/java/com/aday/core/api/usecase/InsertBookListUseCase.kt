package com.aday.core.api.usecase

import com.aday.core.dagger.annotation.qualifier.ForApplication
import com.aday.core.room.BookListRepository
import com.aday.model.room.BookListEntity
import javax.inject.Inject

class InsertBookListUseCase @Inject constructor(private val bookListRepository: BookListRepository){
    fun insert(bookListEntity: BookListEntity) = bookListRepository.insert(bookListEntity)
}