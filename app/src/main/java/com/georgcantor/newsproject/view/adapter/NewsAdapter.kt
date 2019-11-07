package com.georgcantor.newsproject.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.model.data.Article
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var news: MutableList<Article> = ArrayList()

    fun setNews(news: List<Article>) {
        this.news.addAll(news)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item, null)

        return NewsViewHolder(itemView)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.articleTextView.text = news[position].description

        Glide.with(context)
            .load(news[position].url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.articleImageView)

    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleImageView: ImageView = view.newsImageView
        val articleTextView: TextView = view.newsTextView
    }

}