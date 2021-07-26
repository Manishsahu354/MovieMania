package com.manish.moviemania.ui.home

import android.widget.ImageView
import com.manish.moviemania.data.model.MovieResponseItem

interface NowShowingItemClickListener {

    fun onShowingItemClicked(item: MovieResponseItem , mImageView: ImageView)
}