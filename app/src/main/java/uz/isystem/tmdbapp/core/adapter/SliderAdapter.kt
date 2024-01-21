package uz.isystem.tmdbapp.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.MovieData
import uz.isystem.tmdbapp.databinding.ItemSliderBinding

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    private val data = ArrayList<MovieData>()
    var onItemClicked: ((MovieData) -> Unit)? = null

    var mycon: Context? = null

    fun setData(data: List<MovieData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }

    inner class ViewHolder(private val binding: ItemSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(movieData: MovieData) {

            if (movieData.posterPath == null) {
                Glide.with(binding.sliderItemImage)
                    .load(R.drawable.not_found)
                    .into(binding.sliderItemImage)
            } else {
                Glide.with(binding.sliderItemImage)
                    .load("https://image.tmdb.org/t/p/w500" + movieData.backdropPath)
                    .into(binding.sliderItemImage)
            }
            binding.sliderItemName.text = movieData.title
            binding.sliderItemGenre.text =
                "${String.format(mycon!!.getString(R.string.popularity))} ${movieData.popularity}"



            binding.root.setOnClickListener {
                onItemClicked!!.invoke(movieData)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mycon = parent.context
        return ViewHolder(
            ItemSliderBinding.inflate(
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