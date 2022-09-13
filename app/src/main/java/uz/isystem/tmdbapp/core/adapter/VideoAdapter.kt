package uz.isystem.tmdbapp.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieTrailer.VideoData
import uz.isystem.tmdbapp.databinding.ItemTrailerBinding

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private var context: Context? = null

    private val data = ArrayList<VideoData>()

    var onItemClicked: ((VideoData) -> Unit)? = null

    fun setData(data: List<VideoData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }

    inner class ViewHolder(private val binding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(movieData: VideoData) {

            context = context

            Glide.with(binding.itemImage)
                .load("https://img.youtube.com/vi/" + movieData.key + "/hqdefault.jpg")
                .into(binding.itemImage)

            binding.trailerName.text = movieData.name

            binding.imageGroup.setOnClickListener {
                onItemClicked!!.invoke(movieData)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTrailerBinding.inflate(
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