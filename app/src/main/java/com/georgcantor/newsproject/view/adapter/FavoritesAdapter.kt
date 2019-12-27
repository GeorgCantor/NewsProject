package com.georgcantor.newsproject.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.model.local.Article
import com.georgcantor.newsproject.util.loadImage
import kotlinx.android.synthetic.main.news_item.view.*

class FavoritesAdapter(
    private val context: Context,
    articles: MutableList<Article>,
    private val clickListener: (Article) -> Unit,
    private val removeClickListener: (Article) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private val articles: MutableList<Article> = ArrayList()

    init {
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder =
        FavoritesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, null)
        )

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.titleTextView.text = articles[position].title
        holder.descriptionTextView.text = articles[position].description
        holder.saveButton.text = "Remove"
        articles[position].urlToImage?.let { context.loadImage(it, holder.articleImageView) }

        holder.itemView.setOnClickListener {
            clickListener(articles[position])
        }

        holder.saveButton.setOnClickListener {
            removeClickListener(articles[position])
        }
    }

    class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.titleTextView
        val descriptionTextView: TextView = view.descriptionTextView
        val articleImageView: ImageView = view.newsImageView
        val saveButton: Button = view.saveButton
    }

}