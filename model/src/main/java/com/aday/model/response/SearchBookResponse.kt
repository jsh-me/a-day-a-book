package com.aday.model.response

import com.aday.model.entity.BookInfo
import com.fasterxml.jackson.annotation.JsonProperty

data class SearchBookResponse (
    @JsonProperty("total") var total: Int,
    @JsonProperty("start") var start: Int,
    @JsonProperty("display") var display: Int,
    @JsonProperty("items") var items: ArrayList<BookInfo>
)