package com.aday.model.response

import com.aday.model.entity.BookInfo
import com.fasterxml.jackson.annotation.JsonProperty

data class SearchBookResponse (
    @JsonProperty("documents") var documents: ArrayList<BookInfo>
)