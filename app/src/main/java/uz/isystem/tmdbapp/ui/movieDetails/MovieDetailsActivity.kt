package com.example.movieapp.ui.main.movieDetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.ui.main.seeAllMovies.SeeAllActorActivity
import com.example.movieapp.ui.main.seeAllMovies.SeeAllMoviesActivity
import com.example.movieapp.ui.main.trailer.TrailerActivity
import com.google.android.material.snackbar.Snackbar
import uz.isystem.tmdbapp.core.adapter.CastAdapter
import uz.isystem.tmdbapp.core.adapter.SimilarMoviesAdapter
import uz.isystem.tmdbapp.core.adapter.VideoAdapter
import uz.isystem.tmdbapp.core.models.response.main.home.cast.CastResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails.MovieDetailsResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieTrailer.MovieTrailerResponse
import uz.isystem.tmdbapp.core.models.response.main.home.similarMovies.SimilarMoviesResponse
import uz.isystem.tmdbapp.databinding.ActivityMovieDetailsBinding
import uz.isystem.tmdbapp.ui.base.BaseActivity
import uz.isystem.tmdbapp.ui.main.home.MoviesFragment


class MovieDetailsActivity : BaseActivity(), MovieDetailsMVP.View {

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val MOVIE_TYPE = "Movie_Type"
    }

    lateinit var data: MovieDetailsResponse

    lateinit var presenter: MovieDetailsMVP.Presenter

    lateinit var binding: ActivityMovieDetailsBinding

    private var productionCompanies: String = "Production companies: "
    lateinit var productionCountries: String
    var genre: String = "Genre: "

    lateinit var similarMoviesAdapter: SimilarMoviesAdapter

    lateinit var videoAdapter: VideoAdapter

    lateinit var castAdapter: CastAdapter


    override fun getView(): View? {
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {

        binding.youtubeVideo.isClickable = true

        val intent = intent
        val id = intent.getIntExtra(MOVIE_DATA, 0)
        //val id=AppCache.appCache!!.getMovieId()
        prepareSimilarMoviesRecyclerView()
        trailerMoviesRecyclerView()
        CastMoviesRecyclerView()
        presenter = MovieDetailsPresenter(this)
        presenter.loadMovieDetails(id)
        presenter.loadMovieCast(id)
        presenter.loadMovieTrailer(id)
        presenter.loadSimilarMovies(id)

        castMoviesClicked()

        similarMoviesClicked()

        videoAdapter.onItemClicked = {

            val bundle = bundleOf(TrailerActivity.KEY_LINK to it.key)

            val intent = Intent(this, TrailerActivity::class.java)

            intent.putExtras(bundle)

            this.startActivity(intent)

            //            Open Youtube
//            var intent = Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse("https://www.youtube.com/watch?v=" + it.key)
//            )
        }

        binding.allButtonSimilar.setOnClickListener {
            var intent = Intent(this, SeeAllMoviesActivity::class.java)
            intent.putExtra(MOVIE_TYPE, "Similar Movies")
            intent.putExtra(MOVIE_DATA, id)
            startActivity(intent)
        }
        binding.allButtonCast.setOnClickListener {
            var intent = Intent(this, SeeAllActorActivity::class.java)
            intent.putExtra(MOVIE_TYPE, "Cast")
            intent.putExtra(MOVIE_DATA, id)
            startActivity(intent)
        }
    }


//        binding.favoriteButtom.setOnClickListener {
//            presenter.markAsFavorite(id)
//        }

    fun similarMoviesClicked() {

        similarMoviesAdapter.onItemClicked = {

            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(MoviesFragment.MOVIE_DATA, it.id)

            startActivity(intent)
        }
    }

    fun castMoviesClicked() {

        castAdapter.onItemClicked = {

            val intent = Intent(this, ActorDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)

            startActivity(intent)
        }
    }


    private fun prepareSimilarMoviesRecyclerView() {
        similarMoviesAdapter = SimilarMoviesAdapter()
        binding.similarMovies.adapter = similarMoviesAdapter
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.similarMovies.layoutManager = layoutManager
    }

    private fun trailerMoviesRecyclerView() {
        videoAdapter = VideoAdapter()
        binding.youtubeVideo.adapter = videoAdapter
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.youtubeVideo.layoutManager = layoutManager
    }

    private fun CastMoviesRecyclerView() {
        castAdapter = CastAdapter()
        binding.actors.adapter = castAdapter
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.actors.layoutManager = layoutManager
    }

    override fun setSimilarMovies(similarMoviesResponse: SimilarMoviesResponse) {
        similarMoviesAdapter.setData(similarMoviesResponse.results)

    }

    override fun setTrailerMovies(movieDetails: MovieTrailerResponse) {
        videoAdapter.setData(movieDetails.results)
    }

    override fun setMovieCast(castResponse: CastResponse) {
        castAdapter.setData(castResponse.cast)
    }


    override fun getFavoriteResult(success: Boolean) {
//        if (success) {
//            Toast.makeText(
//                applicationContext,
//                "Marked as favorite successfully",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            Toast.makeText(applicationContext, "Not marked, error", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun showData(movieDetails: MovieDetailsResponse) {

        Glide.with(binding.movieImage)
            .load("https://image.tmdb.org/t/p/w500" + movieDetails.posterPath)
            .into(binding.movieImage)

        binding.releaseDate.text = "Release date: ${movieDetails.releaseDate}"

        binding.movieName.text = movieDetails.title

        binding.movieBudget.text = "Budget  ${movieDetails.budget} $"

        movieDetails.productionCompanies.forEach {
            productionCompanies += "${it.name} "
        }
        binding.productionCompanies.text = productionCompanies

        binding.spokenLanguage.text = "Language: ${movieDetails.spokenLanguages[0].name}"

        productionCountries = if (movieDetails.productionCountries.size == 1) {
            "Production country: "
        } else {
            "Production countries: "
        }

        movieDetails.productionCountries.forEach {
            var count = 0
            if (movieDetails.productionCountries.size - 1 > count && movieDetails.productionCountries.size > 0) {
                productionCountries += "${it.name}, "
                count++
            } else {
                productionCountries += "${it.name} "
            }
        }
        binding.countryName.text = productionCountries


        movieDetails.genres.forEach {
            genre += "${it.name} "
        }
        binding.movieGenre.text = genre

        binding.movieOverview.text = "Overview:\n ${movieDetails.overview}"

        Glide.with(binding.parent)
            .load("https://image.tmdb.org/t/p/w500" + movieDetails.backdropPath)
            .into(binding.backgroundImage)

    }

    override fun setMovieDetails(movieDetails: MovieDetailsResponse) {
        binding.progressBar.visibility = View.GONE
        data = movieDetails
//        binding.progressBar.progress = (data.vote_average * 10).toInt()
//        binding.progressBarText.text = ((data.vote_average * 10).toInt()).toString() + "%"
        showData(data)
    }


    override fun onError(message: String) {
        Snackbar.make(binding.root, "${message}", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelRequest()
    }
}