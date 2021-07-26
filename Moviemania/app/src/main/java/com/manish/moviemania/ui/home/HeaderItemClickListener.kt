package com.manish.moviemania.ui.home

import android.widget.ImageView
import com.manish.moviemania.data.model.MovieResponseItem

interface HeaderItemClickListener {

    fun onHeaderItemClicked(item: MovieResponseItem,mImageView:ImageView)
}