package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.models.response.main.home.cast.Cast
import uz.isystem.tmdbapp.databinding.CelebritiesItemChildBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private val data = ArrayList<Cast>()

    var onItemClicked: ((Cast) -> Unit)? = null

    fun setData(data: List<Cast>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }

    inner class ViewHolder(private val binding: CelebritiesItemChildBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(cast: Cast) {

            if (cast.profilePath == null) {
                Glide.with(binding.itemImage)
                    .load(R.drawable.not_found)
                    .into(binding.itemImage)
            } else {

                Glide.with(binding.itemImage)
                    .load("https://image.tmdb.org/t/p/w500" + cast.profilePath)
                    .into(binding.itemImage)
            }

            binding.itemTitle.text = cast.name
            binding.itemSize.text = cast.character

            binding.root.setOnClickListener {
                onItemClicked!!.invoke(cast)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CelebritiesItemChildBinding.inflate(
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