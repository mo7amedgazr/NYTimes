package com.test.nytimes.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.nytimes.data.models.ResultsItem
import com.test.nytimes.databinding.ItemArticleBinding


class ArticlesAdapter(private val items: ArrayList<ResultsItem>) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    var onItemClick: ((ResultsItem) -> Unit)? = null

    class ArticlesViewHolder(v: ItemArticleBinding) : RecyclerView.ViewHolder(v.root) {
        var view: ItemArticleBinding = v

        fun bindData(resultsItem: ResultsItem) {
            view.tvTitle.text = resultsItem.title
            view.tvDesc.text = resultsItem.byline
            view.tvType.text = resultsItem.type
            view.tvDate.text = resultsItem.publishedDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        return ArticlesViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bindData(items[position])
        holder.view.root.setOnClickListener {
            onItemClick?.invoke(items[position])
        }
    }

    override fun getItemCount() = items.size
}