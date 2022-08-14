package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.core.models.response.main.home.search.SearchData
import uz.isystem.tmdbapp.databinding.ItemSeeAllBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var data = ArrayList<SearchData>()

    var onItemClicked: ((SearchData) -> Unit)? = null

    fun setData(data: List<SearchData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemSeeAllBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: SearchData) {

            binding.itemTitle.text = result.title
            binding.itemSize.text = "popularity ${result.popularity}"

            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500" + result.posterPath)
                .into(binding.itemImage)

            binding.root.setOnClickListener {
                onItemClicked!!.invoke(result)
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