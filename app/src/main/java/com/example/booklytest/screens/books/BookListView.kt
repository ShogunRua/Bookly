package com.example.booklytest.screens.books

import com.example.booklytest.pojo.BestSeller
import com.example.booklytest.pojo.CarouselImage

interface BookListView {
    fun showBestSeller(bestSellerList: List<BestSeller>)
    fun showCarouselImages(carouselImagesList: List<CarouselImage>)
    fun showError(throwable: Throwable)
}