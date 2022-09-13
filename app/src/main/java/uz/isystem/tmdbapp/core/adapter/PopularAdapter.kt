package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.MovieData
import uz.isystem.tmdbapp.databinding.ItemPopularBinding

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    private val data = ArrayList<MovieData>()

    var onItemClicked: ((MovieData) -> Unit)? = null

    fun setData(data: List<MovieData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }

    inner class ViewHolder(private val binding: ItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(movieData: MovieData) {

            Glide.with(binding.itemImage)
                .load("https://image.tmdb.org/t/p/w500" + movieData.backdropPath)
                .into(binding.itemImage)
            binding.itemTitle.text = movieData.title
            binding.itemSize.text = "popularity ${movieData.popularity}"

            binding.root.setOnClickListener {
                onItemClicked!!.invoke(movieData)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPopularBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}