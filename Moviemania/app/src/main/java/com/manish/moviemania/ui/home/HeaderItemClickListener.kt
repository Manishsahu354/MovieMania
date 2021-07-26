package com.manish.moviemania.ui.home

import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.manish.moviemania.data.model.MovieResponseItem

interface HeaderItemClickListener {

    fun onHeaderItemClicked(item: MovieResponseItem,mImageView:ImageView)
}