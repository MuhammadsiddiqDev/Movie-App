package uz.isystem.tmdbapp.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.models.response.main.home.cast.Cast
import uz.isystem.tmdbapp.databinding.ItemCelebritesBinding

class CelebritiesAdapter : RecyclerView.Adapter<CelebritiesAdapter.ViewHolder>() {

    private val data = ArrayList<Cast>()

    var mycon: Context? = null

    var onItemClicked: ((Cast) -> Unit)? = null

    fun setData(data: List<Cast>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }

    inner class ViewHolder(private val binding: ItemCelebritesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(movieData: Cast) {

            if (movieData.profilePath == null) {
                Glide.with(binding.itemImage)
                    .load(R.drawable.not_found)
                    .into(binding.itemImage)
            } else {
                Glide.with(binding.itemImage)
                    .load("https://image.tmdb.org/t/p/w500" + movieData.profilePath)
                    .into(binding.itemImage)
            }
            binding.itemTitle.text = movieData.name
            binding.itemSize.text =
                "${String.format(mycon!!.resources.getString(R.string.popularity))} ${movieData.popularity}"


            binding.root.setOnClickListener {
                onItemClicked!!.invoke(movieData)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mycon = parent.context
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