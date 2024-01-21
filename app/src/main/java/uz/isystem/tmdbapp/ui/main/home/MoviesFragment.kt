package uz.isystem.tmdbapp.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import uz.isystem.tmdbapp.core.adapter.HomeAdapter
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.response.login.acountDetail.AccountDetailResponse
import uz.isystem.tmdbapp.core.models.response.login.createRequestToken.CreateRequestTokenResponse
import uz.isystem.tmdbapp.core.models.response.main.home.BaseData
import uz.isystem.tmdbapp.databinding.FragmentMoviesBinding
import uz.isystem.tmdbapp.ui.base.BaseFragment
import uz.isystem.tmdbapp.ui.login.LoginMVP
import uz.isystem.tmdbapp.ui.login.LoginPresenter
import uz.isystem.tmdbapp.ui.movieDetails.MovieDetailsActivity
import uz.isystem.tmdbapp.ui.seeAllMovies.SeeAllMoviesActivity

class MoviesFragment : BaseFragment(), HomeMVP.View, LoginMVP.View {

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val MOVIE_TYPE = "Movie_Type"
    }


    private lateinit var binding: FragmentMoviesBinding

    private var homeMVP: HomeMVP.Presenter? = null

    private lateinit var presenter: LoginMVP.Presenter

    private var language = AppCache.appCache?.getLanguage().toString()

    private var adapter = HomeAdapter()
    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onCreated(savedInstanceState: Bundle?) {


        homeMVP = HomePresenter(this)
        loadData()
        setViewState()
        onSeeAllClicked()
        onRecyclerViewClicked()


        if (AppCache.appCache!!.isFirstOpen() == false) {
            presenter = LoginPresenter(this)
            presenter.getAccountDetail()
        }


        binding.notInternetImage.visibility = View.GONE
        binding.notInternetText.visibility = View.GONE

    }

    private fun setViewState() {
        binding.groupList2.adapter = adapter
        binding.groupList2.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onRecyclerViewClicked() {
        adapter.onSliderClicked = {
            val intent = Intent(requireActivity(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }

        adapter.onNowPlayingClicked = {
            val intent = Intent(requireActivity(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }

        adapter.onTopRatedClicked = {
            val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }

        adapter.onPopularClicked = {
            val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }
        adapter.onUpcomingClicked = {
            val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }
    }

    private fun onSeeAllClicked() {
        adapter.onSeeAllNowPlayingClicked = {
            var intent = Intent(requireActivity(), SeeAllMoviesActivity::class.java)
            intent.putExtra(MOVIE_TYPE, it)
            startActivity(intent)
        }

        adapter.onSeeAllPopularClicked = {
            var intent = Intent(requireActivity(), SeeAllMoviesActivity::class.java)
            intent.putExtra(MOVIE_TYPE, it)

            startActivity(intent)
        }

        adapter.onSeeAllTopRatedClicked = {
            var intent = Intent(requireActivity(), SeeAllMoviesActivity::class.java)
            intent.putExtra(MOVIE_TYPE, it)
            startActivity(intent)
        }
        adapter.onSeeAllUpcomingClicked = {
            var intent = Intent(requireActivity(), SeeAllMoviesActivity::class.java)
            intent.putExtra(MOVIE_TYPE, it)
            startActivity(intent)
        }
    }

    private fun loadData() {

        homeMVP?.loadSliderData(language = language)
        homeMVP?.loadPopular(language = language)
        homeMVP?.loadNowPlaying(language = language)
        homeMVP?.loadTopRated(language = language)
        homeMVP?.loadUpcomingData(language = language)

    }

    override fun onViewDestroy() {
        homeMVP?.cancelRequest()
        adapter.clearData()

    }

    override fun isLoading(isLoading: Boolean) {

    }

    override fun setData(any: Any) {

    }

    override fun createRequestToken(token: String) {
    }

    override fun createSessionWithLogin(response: CreateRequestTokenResponse) {
    }

    override fun createSession(sessionId: String) {
    }

    override fun accountDetail(accountDetail: AccountDetailResponse) {

        AppCache.appCache!!.setUsername(accountDetail.username)

        if (accountDetail.avatar.tmdb.avatarPath != null) {

            AppCache.appCache!!.setImage("https://image.tmdb.org/t/p/w500${accountDetail.avatar.tmdb.avatarPath}")

        } else {

            AppCache.appCache!!.setImage("https://c8.alamy.com/zooms/9/80d94c5b96c54446b2dc609a62b9f61b/2c5xkmf.jpg")

        }

        if (accountDetail.name == "") {
            AppCache.appCache!!.setName("Name")
        } else {
            AppCache.appCache!!.setName(accountDetail.name)
        }
        binding.progressBar.visibility = View.GONE
    }

    override fun onError(message: String?) {
//        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()

        binding.notInternetImage.visibility = View.VISIBLE
        binding.notInternetText.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    override fun setMovieData(data: BaseData) {

        binding.progressBar.visibility = View.GONE

        adapter.addData(data)
    }


}
