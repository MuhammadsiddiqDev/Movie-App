package uz.isystem.tmdbapp.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.ui.main.movieDetails.MovieDetailsActivity
import com.example.movieapp.ui.main.seeAllMovies.SeeAllMoviesActivity
import com.google.android.material.snackbar.Snackbar
import uz.isystem.tmdbapp.core.adapter.HomeAdapter
import uz.isystem.tmdbapp.databinding.FragmentMoviesBinding
import uz.isystem.tmdbapp.ui.base.BaseFragment

class MoviesFragment : BaseFragment(), HomeMVP.View {

    companion object {
        const val MOVIE_DATA = "Movie_Data"
        const val MOVIE_TYPE = "Movie_Type"
    }


    private lateinit var binding: FragmentMoviesBinding

    private var homeMVP: HomeMVP.Presenter? = null

    private var adapter = HomeAdapter()
    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onCreated(savedInstanceState: Bundle?) {


        homeMVP = HomePresenter(this)
        loadData()
        setViewState()
        onSeeAllClicked()
        onRecyclerViewClicked()

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

        homeMVP?.loadSliderData()
        homeMVP?.loadPopular()
        homeMVP?.loadNowPlaying()
        homeMVP?.loadTopRated()
        homeMVP?.loadUpcomingData()

    }

    override fun onViewDestroy() {
        homeMVP?.cancelRequest()
        adapter.clearData()

    }

    override fun isLoading(isLoading: Boolean) {

    }

    override fun onError(message: String?) {
        val snackBar = message?.let {
            Snackbar.make(binding.groupList2, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun setMovieData(data: BaseData) {

        binding.progressBar.visibility = View.GONE

        adapter.addData(data)
    }


}
