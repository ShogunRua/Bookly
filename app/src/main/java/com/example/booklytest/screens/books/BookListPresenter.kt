package com.example.booklytest.screens.books

import com.example.booklytest.api.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BookListPresenter(private val view: BookListView) {

    private val compositeDisposable = CompositeDisposable()

    fun loadCarouseImages() {
        val disposable = ApiFactory.apiService.getCarouselImages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showCarouselImages(it)
            }, {
                view.showError(it)
            })
        compositeDisposable.add(disposable)
    }

    fun loadBestSellerBooks() {
        val disposable = ApiFactory.apiService.getBestSeller()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showBestSeller(it)
            }, {
                view.showError(it)
            })
        compositeDisposable.add(disposable)
    }

    fun disposeDisposable() {
        compositeDisposable.dispose()
    }
}