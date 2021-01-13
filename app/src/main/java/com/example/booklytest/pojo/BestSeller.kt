package com.example.booklytest.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class BestSeller (
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("author")
    @Expose
    var author: String? = null,

    @SerializedName("price")
    @Expose
    var price: Double? = null,

    @SerializedName("image")
    @Expose
    var image: String? = null,

    @SerializedName("rate")
    @Expose
    var rate: RatingOfBook? = null
)