package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.core.models.response.main.home.cast.Cast
import uz.isystem.tmdbapp.databinding.ItemSeeAllCelebritiesBinding

class SeeAllActorAdapter : RecyclerView.Adapter<SeeAllActorAdapter.ViewHolder>() {

    private var data = ArrayList<Cast>()

    var onItemClicked: ((Cast) -> Unit)? = null

    fun setData(data: List<Cast>) {
        this.data.addAll(data)
        notifyItemRangeChanged(data.size, data.size - 1)
    }

    inner class ViewHolder(val binding: ItemSeeAllCelebritiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(movieData: Cast) {
            binding.itemTitle.text = movieData.name
            binding.itemSize.text = "${movieData.knownForDepartment}"
            binding.itemDescription.text = "popularity ${movieData.popularity}"

            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500" + movieData.profilePath)
                .into(binding.itemImage)

            binding.root.setOnClickListener {
                onItemClicked!!.invoke(movieData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSeeAllCelebritiesBinding.inflate(
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