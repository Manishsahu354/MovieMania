package com.manish.moviemania

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.manish.moviemania.data.model.MovieResponseItem
import com.manish.moviemania.databinding.ActivityMainBinding
import com.manish.moviemania.ui.details.DetailsActivity
import com.manish.moviemania.ui.home.*
import com.manish.moviemania.util.Constants.QUERY_PAGE_SIZE
import com.manish.moviemania.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NowShowingItemClickListener, HeaderItemClickListener {

    private lateinit var binding: ActivityMainBinding

    lateinit var showingAdapter: ShowingAdapter
    lateinit var headerRecyclerAdapter: HeaderRecyclerAdapter
    private val viewModel by viewModels<HomeViewModel>()
    private val responseList: List<MovieResponseItem> = listOf()

    var isLoading  = false
    var isLastPage = false
    var isScrolling = false
    var firstTime = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        getViewModelData()


    }

    private fun getViewModelData() {

//        viewModel.getMovieData()

        viewModel.responseData.observe(this,{response->

            when (response) {

                is NetworkResult.Loading -> {
                    if (firstTime){
                        showShimmerEffect()
                    }else{
                        showProgressBar()
                    }

                }

                is NetworkResult.Success -> {
                    if (firstTime){
                        hideShimmerEffect()
                        firstTime = false
                    }else{
                        hideProgressBar()
                    }
                    response.data?.let { movieResponse->
                        showingAdapter.differ.submitList(movieResponse)

                        val totalPages = movieResponse.size / QUERY_PAGE_SIZE+2
                        isLastPage = viewModel.moviePage  == totalPages

                        if (isLastPage){
                            binding.nowShowingRecyclerview.setPadding(0,0,0,0)
                        }
                    }


                    response.data?.let { headerRecyclerAdapter.setData(it) }
                }

                is NetworkResult.Error -> {

                    if (firstTime){
                        hideShimmerEffect()
                    }else{
                        hideProgressBar()
                    }

                    response.message?.let { message->

                        Snackbar.make(binding.root,"An Error Occured: $message", Snackbar.LENGTH_LONG).show()
                    }
                }
            }


        })
    }

    private fun setUpRecyclerView() {
        showingAdapter = ShowingAdapter(this)
        binding.nowShowingRecyclerview.adapter = showingAdapter
        binding.nowShowingRecyclerview.addOnScrollListener(this.scrollListener)

        headerRecyclerAdapter = HeaderRecyclerAdapter(responseList,this)
        binding.headerRecyclerView.adapter = headerRecyclerAdapter

    }

    private fun showShimmerEffect() {
        binding.headerRecyclerView.showShimmer()
        binding.nowShowingRecyclerview.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.headerRecyclerView.hideShimmer()
        binding.nowShowingRecyclerview.hideShimmer()
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.tvNowShowing.visibility = View.VISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvNowShowing.visibility = View.GONE
        isLoading = true
    }

    private val scrollListener = object :RecyclerView.OnScrollListener(){

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (dy > 0) {

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount

                val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
                val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
                val isNotAtBeginning = firstVisibleItemPosition >= 0
                val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE

                val shouldPaginate =
                    isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                            isTotalMoreThanVisible && isScrolling

                if (shouldPaginate) {
                    viewModel.getMovieData()
                    isScrolling = false
                }
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    isScrolling = true

            }
        }
    }

    override fun onShowingItemClicked(item: MovieResponseItem ,mImageView:ImageView) {
        val intent = Intent(this,DetailsActivity::class.java)
        intent.putExtra("item", item)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            mImageView,
            ViewCompat.getTransitionName(mImageView)!!
        )

        startActivity(intent,options.toBundle())
    }

    override fun onHeaderItemClicked(item: MovieResponseItem,mImageView:ImageView) {

        val intent = Intent(this,DetailsActivity::class.java)
        intent.putExtra("item", item)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            mImageView,
            ViewCompat.getTransitionName(mImageView)!!
        )

        startActivity(intent,options.toBundle())

    }
}