package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.MovieData
import uz.isystem.tmdbapp.databinding.ItemSeeAllBinding

class SeeAllMoviesAdapter : RecyclerView.Adapter<SeeAllMoviesAdapter.ViewHolder>() {

    private var data = ArrayList<MovieData>()

    var onItemClicked: ((MovieData) -> Unit)? = null

    fun setData(data: List<MovieData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }


    inner class ViewHolder(val binding: ItemSeeAllBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(movieData: MovieData) {
            binding.itemTitle.text = movieData.title
            binding.itemSize.text = "Release date: ${movieData.releaseDate}"

            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500" + movieData.posterPath)
                .into(binding.itemImage)

            binding.root.setOnClickListener {
                onItemClicked!!.invoke(movieData)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSeeAllBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}