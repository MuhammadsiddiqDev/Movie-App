package com.example.movieapp.ui.main.seeAllMovies

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.ui.main.movieDetails.MovieDetailsActivity
import com.google.android.material.snackbar.Snackbar
import uz.isystem.tmdbapp.core.adapter.SeeAllMoviesAdapter
import uz.isystem.tmdbapp.core.models.response.main.home.UpcomingMovie.UpcomingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.castSimilar.CastSimilarResponse
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.NowPlayingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.popularMovie.PopularResponse
import uz.isystem.tmdbapp.core.models.response.main.home.similarMovies.SimilarMoviesResponse
import uz.isystem.tmdbapp.core.models.response.main.home.topRatedMovie.TopRatedResponse
import uz.isystem.tmdbapp.databinding.ActivitySeeAllMovieBinding
import uz.isystem.tmdbapp.ui.base.BaseActivity
import uz.isystem.tmdbapp.ui.main.home.MoviesFragment

class SeeAllMoviesActivity : BaseActivity(), SeeAllMoviesMVP.View {

    private lateinit var binding: ActivitySeeAllMovieBinding
    private lateinit var presenter: SeeAllMoviesMVP.Presenter
    private var adapter: SeeAllMoviesAdapter = SeeAllMoviesAdapter()
    private lateinit var MovieType: String

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val MOVIE_TYPE = "Movie_Type"
    }

    override fun getView(): View? {
        binding = ActivitySeeAllMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        var intent = intent
        MovieType = intent.getStringExtra(MOVIE_TYPE)!!

        val id = intent.getIntExtra(MoviesFragment.MOVIE_DATA, 0)

        binding.movieType.text = MovieType

        //Now Playing Movies
        presenter = SeeAllMoviesPresenter(this)
        when (MovieType) {
            "Now Playing" -> {
                presenter.loadNowPlayingMovies()
            }
            "Top Rated Movies" -> {
                presenter.loadTopRatedMovies()
            }
            "Upcoming Movies" -> {
                presenter.loadUpcomingMovies()
            }
            "Similar Movies" -> {
                presenter.loadSimilarMovies(id)
            }
            "Movies of the actor" -> {
                presenter.loadSimilarActor(id)
            }
            else -> {
                presenter.loadPopularMovies()
            }
        }
        prepareRecyclerView()
        onRecyclerViewClicked()
        pagination()
    }

    private fun onRecyclerViewClicked() {
        adapter.onItemClicked = {
            var intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }
    }

    private fun prepareRecyclerView() {
        var layoutManager =
            GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
    }

    private fun pagination() {
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelRequest()
    }

    override fun setPopularMovies(popularMoviesResponse: PopularResponse) {
        binding.progressBar.visibility = View.GONE
        adapter.setData(popularMoviesResponse.results)
    }

    override fun setNowPlayingMovies(nowPlayingResponse: NowPlayingResponse) {
        binding.progressBar.visibility = View.GONE
        adapter.setData(nowPlayingResponse.results)
    }

    override fun setTopRatedMovies(topRatedResponse: TopRatedResponse) {
        binding.progressBar.visibility = View.GONE
        adapter.setData(topRatedResponse.results)
    }

    override fun setUpcomingMovies(upcomingResponse: UpcomingResponse) {
        binding.progressBar.visibility = View.GONE
        adapter.setData(upcomingResponse.results)
    }

    override fun setSimilarMovies(similarMoviesResponse: SimilarMoviesResponse) {
        binding.progressBar.visibility = View.GONE
        adapter.setData(similarMoviesResponse.results)
    }

    override fun setSimilarActor(castSimilarResponse: CastSimilarResponse) {
        binding.progressBar.visibility = View.GONE
        adapter.setData(castSimilarResponse.cast)
    }

    override fun onError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}