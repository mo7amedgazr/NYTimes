package com.test.nytimes.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.test.nytimes.data.models.ResultsItem
import com.test.nytimes.databinding.ActivityArticleDetailsBinding
import com.test.nytimes.ui.base.BaseActivity
import com.test.nytimes.utils.INTENT_ARTICLE_ITEM

class ArticleDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailsBinding

    companion object {
        fun start(context: Context, resultsItem: ResultsItem) {
            val intent = Intent(context, ArticleDetailsActivity::class.java)
            intent.putExtra(INTENT_ARTICLE_ITEM, resultsItem)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getIntentData()

    }

    private fun getIntentData() {
        val resultsItem = intent.getSerializableExtra(INTENT_ARTICLE_ITEM) as ResultsItem
        binding.tvTitle.text = resultsItem.title
        binding.tvAbstract.text = resultsItem.abstract
        binding.tvType.text = resultsItem.type
        binding.tvByline.text = resultsItem.byline
        binding.tvSection.text = resultsItem.section
        binding.tvDate.text = resultsItem.publishedDate
        binding.tvCaption.text = resultsItem.media?.get(0)?.caption
        resultsItem.media?.get(0)?.mediaMetadata?.get(0)?.url?.let {
            Glide.with(this).load(it).into(binding.ivImage)
        }
    }
}