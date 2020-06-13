package com.aday.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_list_table")
data class BookListEntity(@PrimaryKey(autoGenerate = true) var id: Int?,
                          @ColumnInfo(name = "date") var date: String,
                          @ColumnInfo(name = "book_image") var image: String,
                          @ColumnInfo(name = "book_name") var name: String,
                          @ColumnInfo(name = "rating") var rating: Int,
                          @ColumnInfo(name = "five_words") var fiveWords: String?,
                          @ColumnInfo(name = "detail_memo") var memo: String?)
