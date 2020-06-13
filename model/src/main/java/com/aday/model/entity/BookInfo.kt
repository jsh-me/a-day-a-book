package com.aday.model.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class BookInfo(
    @JsonProperty("title") var title: String,
    //@JsonProperty("link") var link: String,
    @JsonProperty("author") var author: String,
    //@JsonProperty("price") var price: String,
    //@JsonProperty("discount") var discount: String,
    @JsonProperty("publisher") var publisher: String,
    //@JsonProperty("pubdate") var pubdate: String,
    //@JsonProperty("description") var discription: String
    @JsonProperty("image") var image: String

)