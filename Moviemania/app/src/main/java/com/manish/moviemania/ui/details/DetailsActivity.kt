package com.manish.moviemania.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.manish.moviemania.data.model.MovieResponseItem
import com.manish.moviemania.databinding.ActivityDetailsBinding
import org.jsoup.Jsoup

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieItem = intent.getSerializableExtra("item") as? MovieResponseItem


            binding.apply {

                imageBack.setOnClickListener {
                    finish()
                }

                movieDate.text = movieItem?.premiered
                movieTitle.text = movieItem?.name

                val textFromHtml: String = Jsoup.parse(movieItem!!.summary).text()
                textContent.text = textFromHtml

                val rating = movieItem.rating.average
                ratingValue.text = rating.toString()
                movieRating.rating = (rating.div(2)).toFloat()

                Glide.with(this@DetailsActivity).load(movieItem.image.medium).into(detailsImage)
            }



    }
}