package com.test.nytimes.ui.base

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.test.nytimes.ui.main.MainViewModel
import kotlin.reflect.KClass

open class BaseActivity<out ViewModelType : BaseViewModel>(clazz: KClass<ViewModelType>) : AppCompatActivity() {

    lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {

        viewModel.showError.observe(this, Observer { showError ->
            if (showError == null)
                return@Observer

            Log.d("showError","Came here!")

            // handle error here
            Toast.makeText(this, showError, Toast.LENGTH_LONG).show()
        })
    }

    /** Content to be implemented as needed */

}
