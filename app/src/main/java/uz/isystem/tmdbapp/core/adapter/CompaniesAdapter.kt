package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails.ProductionCompany
import uz.isystem.tmdbapp.databinding.CompaniesItemChildBinding

class CompaniesAdapter : RecyclerView.Adapter<CompaniesAdapter.ViewHolder>() {

    private val data = ArrayList<ProductionCompany>()

    fun setData(data: List<ProductionCompany>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }

    inner class ViewHolder(private val binding: CompaniesItemChildBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(companies: ProductionCompany) {

            if (companies.logoPath == null) {
                Glide.with(binding.itemImage)
                    .load(R.drawable.not_found)
                    .into(binding.itemImage)

                binding.itemImage.scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                binding.itemImage.setPadding(4, 0, 4, 0)
                Glide.with(binding.itemImage)
                    .load("https://image.tmdb.org/t/p/w500" + companies.logoPath)
                    .into(binding.itemImage)
            }

            binding.itemTitle.text = companies.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CompaniesItemChildBinding.inflate(
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