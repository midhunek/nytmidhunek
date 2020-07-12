package test.app.nyt.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import test.app.nyt.R
import test.app.nyt.data.model.nytdata.Result
import test.app.nyt.databinding.NewsDataBinding

class NewsListAdapter (private var clickListener :  (Result, Int) -> Unit) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    companion object {

        @JvmStatic
        @BindingAdapter("media")
        fun loadImage(
            imageView: ImageView,
            imageURL: String?
        ) {
            Glide.with(imageView.context)
                .setDefaultRequestOptions(
                    RequestOptions()
                        .override(300, 300)
                        .circleCrop()
                        .placeholder(R.drawable.ic_launcher_background)
                )
                .load(imageURL)
                .into(imageView)
        }
    }

    private var result = mutableListOf<Result>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsDataBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = result.size


    inner class ViewHolder(private val binding: NewsDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Result,
            clickListener: (Result, Int) -> Unit
        ) {
            binding.news = item

           if (item.media.isNotEmpty()){
               if (item.media[0].mediametadata.isNotEmpty()){
                   binding.imageUrl = item.media[0].mediametadata[0].url
               }
           }

            itemView.setOnClickListener {
                clickListener (item,adapterPosition)
            }

            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind(result[position],clickListener)


    fun addData(list: List<Result>) {
        result.clear()
        result.addAll(list)
        notifyDataSetChanged()
    }
}