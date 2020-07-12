package test.app.nyt.ui.main.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import test.app.nyt.R
import test.app.nyt.data.model.nytdata.Result
import test.app.nyt.databinding.ActivityDetailBinding


@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {

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
