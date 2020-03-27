package com.georgcantor.newsproject.view.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.util.loadImage
import com.georgcantor.newsproject.view.adapter.NewsAdapter
import kotlinx.android.synthetic.main.news_item.view.*

class NewsViewHolder(private val parent: View) : RecyclerView.ViewHolder(parent) {

    fun bindTo(
        article: Article?,
        listener: NewsAdapter.OnClickListener
    ) {
        article?.let { art ->
            itemView.titleTextView.text = art.title
            itemView.descriptionTextView.text = art.description
            parent.context.loadImage(art.urlToImage ?: "", itemView.newsImageView)

            itemView.setOnClickListener {
                listener.onItemClick(art)
            }

            itemView.saveButton.setOnClickListener {
                listener.onSaveClick(art)
            }
        }
    }
}