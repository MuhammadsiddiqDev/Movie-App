package uz.isystem.tmdbapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails.ProductionCountry
import uz.isystem.tmdbapp.databinding.CountryItemChildBinding

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private val data = ArrayList<ProductionCountry>()

    fun setData(data: List<ProductionCountry>) {
        this.data.clear()
        this.data.addAll(data)
        notifyItemRangeInserted(this.data.size - data.size, data.size)
    }

    inner class ViewHolder(private val binding: CountryItemChildBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binData(country: ProductionCountry) {

            binding.itemText.text = country.iso31661
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CountryItemChildBinding.inflate(
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