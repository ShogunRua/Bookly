package com.example.booklytest.screens.selectedBook

import com.example.booklytest.api.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BookInfoPresenter(private val view: BookInfoView) {

    private val compositeDisposable = CompositeDisposable()

    fun loadSimilarBooks() {
        val disposable = ApiFactory.apiService.getSimilarBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showSimilar(it)
            }, {
                view.showError(it)
            })
        compositeDisposable.add(disposable)
    }

    fun disposeDisposable() {
        compositeDisposable.dispose()
    }
}