package test.app.nyt.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import test.app.nyt.R
import test.app.nyt.data.model.nytdata.Result
import test.app.nyt.ui.main.adapter.NewsListAdapter
import test.app.nyt.ui.main.viewmodel.NewsListViewModel
import test.app.nyt.utils.Constants
import test.app.nyt.utils.Status


@AndroidEntryPoint
class NewsListActivity : AppCompatActivity() {

    private val newsListViewModel : NewsListViewModel by viewModels()
    private lateinit var adapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsListAdapter{resultData,position -> itemClickListener (resultData,position)}
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun itemClickListener(resultData: Result, position: Int) {

        val nextInt = Intent(this, NewsDetailActivity::class.java)
        nextInt.putExtra("SELECTED_NEWS", resultData)
        startActivity(nextInt)

    }

    private fun setupObserver() {
        newsListViewModel.apiStatus.observe(this, Observer {
            when (it) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
            }
        })

        newsListViewModel.nytData.observe(this, Observer {
            it?.results?.let {results -> renderList(results)
            }
        })

        newsListViewModel.fetchNews("all-sections","7",Constants.NYT_API_KEY)
    }

    private fun renderList(users: List<Result>) {
        adapter.addData(users)
    }
}
