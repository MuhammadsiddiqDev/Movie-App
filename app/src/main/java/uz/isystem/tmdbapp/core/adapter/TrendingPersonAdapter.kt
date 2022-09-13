package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.core.models.response.main.home.cast.Cast
import uz.isystem.tmdbapp.databinding.ItemCelebritiesTrendingBinding

class TrendingPersonAdapter : RecyclerView.Adapter<TrendingPersonAdapter.ViewHolder>() {

    private val data = ArrayList<Cast>()

    var onItemClicked: ((Cast) -> Unit)? = null

    fun setData(data: List<Cast>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }

    inner class ViewHolder(private val binding: ItemCelebritiesTrendingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(trendingData: Cast) {
            Glide.with(binding.imageGroup1)
                .load("https://image.tmdb.org/t/p/w500" + trendingData.profilePath)
                .into(binding.itemImage1)
            binding.itemTitle.text = trendingData.name
            binding.itemSize1.text = "popularity ${trendingData.popularity}"

            binding.root.setOnClickListener {
                onItemClicked!!.invoke(trendingData)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCelebritiesTrendingBinding.inflate(
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