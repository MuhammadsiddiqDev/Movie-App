package uz.isystem.tmdbapp.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.models.response.main.home.cast.Cast
import uz.isystem.tmdbapp.databinding.ItemCelebritiesTrendingBinding

class TrendingPersonAdapter : RecyclerView.Adapter<TrendingPersonAdapter.ViewHolder>() {

    private val data = ArrayList<Cast>()

    var onItemClicked: ((Cast) -> Unit)? = null

    var mycon: Context? = null

    fun setData(data: List<Cast>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }

    inner class ViewHolder(private val binding: ItemCelebritiesTrendingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(trendingData: Cast) {

            if (trendingData.profilePath == null) {
                Glide.with(binding.itemImage1)
                    .load(R.drawable.not_found)
                    .into(binding.itemImage1)
            } else {
                Glide.with(binding.imageGroup1)
                    .load("https://image.tmdb.org/t/p/w500" + trendingData.profilePath)
                    .into(binding.itemImage1)
            }
            binding.itemTitle.text = trendingData.name
            binding.itemSize1.text =
                "${String.format(mycon!!.getString(R.string.popularity))} ${trendingData.popularity}"

            binding.root.setOnClickListener {
                onItemClicked!!.invoke(trendingData)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        mycon = parent.context
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