package com.georgcantor.newsproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.model.local.Article
import com.georgcantor.newsproject.util.loadImage
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(
    news: List<Article>,
    private val clickListener: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var news: MutableList<Article> = ArrayList()

    init {
        this.news.addAll(news)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item, null)
        val viewHolder = NewsViewHolder(itemView)

        itemView.setOnClickListener {
            clickListener(news[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        holder.title.text = news[position].title
        holder.description.text = news[position].description
//        holder.date.text = news[position].publishedAt

        holder.articleImageView.context.loadImage(
            news[position].imageUrl ?: "",
            holder.articleImageView,
            null
        )
    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleImageView: ImageView = view.newsImageView
//        val title: TextView = view.titleTextView
        val description: TextView = view.descriptionTextView
//        val date: TextView = view.dateTextView
    }

}