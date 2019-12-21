package com.georgcantor.newsproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.georgcantor.newsproject.R
import kotlinx.android.synthetic.main.tag_item.view.*
import java.util.*

class TagsAdapter(
    tags: MutableList<String>,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<TagsAdapter.TagViewHolder>() {

    private val tags: MutableList<String> = ArrayList()

    init {
        this.tags.addAll(tags)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tag_item, null)

        return TagViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        this.tags.let {
            val tag = it[position]
            holder.tagTextView.text = tag
            holder.tagTextView.setOnClickListener {
                clickListener(tag)
            }
        }
    }

    override fun getItemCount(): Int = tags.size

    class TagViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tagTextView: TextView = view.tagTextView
    }

}