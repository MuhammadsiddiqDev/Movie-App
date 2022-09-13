package com.example.movieapp.ui.main.home.searchFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.ui.main.movieDetails.ActorDetailsActivity
import com.example.movieapp.ui.main.seeAllMovies.SeeAllActorActivity
import com.google.android.material.snackbar.Snackbar
import uz.isystem.tmdbapp.core.adapter.CelebritiesAdapter
import uz.isystem.tmdbapp.core.adapter.TrendingPersonAdapter
import uz.isystem.tmdbapp.core.models.response.main.home.popularPeople.PeoplePopularResponse
import uz.isystem.tmdbapp.databinding.FragmentCelebritiesBinding
import uz.isystem.tmdbapp.ui.base.BaseFragment

class CelebritiesFragment : BaseFragment(), CelebritiesMVP.View {

    private lateinit var celebritiesPresenter: CelebritiesMVP.Presenter
    private lateinit var celebritiesAdapter: CelebritiesAdapter

    private lateinit var trendingAdapter: TrendingPersonAdapter

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val MOVIE_TYPE = "Movie_Type"
    }

    private lateinit var binding: FragmentCelebritiesBinding
    override fun createView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCelebritiesBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onCreated(savedInstanceState: Bundle?) {
        celebritiesPresenter = CelebritiesPresenter(this)

        popularRecyclerview()
        trendingRecyclerview()

        celebritiesPresenter.loadCelebritiesCast()
        celebritiesPresenter.loadCelebritiesTrending()

        celebritiesAdapter.onItemClicked = {
            var intent = Intent(requireActivity(), ActorDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }
        trendingAdapter.onItemClicked = {
            var intent = Intent(requireActivity(), ActorDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }

        binding.allButtonPopular.setOnClickListener {
            var intent = Intent(requireActivity(), SeeAllActorActivity::class.java)
            intent.putExtra(MOVIE_TYPE, "Actor Popular")
            startActivity(intent)
        }
        binding.allButtonTrending.setOnClickListener {
            var intent = Intent(requireActivity(), SeeAllActorActivity::class.java)
            intent.putExtra(MOVIE_TYPE, "Actor Trending")
            startActivity(intent)
        }
    }

    override fun onViewDestroy() {
        celebritiesPresenter.cancelRequest()
    }


    private fun popularRecyclerview() {
        celebritiesAdapter = CelebritiesAdapter()
        binding.groupList1.adapter = celebritiesAdapter
        var layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        binding.groupList1.layoutManager = layoutManager
    }

    private fun trendingRecyclerview() {
        trendingAdapter = TrendingPersonAdapter()
        binding.groupList3.adapter = trendingAdapter
        var layoutManager = GridLayoutManager(context, 4, GridLayoutManager.HORIZONTAL, false)
        binding.groupList3.layoutManager = layoutManager
    }

    override fun getCelebritiesCast(popularResponse: PeoplePopularResponse) {
        binding.progressBar.visibility = View.GONE
        celebritiesAdapter.setData(popularResponse.results)
        if (popularResponse.results.isNotEmpty()) {
            binding.popularTitle.visibility = View.VISIBLE
            binding.allButtonPopular.visibility = View.VISIBLE
        }
    }

    override fun getCelebritiesTrending(popularResponse: PeoplePopularResponse) {
        binding.progressBar.visibility = View.GONE
        trendingAdapter.setData(popularResponse.results)
        if (popularResponse.results.isNotEmpty()) {
            binding.trendTitle.visibility = View.VISIBLE
            binding.allButtonTrending.visibility = View.VISIBLE
        }
    }

    override fun onError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}