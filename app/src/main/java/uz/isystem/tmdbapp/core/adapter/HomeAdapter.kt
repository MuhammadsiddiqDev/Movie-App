package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.tmdbapp.core.models.response.main.home.BaseData
import uz.isystem.tmdbapp.core.models.response.main.home.UpcomingMovie.UpcomingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.MovieData
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.NowPlayingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.popularMovie.PopularResponse
import uz.isystem.tmdbapp.core.models.response.main.home.sliderMovie.SliderResponse
import uz.isystem.tmdbapp.core.models.response.main.home.topRatedMovie.TopRatedResponse
import uz.isystem.tmdbapp.databinding.NowPlayingItemGroupBinding
import uz.isystem.tmdbapp.databinding.PopularItemGroupBinding
import uz.isystem.tmdbapp.databinding.SliderItemGroupBinding
import uz.isystem.tmdbapp.databinding.TopRatedItemGroupBinding
import uz.isystem.tmdbapp.databinding.UpcomingItemGroupBinding
import java.util.Timer
import java.util.TimerTask

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onSliderClicked: ((MovieData) -> Unit)? = null
    var onTopRatedClicked: ((MovieData) -> Unit)? = null
    var onNowPlayingClicked: ((MovieData) -> Unit)? = null
    var onPopularClicked: ((MovieData) -> Unit)? = null
    var onUpcomingClicked: ((MovieData) -> Unit)? = null

    var onSeeAllTopRatedClicked: ((String) -> Unit)? = null
    var onSeeAllPopularClicked: ((String) -> Unit)? = null
    var onSeeAllNowPlayingClicked: ((String) -> Unit)? = null
    var onSeeAllUpcomingClicked: ((String) -> Unit)? = null

    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    private val DELAY_MS: Long = 5000 // time between image scroll

    private val data = ArrayList<BaseData>()

    fun addData(response: BaseData) {
        data.add(response)

        val d = data.sortedBy {
            it.getType()
        }

        this.data.clear()
        this.data.addAll(d)
        notifyDataSetChanged()
    }

    fun setData(response: ArrayList<BaseData>) {
        data.clear()
        data.addAll(response)
        notifyItemInserted(data.size - 1)
        notifyDataSetChanged()
    }


    fun clearData() {
        data.clear()
        notifyItemRangeRemoved(0, data.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewHolder: RecyclerView.ViewHolder = when (viewType) {
            BaseData.TYPE_SLIDER -> {
                TYPE_SLIDER_VH(
                    SliderItemGroupBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            BaseData.TYPE_TOP_RATED -> {
                TYPE_TOP_RATED_VH(
                    TopRatedItemGroupBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            BaseData.TYPE_POPULAR -> {
                TYPE_POPULAR_VH(
                    PopularItemGroupBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            BaseData.TYPE_UPCOMING -> {
                TYPE_UPCOMING_VH(
                    UpcomingItemGroupBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                TYPE_NOW_PLAYING_VH(
                    NowPlayingItemGroupBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            BaseData.TYPE_SLIDER -> {
                (holder as TYPE_SLIDER_VH)
                    .bindData((data[position] as SliderResponse).results)

                stopSliderDuration()

                try {
                    timerTask = object : TimerTask() {
                        override fun run() {
                            holder.slider.post {
                                holder.slider.currentItem = (holder.slider.currentItem + 1) % 20
                            }
                        }
                    }
                    timer = Timer()
                    timer?.schedule(timerTask, DELAY_MS, DELAY_MS)
                } catch (e: IllegalStateException) {
                    timer?.cancel()
                }
            }

            BaseData.TYPE_TOP_RATED -> {
                (holder as TYPE_TOP_RATED_VH)
                    .bindData((data[position] as TopRatedResponse).results)
            }

            BaseData.TYPE_POPULAR -> {
                (holder as TYPE_POPULAR_VH)
                    .bindData((data[position] as PopularResponse).results)
            }
            BaseData.TYPE_UPCOMING -> {
                (holder as TYPE_UPCOMING_VH)
                    .bindData((data[position] as UpcomingResponse).results)
            }

            else -> {
                (holder as TYPE_NOW_PLAYING_VH)
                    .bindData((data[position] as NowPlayingResponse).results)
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }

    inner class TYPE_SLIDER_VH(val binding: SliderItemGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val slider = binding.sliderHome

        private val adapter = SliderAdapter()

        fun bindData(movieList: List<MovieData>) {
            binding.sliderHome.adapter = adapter
            binding.springDotsIndicator.attachTo(binding.sliderHome)

            adapter.setData(movieList)


            adapter.onItemClicked = {
                onSliderClicked!!.invoke(it)
            }
        }

    }


    inner class TYPE_POPULAR_VH(val binding: PopularItemGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter = PopularAdapter()

        fun bindData(movieList: List<MovieData>) {

            binding.childList1.adapter = adapter

            var layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.childList1.layoutManager = layoutManager


            adapter.setData(movieList)

//            binding.movieType.text = "Now Playing Movies"

            adapter.onItemClicked = {
                onPopularClicked!!.invoke(it)
            }

            binding.allButton.setOnClickListener {
                onSeeAllPopularClicked!!.invoke("Popular Movies")
            }
        }

    }

    inner class TYPE_NOW_PLAYING_VH(val binding: NowPlayingItemGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter = NowPlayingAdapter()

        fun bindData(movieList: List<MovieData>) {
            binding.childList.adapter = adapter

            var layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.childList.layoutManager = layoutManager

            adapter.setData(movieList)

//            binding.movieType.text = "Now Playing Movies"

            adapter.onItemClicked = {
                onNowPlayingClicked!!.invoke(it)
            }

            binding.allButton.setOnClickListener {
                onSeeAllNowPlayingClicked!!.invoke("Now Playing")
            }
        }
    }

    inner class TYPE_UPCOMING_VH(val binding: UpcomingItemGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter = UpcomingAdapter()

        fun bindData(movieList: List<MovieData>) {
            binding.childList.adapter = adapter

            var layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.childList.layoutManager = layoutManager

            adapter.setData(movieList)

//            binding.movieType.text = "Now Playing Movies"

            adapter.onItemClicked = {
                onUpcomingClicked!!.invoke(it)
            }

            binding.allButton.setOnClickListener {
                onSeeAllUpcomingClicked!!.invoke("Upcoming Movies")
            }
        }
    }

    inner class TYPE_TOP_RATED_VH(val binding: TopRatedItemGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter = TopRatedAdapter()

        fun bindData(movieList: List<MovieData>) {

            binding.childList4.adapter = adapter
            var gridLayoutManager =
                GridLayoutManager(binding.root.context, 4, GridLayoutManager.HORIZONTAL, false)
            binding.childList4.layoutManager = gridLayoutManager


            adapter.setData(movieList)

//            binding.movieType.text = "Now Playing Movies"

            adapter.onItemClicked = {
                onTopRatedClicked!!.invoke(it)
            }

            binding.allButton.setOnClickListener {
                onSeeAllTopRatedClicked!!.invoke("Top Rated Movies")
            }
        }
    }

    fun stopSliderDuration() {
        timerTask?.cancel()
        timer?.cancel()
    }
}