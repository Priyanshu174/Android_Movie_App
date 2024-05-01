package com.example.movie_rating_final.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movie_rating_final.Domain.SliderItems
import com.example.movie_rating_final.R

class SliderAdapter(private var sliderItems: MutableList<SliderItems>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.slide_item_container, parent, false)
        return SliderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(sliderItems[position])
        if (position == sliderItems.size - 2) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageSlide)

        fun bind(sliderItem: SliderItems) {
            val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(60))
            Glide.with(context)
                .load(sliderItem.image)
                .apply(requestOptions)
                .into(imageView)
        }
    }

    private val runnable = Runnable {
        // Duplicate all items in the list (just an example)
        val duplicatedItems = ArrayList(sliderItems)
        sliderItems.addAll(duplicatedItems)
        notifyDataSetChanged()
    }
}
