package com.example.booklytest.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RatingOfBook(
    @SerializedName("score")
    @Expose
    var score: Double? = null,

    @SerializedName("amount")
    @Expose
    var amount: Int? = null
)