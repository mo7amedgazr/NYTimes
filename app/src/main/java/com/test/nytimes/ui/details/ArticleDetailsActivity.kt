package com.test.nytimes.ui.details

import android.os.Bundle
import com.test.nytimes.R
import com.test.nytimes.ui.base.BaseActivity

class ArticleDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
    }
}