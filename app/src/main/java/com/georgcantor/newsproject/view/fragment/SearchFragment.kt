package com.georgcantor.newsproject.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.georgcantor.newsproject.R
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

class SearchFragment : Fragment(), NewsAdapter.OnClickListener {

    private lateinit var viewModel: NewsViewModel
    private lateinit var shareDataViewModel: ShareDataViewModel
    private lateinit var adapter: NewsAdapter
    private lateinit var manager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shareDataViewModel = getSharedViewModel { parametersOf(null) }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(searchToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        manager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        showKeyboard(searchView)
        checkQuery()

        searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter(this)
        searchRecyclerView.adapter = adapter

        setupRequest()

        searchRefreshLayout.setOnRefreshListener {
            if (::viewModel.isInitialized) viewModel.retry()
            searchRefreshLayout.isRefreshing = false
        }
    }

    override fun onClickRetry() {
        viewModel.retry()
    }

    override fun onItemClick(article: Article) {
        shareDataViewModel.setArticle(article)
        view?.let { Navigation.findNavController(it).navigate(R.id.articleFragment) }
    }

    override fun onSaveClick(article: Article) {
        viewModel.saveNews(article)
    }

    override fun onListUpdated(size: Int, networkState: NetworkState?) {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                hideKeyboard()
                requireActivity().onBackPressed()
            }
        }
        return false
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
            if (query.isNotEmpty()) hideKeyboard()
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