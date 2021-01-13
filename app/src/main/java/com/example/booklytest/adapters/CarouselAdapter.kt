package com.example.booklytest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.booklytest.R
import com.example.booklytest.pojo.CarouselImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_carousel.view.*

class CarouselAdapter : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    var carouselImageList: List<CarouselImage> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselViewHolder(view)
    }

    override fun getItemCount() = carouselImageList.size

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val carouselImage = carouselImageList[position]
        Picasso.get().load(carouselImage.image).into(holder.ivBook)
    }

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivBook: ImageView = itemView.ivBook
    }
}
