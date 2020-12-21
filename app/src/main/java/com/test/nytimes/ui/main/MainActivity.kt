package com.test.nytimes.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.test.nytimes.R
import com.test.nytimes.data.models.ResultsItem
import com.test.nytimes.data.network.response.MostViewedResponse
import com.test.nytimes.databinding.ActivityMainBinding
import com.test.nytimes.ui.base.BaseActivity
import com.test.nytimes.ui.details.ArticleDetailsActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class), KodeinAware {

    override val kodein by closestKodein()
    private val mainViewModelFactory: MainViewModelFactory by instance<MainViewModelFactory>()

    private lateinit var binding: ActivityMainBinding
    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProviders.of(this, mainViewModelFactory)
                .get(MainViewModel::class.java)

        viewModel.showLoading.observe(this, Observer { showLoading ->
            binding.progressbar.visibility = if (showLoading) View.VISIBLE else View.GONE
        })

        (viewModel as MainViewModel).mostViewedLiveData.observe(this, Observer {
            onGetArticles(it)
        })

        (viewModel as MainViewModel).getMostViewed()
    }

    private fun onGetArticles(it: MostViewedResponse) {
        it.results?.let {
            articlesAdapter = ArticlesAdapter(it as ArrayList<ResultsItem>)
            binding.rvArticles.adapter = articlesAdapter

            articlesAdapter.onItemClick = { item ->

                ArticleDetailsActivity.start(this,item)

            }
        }
    }


}