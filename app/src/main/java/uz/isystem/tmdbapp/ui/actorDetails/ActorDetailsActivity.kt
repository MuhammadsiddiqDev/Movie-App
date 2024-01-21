package uz.isystem.tmdbapp.ui.actorDetails


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.ui.main.movieDetails.ActorDetailsMVP
import com.example.movieapp.ui.main.movieDetails.ActorDetailsPresenter
import com.google.android.material.snackbar.Snackbar
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.adapter.MoviesActorAdapter
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.response.main.home.castSimilar.CastSimilarResponse
import uz.isystem.tmdbapp.core.models.response.main.home.detailActor.ActorDetailResponse
import uz.isystem.tmdbapp.databinding.ActivityAboutActorBinding
import uz.isystem.tmdbapp.ui.base.BaseActivity
import uz.isystem.tmdbapp.ui.movieDetails.MovieDetailsActivity
import uz.isystem.tmdbapp.ui.seeAllMovies.SeeAllMoviesActivity
import uz.isystem.tmdbapp.ui.zoomImage.ActivityZoomImages

class ActorDetailsActivity : BaseActivity(), ActorDetailsMVP.View {

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val MOVIE_TYPE = "Movie_Type"
    }

    lateinit var data: ActorDetailResponse

    lateinit var actorAdapter: MoviesActorAdapter

    lateinit var presenter: ActorDetailsMVP.Presenter

    lateinit var binding: ActivityAboutActorBinding

    private var language = AppCache.appCache?.getLanguage().toString()

    override fun getView(): View {
        binding = ActivityAboutActorBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {

        val intent = intent
        val id = intent.getIntExtra(MOVIE_DATA, 0)
        SimilarMoviesRecyclerView()
        presenter = ActorDetailsPresenter(this)
        presenter.loadActorDetails(id, language = language)
        presenter.loadSimilarMovies(id, language = language)

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


        if (actorDetailResponse.biography.isNotEmpty()) {

            binding.movieOverviewText.visibility = View.VISIBLE

        }

        if (actorDetailResponse.knownForDepartment != null) {

            binding.knownForText.visibility = View.VISIBLE

        }
        if (actorDetailResponse.placeOfBirth != null) {

            binding.birthplaceText.visibility = View.VISIBLE

        }
        if (actorDetailResponse.birthday != null) {

            binding.dateBirthText.visibility = View.VISIBLE

        }

        if (actorDetailResponse.profilePath == null) {
            Glide.with(binding.movieImage)
                .load(R.drawable.not_found)
                .into(binding.movieImage)
            binding.movieImage.isEnabled = false
        } else {
            Glide.with(binding.movieImage)
                .load("https://image.tmdb.org/t/p/w500" + actorDetailResponse.profilePath)
                .into(binding.movieImage)
        }

        binding.movieImage.setOnClickListener {
            var intent = Intent(this, ActivityZoomImages::class.java)
            intent.putExtra(MovieDetailsActivity.MOVIE_DATA, actorDetailResponse.profilePath)
            startActivity(intent)
        }

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