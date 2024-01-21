package uz.isystem.tmdbapp.ui.movieDetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.ui.main.seeAllMovies.SeeAllActorActivity
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.adapter.CastAdapter
import uz.isystem.tmdbapp.core.adapter.CompaniesAdapter
import uz.isystem.tmdbapp.core.adapter.CountryAdapter
import uz.isystem.tmdbapp.core.adapter.GenreAdapter
import uz.isystem.tmdbapp.core.adapter.SimilarMoviesAdapter
import uz.isystem.tmdbapp.core.adapter.VideoAdapter
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.response.main.home.cast.CastResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails.MovieDetailsResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieTrailer.MovieTrailerResponse
import uz.isystem.tmdbapp.core.models.response.main.home.similarMovies.SimilarMoviesResponse
import uz.isystem.tmdbapp.core.models.response.main.home.watch.AddWatchResponse
import uz.isystem.tmdbapp.core.models.response.main.home.watch.WatchResponse
import uz.isystem.tmdbapp.databinding.ActivityMovieDetailsBinding
import uz.isystem.tmdbapp.ui.accountTmdb.saved.SavedMVP
import uz.isystem.tmdbapp.ui.accountTmdb.saved.SavedPresenter
import uz.isystem.tmdbapp.ui.actorDetails.ActorDetailsActivity
import uz.isystem.tmdbapp.ui.base.BaseActivity
import uz.isystem.tmdbapp.ui.login.LoginActivity
import uz.isystem.tmdbapp.ui.main.home.MoviesFragment
import uz.isystem.tmdbapp.ui.seeAllMovies.SeeAllMoviesActivity
import uz.isystem.tmdbapp.ui.trailer.TrailerActivity
import uz.isystem.tmdbapp.ui.zoomImage.ActivityZoomImages
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MovieDetailsActivity : BaseActivity(), MovieDetailsMVP.View, SavedMVP.View {

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val MOVIE_TYPE = "Movie_Type"
    }

    var data: MovieDetailsResponse? = null

    lateinit var presenter: MovieDetailsMVP.Presenter

    lateinit var binding: ActivityMovieDetailsBinding

    private var language = AppCache.appCache?.getLanguage().toString()

    lateinit var similarMoviesAdapter: SimilarMoviesAdapter
    lateinit var companiesAdapter: CompaniesAdapter
    lateinit var genreAdapter: GenreAdapter
    lateinit var countryAdapter: CountryAdapter

    lateinit var videoAdapter: VideoAdapter

    private lateinit var savedPresenter: SavedPresenter

    private var id = 0

    var isSaved = 0

    private var productionCompanies = ""
    private var productionCountries = ""
    var genre: String = ""


    lateinit var castAdapter: CastAdapter


    override fun getView(): View {
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {

        binding.cardMovieImage.visibility = View.GONE
        binding.bookmark.visibility = View.GONE

        savedPresenter = SavedPresenter(this)
        savedPresenter.loadSavedResult(language = language, page = 1)

//        val color = getColorForRating(binding.ratingBar.rating)
//
//        // Apply the color to the progressTint property
//        binding.ratingBar.progressTintList = ContextCompat.getColorStateList(this, color)


//        binding.youtubeVideo.isClickable = true

        binding.movieName.isSelected = true

        productionCompanies =
            "${String.format(this.resources.getString(R.string.production_companies))}"
        productionCountries =
            "${String.format(this.resources.getString(R.string.production_countries))}"
        genre = "${String.format(this.resources.getString(R.string.genre))}"

        val intent = intent
        id = intent.getIntExtra(MOVIE_DATA, 0)
        //val id=AppCache.appCache!!.getMovieId()
        prepareSimilarMoviesRecyclerView()
        trailerMoviesRecyclerView()
        CastMoviesRecyclerView()
        presenter = MovieDetailsPresenter(this)
        presenter.loadMovieDetails(movieId = id, language = language)
        presenter.loadMovieCast(movieId = id, language = language)
        presenter.loadMovieTrailer(movieId = id, language = language)
        presenter.loadSimilarMovies(movieId = id, language = language)



        binding.bookmark.setOnClickListener {
            if (AppCache.appCache?.isFirstOpen() == true) {

                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            } else {

                savedFun()
            }
        }

//        savedFun()

        castMoviesClicked()

        similarMoviesClicked()

        CompaniesMoviesRecyclerView()

        GenreMoviesRecyclerView()

        CountryMoviesRecyclerView()



        videoAdapter.onItemClicked = {

            if (AppCache.appCache?.isFirstOpen() == false) {

                val bundle = bundleOf(TrailerActivity.KEY_LINK to it.key)
                intent.putExtras(bundle)

                val intent = Intent(this, TrailerActivity::class.java)
                intent.putExtras(bundle)

//            var intent = Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse("https://www.youtube.com/watch?v=" + it.key)
//            )
                this.startActivity(intent)
            } else {

                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
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

//    private fun getColorForRating(rating: Float): Int {
//
//        return when {
//            rating >= 4.0 -> R.color.green // High rating
//            rating >= 2.0 -> R.color.green // Medium rating
//            else -> R.color.green // Low rating
//        }
//    }

    private fun savedFun() {
        if (isSaved == 1) {
            val ss = false
            binding.bookmark.setImageResource(R.drawable.round_bookmark_border_24)
            presenter.addSaved(add = ss, movieId = id)
            isSaved = 2

        } else {
            val ss = true
            binding.bookmark.setImageResource(R.drawable.bookmark_green)
            presenter.addSaved(add = ss, movieId = id)
            isSaved = 1

        }

    }

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

    private fun CompaniesMoviesRecyclerView() {
        companiesAdapter = CompaniesAdapter()
        binding.companiesRv.adapter = companiesAdapter
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.companiesRv.layoutManager = layoutManager
    }

    private fun GenreMoviesRecyclerView() {
        genreAdapter = GenreAdapter()
        binding.genreRv.adapter = genreAdapter
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.genreRv.layoutManager = layoutManager
    }

    private fun CountryMoviesRecyclerView() {
        countryAdapter = CountryAdapter()
        binding.countryRv.adapter = countryAdapter
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.countryRv.layoutManager = layoutManager
    }

    override fun setSimilarMovies(similarMoviesResponse: SimilarMoviesResponse) {
        similarMoviesAdapter.setData(similarMoviesResponse.results)
        if (similarMoviesResponse.results.isNotEmpty()) {
            binding.similarText.visibility = View.VISIBLE
            binding.allButtonSimilar.visibility = View.VISIBLE
        }

    }

    override fun setTrailerMovies(movieDetails: MovieTrailerResponse) {
        videoAdapter.setData(movieDetails.results)
        if (movieDetails.results.isNotEmpty()) {
            binding.videoText.visibility = View.VISIBLE
        }
    }

    override fun getAddSaved(addWatchResponse: AddWatchResponse) {

    }

    override fun setMovieCast(castResponse: CastResponse) {
        castAdapter.setData(castResponse.cast)
        if (castResponse.cast.isNotEmpty()) {
            binding.castCraw.visibility = View.VISIBLE
            binding.allButtonCast.visibility = View.VISIBLE
        }
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

        binding.cardMovieImage.visibility = View.VISIBLE
        binding.rating.visibility = View.VISIBLE
        binding.voteCount.visibility = View.VISIBLE
        binding.voteCount.text = "(${movieDetails.voteCount})"

        val rating = movieDetails.voteAverage / 2

        binding.rating.rating = rating.toFloat()

        if (movieDetails.posterPath == null) {
            Glide.with(binding.movieImage)
                .load(R.drawable.not_found)
                .into(binding.movieImage)
            binding.movieImage.isEnabled = false
        } else {

            Glide.with(binding.movieImage)
                .load("https://image.tmdb.org/t/p/w500" + movieDetails.posterPath)
                .into(binding.movieImage)
        }

        if (movieDetails.backdropPath == null) {
            Glide.with(binding.backgroundImage)
                .load(R.drawable.not_found)
                .into(binding.backgroundImage)
            binding.backgroundImage.isEnabled = false
        } else {
            Glide.with(binding.parent)
                .load("https://image.tmdb.org/t/p/w500" + movieDetails.backdropPath)
                .into(binding.backgroundImage)
        }


        binding.movieImage.setOnClickListener {
            var intent = Intent(this, ActivityZoomImages::class.java)
            intent.putExtra(MOVIE_DATA, movieDetails.posterPath.toString())
            startActivity(intent)
        }

        binding.backgroundImage.setOnClickListener {
            var intent = Intent(this, ActivityZoomImages::class.java)
            intent.putExtra(MOVIE_DATA, movieDetails.backdropPath)
            startActivity(intent)
        }

        val sdfInput = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val originalDate: Date
        try {
            originalDate = sdfInput.parse(movieDetails.releaseDate)!!
        } catch (e: ParseException) {
            e.printStackTrace()
            return
        }

        // Format the date to "dd MMMM yyyy" format
        val sdfOutput = SimpleDateFormat("dd-MMMM yyyy ", Locale.getDefault())
        val formattedDate = sdfOutput.format(originalDate)

        binding.releaseDate.text =
            "${String.format(this.resources.getString(R.string.release_date))} ${formattedDate}"

        binding.movieName.text = movieDetails.title
        binding.countryName.text =
            String.format(this.resources.getString(R.string.production_country))

        val price = movieDetails.revenue.toString()
        val reversedPriceText = price.reversed()
        val priceChunks = reversedPriceText.chunked(3)
        binding.movieBudget.text = "${String.format(this.resources.getString(R.string.revenue))} ${
            priceChunks.joinToString(".").reversed()
        }$"

        companiesAdapter.setData(movieDetails.productionCompanies)
        genreAdapter.setData(movieDetails.genres)
        countryAdapter.setData(movieDetails.productionCountries)

//        movieDetails.productionCompanies.forEach {
//            productionCompanies += "${it.name} "
//        }


        try {
            binding.spokenLanguage.text =
                "${String.format(this.resources.getString(R.string.language))}:  ${movieDetails.spokenLanguages[0].name}"
        } catch (e: Exception) {

        }

//        productionCountries = if (movieDetails.productionCountries.size == 1) {
//            "${String.format(this.resources.getString(R.string.production_country))} "
//        } else {
//            "${String.format(this.resources.getString(R.string.production_companies))} "
//        }

//        movieDetails.productionCountries.forEach {
//            var count = 0
//            if (movieDetails.productionCountries.size - 1 > count && movieDetails.productionCountries.size > 0) {
//                productionCountries += "${it.name}, "
//                count++
//            } else {
//                productionCountries += "${it.name} "
//            }
//        }
//        binding.countryName.text = productionCountries


        movieDetails.genres.forEach {
            genre += " ${it.name} "
        }

        binding.movieOverview.text =
            "${String.format(this.resources.getString(R.string.overview))}\n ${movieDetails.overview}"

    }

    override fun setMovieDetails(movieDetails: MovieDetailsResponse) {
        binding.progressBar.visibility = View.GONE
        binding.cardMovieImage.visibility = View.VISIBLE
        binding.bookmark.visibility = View.VISIBLE


        data = movieDetails

        var vote = (data!!.voteAverage * 10).toInt()

        if (vote >= 70) {
            val customProgressDrawables = ContextCompat.getDrawable(this, R.drawable.circle)
            binding.progressBarRate.progressDrawable = customProgressDrawables
        } else if (vote in 50..70) {

            val customProgressDrawable = ContextCompat.getDrawable(this, R.drawable.circle_bad)
            binding.progressBarRate.progressDrawable = customProgressDrawable
        } else {
            val customProgressDrawable = ContextCompat.getDrawable(this, R.drawable.circle_red)
            binding.progressBarRate.progressDrawable = customProgressDrawable

        }

        binding.progressBarRate.progress = vote
        binding.progressBarText.text = (vote).toString() + "%"

        showData(data!!)
    }

    override fun getSavedResult(saveData: WatchResponse) {

        saveData.results.forEach {

            if (it.id == id) {
                savedFun()
                isSaved = 1
            }
        }
    }


    override fun onError(message: String) {
//        Snackbar.make(binding.root, "${message}", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelRequest()
    }
}