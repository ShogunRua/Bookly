package com.example.booklytest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booklytest.R
import com.example.booklytest.pojo.BestSeller
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_best_seller.view.*

class BestSellerAdapter : RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {

    var bestSellerList: List<BestSeller> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onBestSellerClickListener: OnBestSellerClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestSellerAdapter.BestSellerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_best_seller, parent, false)
        return BestSellerViewHolder(view)
    }

    override fun getItemCount() = bestSellerList.size

    override fun onBindViewHolder(holder: BestSellerAdapter.BestSellerViewHolder, position: Int) {
        val bestSeller = bestSellerList[position]
        with(holder) {
            tvBookTitle.text = bestSeller.title
            tvBookAuthor.text = bestSeller.author
            tvBookPrice.text = bestSeller.price.toString()
            tvBookScore.text = bestSeller.rate?.score.toString()
            tvBookAmount.text = bestSeller.rate?.amount.toString()
            Picasso.get().load(bestSeller.image).into(ivBook)
            itemView.setOnClickListener {
                onBestSellerClickListener?.onBestSellerClick(bestSeller)
            }
        }
    }

    inner class BestSellerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivBook: ImageView = itemView.ivBook
        val tvBookTitle: TextView = itemView.tvBookTitle
        val tvBookAuthor: TextView = itemView.tvBookAuthor
        val tvBookPrice: TextView = itemView.tvBookPrice
        val tvBookScore: TextView = itemView.tvBookScore
        val tvBookAmount: TextView = itemView.tvBookAmount
    }

    interface OnBestSellerClickListener {
        fun onBestSellerClick(bestSeller: BestSeller)
    }
}