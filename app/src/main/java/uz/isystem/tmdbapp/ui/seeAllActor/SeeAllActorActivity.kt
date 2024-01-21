package com.example.movieapp.ui.main.seeAllMovies

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.adapter.SeeAllActorAdapter
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.response.main.home.cast.CastResponse
import uz.isystem.tmdbapp.core.models.response.main.home.popularPeople.PeoplePopularResponse
import uz.isystem.tmdbapp.databinding.ActivitySeeAllMovieBinding
import uz.isystem.tmdbapp.ui.actorDetails.ActorDetailsActivity
import uz.isystem.tmdbapp.ui.base.BaseActivity
import uz.isystem.tmdbapp.ui.movieDetails.MovieDetailsActivity
import uz.isystem.tmdbapp.ui.seeAllActor.SeeAllActorPresenter

class SeeAllActorActivity : BaseActivity(), SeeAllActorMVP.View {

    private lateinit var binding: ActivitySeeAllMovieBinding
    private lateinit var presenter: SeeAllActorMVP.Presenter
    private var adapter: SeeAllActorAdapter = SeeAllActorAdapter()
    private lateinit var MovieType: String
    private var language = AppCache.appCache?.getLanguage().toString()

    private var page = 1
    private var pageCount = 0

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val MOVIE_TYPE = "Movie_Type"
    }

    override fun getView(): View {
        binding = ActivitySeeAllMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        var intent = intent
        MovieType = intent.getStringExtra(MOVIE_TYPE)!!

        adapter.setListener(object : SeeAllActorAdapter.MyPage {
            override fun loadMorePage() {
                if (page <= pageCount) {
                    loadData()
                    binding.progressBarPaging.visibility = View.VISIBLE
                }
            }
        })

        loadData()


        prepareRecyclerView()
        onRecyclerViewClicked()
    }

    fun loadData() {

        val id = intent.getIntExtra(MovieDetailsActivity.MOVIE_DATA, 0)
        binding.movieType.text = MovieType

        presenter = SeeAllActorPresenter(this)
        when (MovieType) {
            "Cast" -> {
                presenter.loadActorDetailSimilar(id, language = language)
                binding.movieType.text = String.format(this.getString(R.string.actors))
            }

            "Actor Trending" -> {
                presenter.loadActorTrending(language = language, page = page)
                binding.movieType.text = String.format(this.getString(R.string.trending))
            }

            else -> {
                presenter.loadActorPopular(language = language, page = page)
                binding.movieType.text = String.format(this.getString(R.string.popular))
            }
        }
    }

    private fun onRecyclerViewClicked() {
        adapter.onItemClicked = {
            var intent = Intent(this, ActorDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }
    }

    private fun prepareRecyclerView() {
        var layoutManager =
            GridLayoutManager(applicationContext, 1, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelRequest()
    }

    override fun setActorTrending(castResponse: CastResponse) {
        binding.progressBar.visibility = View.GONE

        adapter.setData(castResponse.cast)
    }

    override fun setActorPopular(popularResponse: PeoplePopularResponse) {
        binding.progressBar.visibility = View.GONE
        binding.progressBarPaging.visibility = View.GONE

        pageCount = popularResponse.totalPages
        if (page == 1) {
            prepareRecyclerView()
        }
        page += 1
        adapter.setData(popularResponse.results)
    }

    override fun setActorDetailSimilar(popularResponse: PeoplePopularResponse) {
        binding.progressBar.visibility = View.GONE
        binding.progressBarPaging.visibility = View.GONE

        pageCount = popularResponse.totalPages
        if (page == 1) {
            prepareRecyclerView()
        }
        page += 1
        adapter.setData(popularResponse.results)
    }

    override fun onError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}