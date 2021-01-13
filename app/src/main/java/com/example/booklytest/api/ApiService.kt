package com.example.booklytest.api

import com.example.booklytest.pojo.BestSeller
import com.example.booklytest.pojo.CarouselImage
import com.example.booklytest.pojo.SimilarBook
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("carousel")
    fun getCarouselImages(): Single<List<CarouselImage>>

    @GET("best")
    fun getBestSeller(): Single<List<BestSeller>>

    @GET("similar")
    fun getSimilarBooks(): Single<List<SimilarBook>>
}