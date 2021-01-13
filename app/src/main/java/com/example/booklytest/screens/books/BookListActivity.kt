package com.example.booklytest.screens.books

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.booklytest.R
import com.example.booklytest.adapters.BestSellerAdapter
import com.example.booklytest.adapters.CarouselAdapter
import com.example.booklytest.pojo.BestSeller
import com.example.booklytest.pojo.CarouselImage
import com.example.booklytest.screens.selectedBook.BookInfoActivity
import kotlinx.android.synthetic.main.activity_main.*

class BookListActivity : AppCompatActivity(), BookListView {

    private lateinit var presenter: BookListPresenter
    private lateinit var bestSellerAdapter: BestSellerAdapter
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = BookListPresenter(this)
        bestSellerAdapter = BestSellerAdapter()
        bestSellerAdapter.onBestSellerClickListener =
            object : BestSellerAdapter.OnBestSellerClickListener {
                override fun onBestSellerClick(bestSeller: BestSeller) {
                    val intent = BookInfoActivity.newIntent(
                        this@BookListActivity,
                        bestSeller.image.toString(),
                        bestSeller.title.toString(),
                        bestSeller.author.toString(),
                        bestSeller.rate?.score.toString(),
                        bestSeller.rate?.amount.toString(),
                        bestSeller.price.toString()
                    )
                    startActivity(intent)
                }
            }
        carouselAdapter = CarouselAdapter()
        vpCarousel.clipToPadding = false
        vpCarousel.clipChildren = false
        vpCarousel.offscreenPageLimit = 5
        vpCarousel.getChildAt(0).overScrollMode = (RecyclerView.OVER_SCROLL_NEVER)
        vpCarousel.setPageTransformer { page, position ->
            when {
                position <= -1 -> {
                    page.scaleY = 0.85f
                    page.scaleX = 0.85f
                }
                position >= 2 -> {
                    page.scaleX = 0.85f
                    page.scaleY = 0.85f
                    page.translationX = 0f
                }
                position < 1 -> {
                    page.scaleX = 1f
                    page.scaleY = 1f
                    page.translationX = 35f
                }
                else -> {
                    page.scaleX = 0.85f
                    page.scaleY = 0.85f
                    page.translationX = 35f
                }
            }
        }
        vpCarousel.adapter = carouselAdapter
        rvBestSeller.adapter = bestSellerAdapter
        presenter.loadBestSellerBooks()
        presenter.loadCarouseImages()
    }

    override fun showCarouselImages(carouselImagesList: List<CarouselImage>) {
        carouselAdapter.carouselImageList = carouselImagesList
    }

    override fun showBestSeller(bestSellerList: List<BestSeller>) {
        bestSellerAdapter.bestSellerList = bestSellerList
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.disposeDisposable()
    }
}