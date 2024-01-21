package uz.isystem.tmdbapp.ui.searchFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.adapter.SearchActorAdapter
import uz.isystem.tmdbapp.core.adapter.SearchAdapter
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.response.main.home.search.actior.SearchActorResponse
import uz.isystem.tmdbapp.core.models.response.main.home.search.movie.SearchResponse
import uz.isystem.tmdbapp.databinding.FragmentSearchBinding
import uz.isystem.tmdbapp.ui.actorDetails.ActorDetailsActivity
import uz.isystem.tmdbapp.ui.base.BaseFragment
import uz.isystem.tmdbapp.ui.movieDetails.MovieDetailsActivity
import java.net.URLEncoder

class SearchFragment : BaseFragment(), SearchMVP.View {

    companion object {
        const val MOVIE_DATA = "Movie_Data"
    }

    private lateinit var searchPresenter: SearchMVP.Presenter
    private var searchAdapter: SearchAdapter = SearchAdapter()
    private var searchActorAdapter: SearchActorAdapter = SearchActorAdapter()

    private var page = 1
    private var pageCount = 0
    private var query: String = ""

    private var movieActor = true

    private lateinit var binding: FragmentSearchBinding

    private var language = AppCache.appCache?.getLanguage().toString()
    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {

        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.editText, InputMethodManager.SHOW_IMPLICIT)

        searchAdapter.setListener(object : SearchAdapter.MyPage {
            override fun loadMorePage() {
                if (page <= pageCount) {
                    var queryText: String = binding.editText.text.toString()
                    query = URLEncoder.encode(queryText, "utf-8")

                    searchPresenter.loadSearchResult(query, language = language, page = page)
                    binding.progressBarPaging.visibility = View.VISIBLE
                }
            }
        })

        searchActorAdapter.setListener(object : SearchActorAdapter.MyPage {
            override fun loadMorePage() {
                if (page <= pageCount) {
                    var queryText: String = binding.editText.text.toString()
                    query = URLEncoder.encode(queryText, "utf-8")

                    searchPresenter.loadSearchActorResult(query, language = language, page = page)
                    binding.progressBarPaging.visibility = View.VISIBLE
                }
            }
        })


        searchPresenter = SearchPresenter(this)

        binding.searchButton.setOnClickListener {

            page = 1

            if (binding.editText.text.isNotEmpty()) {
                binding.editText.isEnabled = false
                if (movieActor == true) {
                    binding.editText.isEnabled = true
                    movieSearch()
                } else {
                    actorSearch()
                    binding.editText.isEnabled = true
                }
            } else {
                binding.editText.isEnabled = true
                Toast.makeText(
                    requireContext(),
                    "${String.format(requireContext().getString(R.string.the_search_field_is_empty))}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        prepareRecyclerview()

        actorRecyclerview()

        actionFun()

        action()

//        doActorSomething(binding.editText)
//        doMovieSomething(binding.editText)

    }

    private fun action() {
        searchAdapter.onItemClicked = {
            var intent = Intent(requireActivity(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }
        searchActorAdapter.onItemClicked = {
            var intent = Intent(requireActivity(), ActorDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }
    }

    private fun movieSearch() {
        searchAdapter.clearData()

        prepareRecyclerview()

        binding.progressBar.visibility = View.VISIBLE

        if (binding.editText.text.isNotEmpty()) {
            var queryText: String = binding.editText.text.toString()
            query = URLEncoder.encode(queryText, "utf-8")

            searchPresenter.loadSearchResult(query, language = language, page = page)
            binding.searchIcon.visibility = View.INVISIBLE
            binding.searchText.visibility = View.INVISIBLE
            binding.notFoundText.visibility = View.INVISIBLE
            binding.notFoundImage.visibility = View.INVISIBLE
        } else {
            searchAdapter.setData(emptyList())
        }

    }

    private fun actorSearch() {

        searchActorAdapter.clearData()

        actorRecyclerview()
        binding.progressBar.visibility = View.VISIBLE

        if (binding.editText.text.isNotEmpty()) {
            var queryText: String = binding.editText.text.toString()
            query = URLEncoder.encode(queryText, "utf-8")

            searchPresenter.loadSearchActorResult(query, language = language, page = page)
            binding.searchIcon.visibility = View.INVISIBLE
            binding.searchText.visibility = View.INVISIBLE
            binding.notFoundText.visibility = View.INVISIBLE
            binding.notFoundImage.visibility = View.INVISIBLE
        } else {
            searchActorAdapter.setData(emptyList())
        }

    }


    override fun onViewDestroy() {
        searchPresenter.cancelRequest()
    }

    private fun doMovieSomething(search: EditText) {

        search.setOnEditorActionListener(TextView.OnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {

                page = 1

                searchAdapter.clearData()
                prepareRecyclerview()

                searchActorAdapter.clearData()
                actorRecyclerview()

                if (binding.editText.text.isNotEmpty()) {
                    var queryText: String = binding.editText.text.toString()
                    val query: String = URLEncoder.encode(queryText, "utf-8")
                    searchPresenter.loadSearchResult(query, language = language, page = page)
                    binding.searchText.visibility = View.INVISIBLE
                    binding.searchIcon.visibility = View.INVISIBLE
                }

                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun doActorSomething(search: EditText) {

        search.setOnEditorActionListener(TextView.OnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {

                page = 1

                searchAdapter.clearData()
                prepareRecyclerview()

                searchActorAdapter.clearData()
                actorRecyclerview()

                if (binding.editText.text.isNotEmpty()) {
                    var queryText: String = binding.editText.text.toString()
                    val query: String = URLEncoder.encode(queryText, "utf-8")
                    searchPresenter.loadSearchActorResult(query, language = language, page = page)
                    binding.searchText.visibility = View.INVISIBLE
                    binding.searchIcon.visibility = View.INVISIBLE
                }

                return@OnEditorActionListener true
            }
            false
        })
    }


    private fun prepareRecyclerview() {
        binding.searchRecyclerview.adapter = searchAdapter
        var layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        binding.searchRecyclerview.layoutManager = layoutManager
    }

    private fun actorRecyclerview() {
        binding.searchActorRecyclerview.adapter = searchActorAdapter
        var layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
        binding.searchActorRecyclerview.layoutManager = layoutManager
    }


    override fun getSearchResult(movieData: SearchResponse) {

        binding.searchRecyclerview.visibility = View.VISIBLE
        binding.searchActorRecyclerview.visibility = View.GONE

        binding.progressBar.visibility = View.GONE
//        binding.shimmerLayout.stopShimmer()
//        binding.shimmerLayout.visibility = View.GONE
        if (movieData.results.isEmpty()) {
            binding.notFoundImage.visibility = View.VISIBLE
            binding.notFoundText.visibility = View.VISIBLE
        } else {
            binding.notFoundImage.visibility = View.INVISIBLE
            binding.notFoundText.visibility = View.INVISIBLE
        }

        binding.progressBarPaging.visibility = View.GONE

        pageCount = movieData.totalPages
        if (page == 1) {
            prepareRecyclerview()
        }
        page += 1
        searchAdapter.setData(movieData.results)
    }

    override fun getSearchActorResult(actorData: SearchActorResponse) {

        binding.searchActorRecyclerview.visibility = View.VISIBLE
        binding.searchRecyclerview.visibility = View.GONE


        binding.progressBar.visibility = View.GONE
        if (actorData.results.isEmpty()) {
            binding.notFoundImage.visibility = View.VISIBLE
            binding.notFoundText.visibility = View.VISIBLE
        } else {
            binding.notFoundImage.visibility = View.INVISIBLE
            binding.notFoundText.visibility = View.INVISIBLE
        }

        binding.progressBarPaging.visibility = View.GONE

        pageCount = actorData.totalPages
        if (page == 1) {
            prepareRecyclerview()
        }
        page += 1
        searchActorAdapter.setData(actorData.results)
    }

    override fun onError(message: String) {
//        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()

        binding.notInternetImage.visibility = View.VISIBLE
        binding.notInternetText.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }


    private fun actionFun() {

        binding.actor1.setOnClickListener {
            binding.actor2.visibility = View.VISIBLE
            binding.movie2.visibility = View.INVISIBLE
            movieActor = false

            binding.searchText.text =
                String.format(requireContext().getString(R.string.search_any_actor))

            searchAdapter.clearData()
            prepareRecyclerview()

        }
        binding.movie1.setOnClickListener {
            binding.movie2.visibility = View.VISIBLE
            binding.actor2.visibility = View.INVISIBLE
            movieActor = true

            binding.searchText.text =
                String.format(requireContext().getString(R.string.search_any_movie))

            searchActorAdapter.clearData()
            actorRecyclerview()


        }
    }

    override fun onStart() {
        super.onStart()
        binding.editText.text.clear()

    }
}