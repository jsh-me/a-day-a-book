package com.aday.model.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class BookInfo(
    @JsonProperty("authors") var authors: List<String>,
    @JsonProperty("contents") var contents: String,
    @JsonProperty("publisher") var publisher: String,
    @JsonProperty("title") var title: String,
    @JsonProperty("thumbnail") var thumbnail: String
)