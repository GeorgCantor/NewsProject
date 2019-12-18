package com.georgcantor.newsproject.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.base.BaseFragment
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.model.remote.NetworkState
import com.georgcantor.newsproject.view.adapter.NewsAdapter
import com.georgcantor.newsproject.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search_results.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class SearchFragment : BaseFragment(), NewsAdapter.OnClickListener {

    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter
    private lateinit var manager: InputMethodManager

    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter(this)
        searchRecyclerView.adapter = adapter

        setupRequest()
    }

    override fun onClickRetry() {
    }

    override fun onItemClick(article: Article) {
    }

    override fun onListUpdated(size: Int, networkState: NetworkState?) {
    }

    private fun setupRequest() {
        searchView.requestFocus()
        searchView.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                viewModel = getViewModel {
                    parametersOf(searchView.text.toString().trim { it <= ' ' })
                }
                viewModel.getNews()
                getNews()

                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun getNews() {
        viewModel.networkState?.observe(viewLifecycleOwner, Observer(adapter::updateNetworkState))
        viewModel.news.observe(viewLifecycleOwner, Observer(adapter::submitList))
    }

    private fun hideKeyboard() {
        manager.hideSoftInputFromWindow(requireActivity().window.decorView.rootView.windowToken, 0)
    }

}