package com.example.movieapp.ui.main.movieDetails


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.ui.main.seeAllMovies.SeeAllMoviesActivity
import com.google.android.material.snackbar.Snackbar
import uz.isystem.tmdbapp.core.adapter.MoviesActorAdapter
import uz.isystem.tmdbapp.core.models.response.main.home.castSimilar.CastSimilarResponse
import uz.isystem.tmdbapp.core.models.response.main.home.detailActor.ActorDetailResponse
import uz.isystem.tmdbapp.databinding.ActivityAboutActorBinding
import uz.isystem.tmdbapp.ui.base.BaseActivity

class ActorDetailsActivity : BaseActivity(), ActorDetailsMVP.View {

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val MOVIE_TYPE = "Movie_Type"
    }

    lateinit var data: ActorDetailResponse

    lateinit var actorAdapter: MoviesActorAdapter

    lateinit var presenter: ActorDetailsMVP.Presenter

    lateinit var binding: ActivityAboutActorBinding

    override fun getView(): View? {
        binding = ActivityAboutActorBinding.inflate(layoutInflater)

        return binding.root
    }
    override fun onCreated(savedInstanceState: Bundle?) {

        val intent = intent
        val id = intent.getIntExtra(MOVIE_DATA, 0)
        SimilarMoviesRecyclerView()
        presenter = ActorDetailsPresenter(this)
        presenter.loadActorDetails(id)
        presenter.loadSimilarMovies(id)

        similarMoviesClicked()

        binding.allButton.setOnClickListener {
            var intent = Intent(this, SeeAllMoviesActivity::class.java)
            intent.putExtra(MOVIE_TYPE, "Movies of the actor")
            intent.putExtra(MovieDetailsActivity.MOVIE_DATA, id)
            startActivity(intent)
        }

    }


    fun similarMoviesClicked() {

        actorAdapter.onItemClicked = {

            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)

            startActivity(intent)
        }
    }

    private fun SimilarMoviesRecyclerView() {
        actorAdapter = MoviesActorAdapter()
        binding.similarMovies.adapter = actorAdapter
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.similarMovies.layoutManager = layoutManager

    }

    override fun setSimilarMovies(castSimilarResponse: CastSimilarResponse) {
        actorAdapter.setData(castSimilarResponse.cast)
        if (castSimilarResponse.cast.isNotEmpty()) {
            binding.similarText.visibility = View.VISIBLE
            binding.allButton.visibility = View.VISIBLE
        }

    }

    private fun showData(actorDetailResponse: ActorDetailResponse) {

        Glide.with(binding.movieImage)
            .load("https://image.tmdb.org/t/p/w500" + actorDetailResponse.profilePath)
            .into(binding.movieImage)

        binding.actorName.text = actorDetailResponse.name

        binding.movieName.text = actorDetailResponse.name

        binding.knownFor.text = actorDetailResponse.knownForDepartment

        binding.birthplace.text = actorDetailResponse.placeOfBirth

        binding.dateBirth.text = actorDetailResponse.birthday

        binding.movieOverview.text = "\n ${actorDetailResponse.biography}"
    }

    override fun setActorDetails(actorDetailsResponse: ActorDetailResponse) {
        binding.progressBar.visibility = View.GONE
        data = actorDetailsResponse
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