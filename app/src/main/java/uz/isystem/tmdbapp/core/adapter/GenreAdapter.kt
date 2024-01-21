package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails.Genre
import uz.isystem.tmdbapp.databinding.GenreItemChildBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private val data = ArrayList<Genre>()

    fun setData(data: List<Genre>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }

    inner class ViewHolder(private val binding: GenreItemChildBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(genre: Genre) {

            binding.itemText.text = genre.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GenreItemChildBinding.inflate(
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