package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.core.models.response.main.home.cast.Cast
import uz.isystem.tmdbapp.databinding.ItemCelebritesBinding

class CelebritiesAdapter : RecyclerView.Adapter<CelebritiesAdapter.ViewHolder>() {

    private val data = ArrayList<Cast>()

    var onItemClicked: ((Cast) -> Unit)? = null

    fun setData(data: List<Cast>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCelebritesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(movieData: Cast) {
            Glide.with(binding.itemImage)
                .load("https://image.tmdb.org/t/p/w500" + movieData.profilePath)
                .into(binding.itemImage)
            binding.itemTitle.text = movieData.name
            binding.itemSize.text = "popularity ${movieData.popularity}"

            binding.root.setOnClickListener {
                onItemClicked!!.invoke(movieData)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCelebritesBinding.inflate(
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