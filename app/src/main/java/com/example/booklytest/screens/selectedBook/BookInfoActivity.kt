package com.example.booklytest.screens.selectedBook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.booklytest.R
import com.example.booklytest.adapters.SimilarAdapter
import com.example.booklytest.pojo.SimilarBook
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_selected_book.*

class BookInfoActivity : AppCompatActivity(), BookInfoView {

    private lateinit var presenter: BookInfoPresenter
    private lateinit var adapter: SimilarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_book)
        if (!intent.hasExtra(EXTRA_BOOK_IMAGE) && !intent.hasExtra(EXTRA_BOOK_NAME) && !intent.hasExtra(
                EXTRA_BOOK_AUTHOR
            ) && !intent.hasExtra(EXTRA_BOOK_SCORE) && !intent.hasExtra(
                EXTRA_BOOK_AMOUNT
            ) && !intent.hasExtra(EXTRA_BOOK_PRICE)
        ) {
            finish()
            return
        }
        presenter = BookInfoPresenter(this)
        adapter = SimilarAdapter()
        vpSimilar.adapter = adapter
        val bookImage = intent.getStringExtra(EXTRA_BOOK_IMAGE)
        val bookName = intent.getStringExtra(EXTRA_BOOK_NAME)
        val bookAuthor = intent.getStringExtra(EXTRA_BOOK_AUTHOR)
        val bookScore = intent.getStringExtra(EXTRA_BOOK_SCORE)
        val bookAmount = intent.getStringExtra(EXTRA_BOOK_AMOUNT)
        val bookPrice = intent.getStringExtra(EXTRA_BOOK_PRICE)
        setTextToViews(bookImage, bookName, bookAuthor, bookScore, bookAmount, bookPrice)
        ivClose.setOnClickListener {
            finish()
        }
        presenter.loadSimilarBooks()
    }

    companion object {

        private const val EXTRA_BOOK_IMAGE = "image"
        private const val EXTRA_BOOK_NAME = "name"
        private const val EXTRA_BOOK_AUTHOR = "author"
        private const val EXTRA_BOOK_SCORE = "score"
        private const val EXTRA_BOOK_AMOUNT = "amount"
        private const val EXTRA_BOOK_PRICE = "price"

        fun newIntent(
            context: Context,
            bookImage: String,
            bookName: String,
            bookAuthor: String,
            bookScore: String,
            bookAmount: String,
            bookPrice: String
        ): Intent {
            val intent = Intent(context, BookInfoActivity::class.java)
            intent.putExtra(EXTRA_BOOK_IMAGE, bookImage)
            intent.putExtra(EXTRA_BOOK_NAME, bookName)
            intent.putExtra(EXTRA_BOOK_AUTHOR, bookAuthor)
            intent.putExtra(EXTRA_BOOK_SCORE, bookScore)
            intent.putExtra(EXTRA_BOOK_AMOUNT, bookAmount)
            intent.putExtra(EXTRA_BOOK_PRICE, bookPrice)
            return intent
        }
    }

    private fun setTextToViews(
        bookImage: String?,
        bookName: String?,
        bookAuthor: String?,
        bookScore: String?,
        bookAmount: String?,
        bookPrice: String?
    ) {
        Picasso.get().load(bookImage).into(ivBookLogoInfo)
        tvBookName.text = bookName
        tvAuthor.text = bookAuthor
        tvScore.text = bookScore
        tvAmount.text = bookAmount
        buttonBuy.text = bookPrice + getString(R.string.symbol)
    }

    override fun showSimilar(similarBookList: List<SimilarBook>) {
        adapter.similarBookList = similarBookList
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.disposeDisposable()
    }
}