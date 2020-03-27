package com.georgcantor.newsproject.view.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.georgcantor.newsproject.model.remote.NetworkState
import com.georgcantor.newsproject.util.gone
import com.georgcantor.newsproject.util.visible
import com.georgcantor.newsproject.view.adapter.NewsAdapter
import kotlinx.android.synthetic.main.item_network_state.view.*

class NetworkStateViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bindTo(
        networkState: NetworkState?,
        listener: NewsAdapter.OnClickListener
    ) {
        hideViews()
        setVisibility(networkState)

        itemView.retryButton.setOnClickListener {
            listener.onClickRetry()
        }
    }

    private fun hideViews() {
        itemView.progressBar.gone()
        itemView.retryButton.gone()
        itemView.errorTextView.gone()
    }

    private fun setVisibility(networkState: NetworkState?) {
        when (networkState) {
            NetworkState.RUNNING -> itemView.progressBar.visible()
            NetworkState.SUCCESS -> {
            }
            NetworkState.FAILED -> {
                itemView.retryButton.visible()
                itemView.errorTextView.visible()
            }
        }
    }
}