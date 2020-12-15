package com.test.nytimes.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.test.nytimes.R
import com.test.nytimes.databinding.ActivityMainBinding
import com.test.nytimes.ui.base.BaseActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : BaseActivity(),KodeinAware {


    override val kodein by closestKodein()
    private val mainViewModelFactory: MainViewModelFactory by instance<MainViewModelFactory>()
    private lateinit var viewModel: MainViewModel

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initViewModel()

    }

    private fun initViewModel() {
        viewModel =
            ViewModelProviders.of(this, mainViewModelFactory)
                .get(MainViewModel::class.java)

        viewModel.showLoading.observe(this, Observer { showLoading ->
            binding.progressbar.visibility = if (showLoading) View.VISIBLE else View.GONE
        })
        viewModel.showError.observe(this, Observer { showError ->
            if (showError == null)
                return@Observer

            // handle error here
            Toast.makeText(this,showError,Toast.LENGTH_LONG).show()
        })

        viewModel.getMostViewed()
    }


}