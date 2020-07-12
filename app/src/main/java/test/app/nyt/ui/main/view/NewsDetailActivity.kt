package test.app.nyt.ui.main.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import test.app.nyt.R
import test.app.nyt.data.model.nytdata.Result
import test.app.nyt.databinding.ActivityDetailBinding


@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {
    companion object {

        @JvmStatic
        @BindingAdapter("banner")
        fun loadImage(
            imageView: ImageView,
            imageURL: String?
        ) {
            Glide.with(imageView.context)
                .setDefaultRequestOptions(
                    RequestOptions()
                        .override(300, 500)
                )
                .load(imageURL)
                .into(imageView)
        }
    }
    var binding: ActivityDetailBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        binding  = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setupUI()
        setupObserver()

    }
    private fun setupUI() {

        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    }
    private fun setupObserver() {

        if (intent.hasExtra("SELECTED_NEWS")){
            var newsData = intent.getSerializableExtra("SELECTED_NEWS") as Result
            parseData(newsData)
        }


    }
    private fun parseData(result: Result) {
        binding?.news = result

        if (result?.media.isNotEmpty()){
            if (result.media[0].mediametadata.isNotEmpty()){

                var mediaList = result.media[0]?.mediametadata
                if (mediaList?.size>2){
                    binding?.imageUrl = mediaList[2]?.url
                }


            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
