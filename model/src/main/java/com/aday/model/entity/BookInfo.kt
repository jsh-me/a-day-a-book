package com.aday.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

data class BookInfo(
    @JsonProperty("authors") var authors: List<String>,
    @JsonProperty("contents") var contents: String,
    @JsonProperty("publisher") var publisher: String,
    @JsonProperty("title") var title: String,
    @JsonProperty("thumbnail") var thumbnail: String
)