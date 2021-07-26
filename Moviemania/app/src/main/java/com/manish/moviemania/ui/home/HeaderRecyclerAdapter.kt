package com.manish.moviemania.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manish.moviemania.R
import com.manish.moviemania.data.model.MovieResponseItem

class HeaderRecyclerAdapter(
    private var responseList: List<MovieResponseItem>,val listener:HeaderItemClickListener
) : RecyclerView.Adapter<HeaderRecyclerAdapter.HeaderRecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_recycler_item_view, parent, false)

        return HeaderRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderRecyclerViewHolder, position: Int) {

        val response = responseList[position]



        holder.apply {
            Glide.with(responseImage).load(response.image.original).into(responseImage)

            responseImage.setOnClickListener {
                listener.onHeaderItemClicked(response,responseImage)
            }

        }
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    fun setData(newData: MutableList<MovieResponseItem>) {
        responseList = newData
        notifyDataSetChanged()
    }


    class HeaderRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val responseImage: ImageView = itemView.findViewById(R.id.imageHeaderRecyclerView)
        val cardStreamItem: CardView = itemView.findViewById(R.id.cardStreamItem)


    }
}