package com.manish.moviemania.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manish.moviemania.R
import com.manish.moviemania.data.model.MovieResponseItem

class ShowingAdapter(
     val listener:NowShowingItemClickListener
) : RecyclerView.Adapter<ShowingAdapter.ShowingViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<MovieResponseItem>(){
        override fun areItemsTheSame(oldItem: MovieResponseItem, newItem: MovieResponseItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: MovieResponseItem, newItem: MovieResponseItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowingViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.now_showing_item_layout, parent, false)

        return ShowingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowingViewHolder, position: Int) {


        val response = differ.currentList[position]



        holder.apply {
            Glide.with(responseImage).load(response.image.medium).into(responseImage)
            responseImage.setOnClickListener {

                listener.onShowingItemClicked(response,responseImage)

            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class ShowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val responseImage: ImageView = itemView.findViewById(R.id.movieImage)


    }
}