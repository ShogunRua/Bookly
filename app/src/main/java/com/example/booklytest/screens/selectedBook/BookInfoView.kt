package com.example.booklytest.screens.selectedBook

import com.example.booklytest.pojo.SimilarBook

interface BookInfoView {
    fun showSimilar(similarBookList: List<SimilarBook>)
    fun showError(throwable: Throwable)
}