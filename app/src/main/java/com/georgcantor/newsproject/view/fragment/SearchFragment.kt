package com.georgcantor.newsproject.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.base.BaseFragment
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.model.remote.NetworkState
import com.georgcantor.newsproject.view.adapter.NewsAdapter
import com.georgcantor.newsproject.viewmodel.NewsViewModel
import com.georgcantor.newsproject.viewmodel.ShareDataViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search_results.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class SearchFragment : BaseFragment(), NewsAdapter.OnClickListener {

    private lateinit var viewModel: NewsViewModel
    private lateinit var shareDataViewModel: ShareDataViewModel
    private lateinit var adapter: NewsAdapter
    private lateinit var manager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shareDataViewModel = getSharedViewModel { parametersOf() }
    }

    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkQuery()
        manager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter(this)
        searchRecyclerView.adapter = adapter

        setupRequest()
    }

    override fun onClickRetry() {
        viewModel.retry()
    }

    override fun onItemClick(article: Article) {
        shareDataViewModel.setArticle(article)
        view?.let { Navigation.findNavController(it).navigate(R.id.articleFragment) }
    }

    override fun onListUpdated(size: Int, networkState: NetworkState?) {
    }

    private fun setupRequest() {
        val navOption = NavOptions.Builder().setLaunchSingleTop(true).build()

        searchView.requestFocus()
        searchView.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                shareDataViewModel.setQuery(searchView.text.toString().trim { it <= ' ' })

                view?.let {
                    Navigation.findNavController(it).navigate(R.id.searchFragment, null, navOption)
                }
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun checkQuery() {
        shareDataViewModel.query.observe(viewLifecycleOwner, Observer { query ->
            if (query.isNullOrEmpty()) showKeyboard(searchView)
            view?.let {
                viewModel = getViewModel { parametersOf(query) }
                viewModel.getNews()
                getNews()
            }
        })
    }

    private fun getNews() {
        viewModel.networkState?.observe(viewLifecycleOwner, Observer(adapter::updateNetworkState))
        viewModel.news.observe(viewLifecycleOwner, Observer(adapter::submitList))
    }

    private fun showKeyboard(view: View) {
        view.requestFocus()
        manager.showSoftInput(view, 0)
    }

    private fun hideKeyboard() {
        manager.hideSoftInputFromWindow(requireActivity().window.decorView.rootView.windowToken, 0)
    }

}