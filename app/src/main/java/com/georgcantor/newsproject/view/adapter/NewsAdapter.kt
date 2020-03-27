package com.georgcantor.newsproject.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.model.remote.NetworkState
import com.georgcantor.newsproject.view.adapter.holder.NetworkStateViewHolder
import com.georgcantor.newsproject.view.adapter.holder.NewsViewHolder

class NewsAdapter(private val listener: OnClickListener) :
    PagedListAdapter<Article, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }

    private var networkState: NetworkState? = null

    interface OnClickListener {

        fun onClickRetry()

        fun onItemClick(article: Article)

        fun onSaveClick(article: Article)

        fun onListUpdated(size: Int, networkState: NetworkState?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.news_item -> NewsViewHolder(
                view
            )
            R.layout.item_network_state -> NetworkStateViewHolder(
                view
            )
            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.news_item -> (holder as NewsViewHolder).bindTo(
                getItem(position),
                listener
            )
            R.layout.item_network_state -> (holder as NetworkStateViewHolder).bindTo(
                networkState,
                listener
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.news_item
        }
    }

    override fun getItemCount(): Int {
        this.listener.onListUpdated(super.getItemCount(), this.networkState)
        return super.getItemCount()
    }

    fun updateNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()

        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.SUCCESS
}