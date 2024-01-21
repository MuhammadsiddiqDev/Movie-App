package uz.isystem.tmdbapp.ui.accountTmdb.saved

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import uz.isystem.tmdbapp.core.adapter.SavedAdapter
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.response.main.home.watch.WatchResponse
import uz.isystem.tmdbapp.databinding.FragmentSavedBinding
import uz.isystem.tmdbapp.ui.base.BaseFragment
import uz.isystem.tmdbapp.ui.movieDetails.MovieDetailsActivity

class FragmentSaved : BaseFragment(), SavedMVP.View {


    companion object {
        const val MOVIE_DATA = "Movie_Data"
    }

    private lateinit var savedPresenter: SavedPresenter
    private var savedAdapter: SavedAdapter = SavedAdapter()

    private var page = 1
    private var pageCount = 0

    private var language = AppCache.appCache?.getLanguage()

    private lateinit var binding: FragmentSavedBinding

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {

        savedAdapter.setListener(object : SavedAdapter.MyPage {
            override fun loadMorePage() {
                if (page <= pageCount) {
                    savedPresenter.loadSavedResult(language = language.toString(), page = page)
                    binding.progressBarPaging.visibility = View.VISIBLE
                }
            }
        })


        savedPresenter = SavedPresenter(this)

        savedPresenter.loadSavedResult(language = language.toString(), page = page)

        prepareRecyclerview()

        actionClicked()

    }

    private fun actionClicked() {
        savedAdapter.itemClicked = {
            var intent = Intent(requireActivity(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }
    }


    override fun onViewDestroy() {

        savedPresenter.cancelRequest()

    }

    private fun prepareRecyclerview() {
        binding.savedRecyclerview.adapter = savedAdapter
        var layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        binding.savedRecyclerview.layoutManager = layoutManager
    }

    override fun getSavedResult(saveData: WatchResponse) {

        binding.progressBar.visibility = View.GONE

        binding.progressBarPaging.visibility = View.GONE

        pageCount = saveData.totalPages
        if (page == 1) {
            prepareRecyclerview()
        }
        page += 1
        if (saveData.results.isEmpty()) {
            binding.emoji.visibility = View.VISIBLE
            binding.textEmpty.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE

            binding.progressBarPaging.visibility = View.GONE
        }
        savedAdapter.setData(saveData.results)

    }


    override fun onError(message: String) {

    }
}