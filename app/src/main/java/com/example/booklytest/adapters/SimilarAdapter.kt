package com.example.booklytest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.booklytest.R
import com.example.booklytest.pojo.SimilarBook
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_similar.view.*

class SimilarAdapter : RecyclerView.Adapter<SimilarAdapter.SimilarViewHolder>() {

    var similarBookList: List<SimilarBook> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_similar, parent, false)
        return SimilarViewHolder(view)
    }

    override fun getItemCount() = similarBookList.size

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        val similarImage = similarBookList[position]
        Picasso.get().load(similarImage.image).into(holder.ivSimilarBook)
    }

    inner class SimilarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivSimilarBook: ImageView = itemView.ivSimilarBook
    }
}