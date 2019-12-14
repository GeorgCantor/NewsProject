package com.georgcantor.newsproject.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.util.loadImage
import kotlinx.android.synthetic.main.news_item.view.*

class NewsViewHolder(private val parent: View) : RecyclerView.ViewHolder(parent) {

    fun bindTo(article: Article?) {
        article?.let {
            itemView.titleTextView.text = it.title
            itemView.descriptionTextView.text = it.description
            parent.context.loadImage(it.urlToImage ?: "", itemView.newsImageView)
        }
    }

}