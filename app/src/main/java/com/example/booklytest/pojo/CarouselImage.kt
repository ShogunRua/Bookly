package com.example.booklytest.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CarouselImage(
    @SerializedName("id")
    @Expose
    private var id: Int? = null,

    @SerializedName("image")
    @Expose
    var image: String? = null

)