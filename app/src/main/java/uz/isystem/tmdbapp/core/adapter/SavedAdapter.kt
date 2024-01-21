package uz.isystem.tmdbapp.core.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.models.response.main.home.watch.WatchResult
import uz.isystem.tmdbapp.databinding.ItemSeeAllBinding


class SavedAdapter : RecyclerView.Adapter<SavedAdapter.ViewHolder>() {

    var data = ArrayList<WatchResult>()


    var itemClicked: ((WatchResult) -> Unit)? = null

    var mycon: Context? = null

    private var pager: MyPage? = null

    fun setData(data: List<WatchResult>) {
        Handler(Looper.getMainLooper()).post {
            val previous = this.data.size
            this.data.addAll(data)
            notifyItemRangeInserted(previous, this.data.size)
        }
    }

    inner class ViewHolder(val binding: ItemSeeAllBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: WatchResult) {

            binding.itemTitle.text = result.title
            binding.itemSize.text =
                "${String.format(mycon!!.getString(R.string.popularity))} ${result.popularity}"

            if (result.posterPath == null) {
                Glide.with(binding.itemImage)
                    .load(R.drawable.not_found)
                    .into(binding.itemImage)
            } else {
                Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500" + result.posterPath)
                    .into(binding.itemImage)
            }

            binding.root.setOnClickListener {
                itemClicked!!.invoke(result)
            }

        }
    }

    fun clearData() {
        data.clear()
    }

    fun setListener(pager: MyPage) {
        this.pager = pager
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mycon = parent.context
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

        if (position + 1 >= data.size) {
            pager?.loadMorePage()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface MyPage {
        fun loadMorePage()
    }
}