package com.example.movieapp.ui.main.home.searchFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.ui.main.movieDetails.MovieDetailsActivity
import com.google.android.material.snackbar.Snackbar
import uz.isystem.tmdbapp.core.adapter.SearchAdapter
import uz.isystem.tmdbapp.core.models.response.main.home.search.SearchData
import uz.isystem.tmdbapp.databinding.FragmentSearchBinding
import uz.isystem.tmdbapp.ui.base.BaseFragment
import java.net.URLEncoder

class SearchFragment : BaseFragment(), SearchMVP.View {

    private lateinit var searchPresenter: SearchMVP.Presenter
    private lateinit var searchAdapter: SearchAdapter

    companion object {
        const val MOVIE_DATA = "Movie_Data"
    }

    private lateinit var binding: FragmentSearchBinding
    override fun createView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        searchPresenter = SearchPresenter(this)


        binding.searchButton.setOnClickListener {

            binding.progressBar.visibility = View.VISIBLE

            if (binding.editText.text.isNotEmpty()) {
                var queryText: String = binding.editText.text.toString()
                val query: String = URLEncoder.encode(queryText, "utf-8")
                searchPresenter.loadSearchResult(query)
                binding.searchIcon.visibility = View.INVISIBLE
                binding.searchText.visibility = View.INVISIBLE
                binding.notFoundText.visibility = View.INVISIBLE
                binding.notFoundImage.visibility = View.INVISIBLE
            } else {
                searchAdapter.setData(emptyList())
            }
        }

        prepareRecyclerview()

        searchAdapter.onItemClicked = {
            var intent = Intent(requireActivity(), MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_DATA, it.id)
            startActivity(intent)
        }

        doSomething(binding.editText)
    }

    override fun onViewDestroy() {
        searchPresenter.cancelRequest()
    }


    private fun doSomething(search: EditText) {

        search.setOnEditorActionListener(TextView.OnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {

                if (binding.editText.text.isNotEmpty()) {
                    var queryText: String = binding.editText.text.toString()
                    val query: String = URLEncoder.encode(queryText, "utf-8")
                    searchPresenter.loadSearchResult(query)
                    binding.searchText.visibility = View.INVISIBLE
                    binding.searchIcon.visibility = View.INVISIBLE
                }

                return@OnEditorActionListener true
            }
            false
        })
    }


    private fun prepareRecyclerview() {
        searchAdapter = SearchAdapter()
        binding.searchRecyclerview.adapter = searchAdapter
        var layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        binding.searchRecyclerview.layoutManager = layoutManager
    }


    override fun getSearchResult(movieData: List<SearchData>) {
        binding.progressBar.visibility = View.GONE
//        binding.shimmerLayout.stopShimmer()
//        binding.shimmerLayout.visibility = View.GONE
        if (movieData.isEmpty()) {
            binding.notFoundImage.visibility = View.VISIBLE
            binding.notFoundText.visibility = View.VISIBLE
        } else {
            binding.notFoundImage.visibility = View.INVISIBLE
            binding.notFoundText.visibility = View.INVISIBLE
        }
        searchAdapter.setData(movieData)
    }

    override fun onError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}