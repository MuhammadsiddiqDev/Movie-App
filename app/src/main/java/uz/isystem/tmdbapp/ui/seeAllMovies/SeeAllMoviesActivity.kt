package uz.isystem.tmdbapp.ui.seeAllMovies

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.ui.main.seeAllMovies.SeeAllMoviesMVP
import com.google.android.material.snackbar.Snackbar
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.adapter.SeeAllMoviesAdapter
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.response.main.home.UpcomingMovie.UpcomingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.castSimilar.CastSimilarResponse
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.NowPlayingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.popularMovie.PopularResponse
import uz.isystem.tmdbapp.core.models.response.main.home.similarMovies.SimilarMoviesResponse
import uz.isystem.tmdbapp.core.models.response.main.home.topRatedMovie.TopRatedResponse
import uz.isystem.tmdbapp.databinding.ActivitySeeAllMovieBinding
import uz.isystem.tmdbapp.ui.base.BaseActivity
import uz.isystem.tmdbapp.ui.main.home.MoviesFragment
import uz.isystem.tmdbapp.ui.movieDetails.MovieDetailsActivity

class SeeAllMoviesActivity : BaseActivity(), SeeAllMoviesMVP.View {

    private lateinit var binding: ActivitySeeAllMovieBinding
    private lateinit var presenter: SeeAllMoviesMVP.Presenter
    private var adapter: SeeAllMoviesAdapter = SeeAllMoviesAdapter()
    private lateinit var MovieType: String
    private var page = 1
    private var pageCount = 0

    private var language = AppCache.appCache?.getLanguage().toString()

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

        adapter.setListener(object : SeeAllMoviesAdapter.MyPage {
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

    private fun loadData() {

        val id = intent.getIntExtra(MoviesFragment.MOVIE_DATA, 0)

        //Now Playing Movies
        presenter = SeeAllMoviesPresenter(this)
        when (MovieType) {
            "Now Playing" -> {
                presenter.loadNowPlayingMovies(language = language, page)
                binding.movieType.text = String.format(this.getString(R.string.now_playing))
            }

            "Top Rated Movies" -> {
                presenter.loadTopRatedMovies(language = language, page)
                binding.movieType.text = String.format(this.getString(R.string.top_rated))
            }

            "Upcoming Movies" -> {
                presenter.loadUpcomingMovies(language = language, page)
                binding.movieType.text = String.format(this.getString(R.string.upcoming))
            }

            "Similar Movies" -> {
                presenter.loadSimilarMovies(id, language = language, page)
                binding.movieType.text = String.format(this.getString(R.string.similar_movies))
            }

            "Movies of the actor" -> {
                presenter.loadSimilarActor(id, language = language)
                binding.movieType.text = String.format(this.getString(R.string.movies_of_the_actor))
            }

            else -> {
                presenter.loadPopularMovies(language = language, page)
                binding.movieType.text = String.format(this.getString(R.string.popular))
            }
        }
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelRequest()
    }

    override fun setPopularMovies(popularResponse: PopularResponse) {
        binding.progressBar.visibility = View.GONE
        binding.progressBarPaging.visibility = View.GONE

        pageCount = popularResponse.totalPages
        if (page == 1) {
            prepareRecyclerView()
        }
        page += 1
        adapter.setData(popularResponse.results)
    }

    override fun setNowPlayingMovies(nowPlayingResponse: NowPlayingResponse) {
        binding.progressBar.visibility = View.GONE
        binding.progressBarPaging.visibility = View.GONE

        pageCount = nowPlayingResponse.totalPages
        if (page == 1) {
            prepareRecyclerView()
        }
        page += 1
        adapter.setData(nowPlayingResponse.results)
    }

    override fun setTopRatedMovies(topRatedResponse: TopRatedResponse) {
        binding.progressBar.visibility = View.GONE
        binding.progressBarPaging.visibility = View.GONE

        pageCount = topRatedResponse.totalPages
        if (page == 1) {
            prepareRecyclerView()
        }
        page += 1
        adapter.setData(topRatedResponse.results)
    }

    override fun setUpcomingMovies(upcomingResponse: UpcomingResponse) {
        binding.progressBar.visibility = View.GONE
        binding.progressBarPaging.visibility = View.GONE

        pageCount = upcomingResponse.totalPages
        if (page == 1) {
            prepareRecyclerView()
        }
        page += 1
        adapter.setData(upcomingResponse.results)
    }

    override fun setSimilarMovies(similarMoviesResponse: SimilarMoviesResponse) {
        binding.progressBar.visibility = View.GONE
        binding.progressBarPaging.visibility = View.GONE

        pageCount = similarMoviesResponse.totalPages
        if (page == 1) {
            prepareRecyclerView()
        }
        page += 1
        adapter.setData(similarMoviesResponse.results)
    }

    override fun setSimilarActor(castSimilarResponse: CastSimilarResponse) {
        binding.progressBar.visibility = View.GONE
        binding.progressBarPaging.visibility = View.GONE
        adapter.setData(castSimilarResponse.cast)
    }

    override fun onError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}