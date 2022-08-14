package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.MovieData
import uz.isystem.tmdbapp.databinding.ItemNowPlayingBinding

class SimilarMoviesAdapter : RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder>() {

    val data = ArrayList<MovieData>()

    var onItemClicked: ((MovieData) -> Unit)? = null


    fun setData(data: List<MovieData>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemNowPlayingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(similarMovieData: MovieData) {
            Glide.with(binding.imageGroup)
                .load("https://image.tmdb.org/t/p/w500" + similarMovieData.posterPath)
                .into(binding.itemImage)

            binding.itemTitle.text = similarMovieData.title
            binding.itemSize.text = "popularity ${similarMovieData.popularity}"

            binding.root.setOnClickListener {
                onItemClicked?.invoke(similarMovieData)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNowPlayingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
        /*holder.itemView.setOnClickListener {
            onItemClicked!!.invoke(data[position])
        }*/
    }

    override fun getItemCount(): Int {
        return data.size
    }
}