package com.anle.democache.net

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("ID")
    var iD: Long,

    @SerializedName("Title")
    var title: String,

    @SerializedName("Description")
    var description: String,

    @SerializedName("PageCount")
    var pageCount: Int,

    @SerializedName("Excerpt")
    var excerpt: String,

    @SerializedName("PublishDate")
    var publishDate: String
)
