package uz.isystem.tmdbapp.core.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.models.response.main.home.search.actior.ActorResult
import uz.isystem.tmdbapp.databinding.ItemSeeAllCelebritiesBinding

class SearchActorAdapter : RecyclerView.Adapter<SearchActorAdapter.ViewHolder>() {

    private var data = ArrayList<ActorResult>()

    var onItemClicked: ((ActorResult) -> Unit)? = null

    var mycon: Context? = null

    private var pager: MyPage? = null

    fun setData(data: List<ActorResult>) {
        Handler(Looper.getMainLooper()).post {
            val previous = this.data.size
            this.data.addAll(data)
            notifyItemRangeInserted(previous, this.data.size)
        }
    }


    inner class ViewHolder(val binding: ItemSeeAllCelebritiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(actorData: ActorResult) {
            binding.itemTitle.text = actorData.name
            binding.itemSize.text = "${actorData.knownForDepartment}"
            binding.itemDescription.text =
                "${String.format(mycon!!.getString(R.string.popularity))} ${actorData.popularity}"

            if (actorData.profilePath == null) {
                Glide.with(binding.itemImage)
                    .load(R.drawable.not_found)
                    .into(binding.itemImage)
            } else {
                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500" + actorData.profilePath)
                    .into(binding.itemImage)
            }

            binding.root.setOnClickListener {
                onItemClicked!!.invoke(actorData)
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
            ItemSeeAllCelebritiesBinding.inflate(
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