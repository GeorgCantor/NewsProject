package com.georgcantor.newsproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.model.data.Article
import com.georgcantor.newsproject.model.remote.NetworkState
import com.georgcantor.newsproject.view.adapter.NewsAdapter
import com.georgcantor.newsproject.viewmodel.NewsViewModel
import com.georgcantor.newsproject.viewmodel.ShareDataViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.item_network_state.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class NewsFragment : Fragment(), NewsAdapter.OnClickListener {

    companion object {
        private const val QUERY = "query"

        fun newInstance(query: String): NewsFragment {
            val bundle = Bundle()
            bundle.putString(QUERY, query)
            val fragment = NewsFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var viewModel: NewsViewModel
    private lateinit var shareDataViewModel: ShareDataViewModel
    private lateinit var adapter: NewsAdapter
    private var query: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(newsToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        query = arguments?.get(QUERY) as? String
        if (!query.isNullOrEmpty()) {
            //open in tabs
            appBar.visibility = View.GONE
            viewModel = getViewModel { parametersOf(query) }
            viewModel.getNews()
            getNews()
        } else {
            //open from drawer
            shareDataViewModel = getSharedViewModel { parametersOf(null) }
            shareDataViewModel.query.observe(viewLifecycleOwner, Observer {
                newsToolbar.title = it
                viewModel = getViewModel { parametersOf(it) }
                viewModel.getNews()
                getNews()
            })
        }

        refreshLayout.setOnRefreshListener {
            if (::viewModel.isInitialized) viewModel.retry()
            refreshLayout.isRefreshing = false
        }
    }

    override fun onClickRetry() {
        viewModel.retry()
    }

    override fun onItemClick(article: Article) {
        if (!::shareDataViewModel.isInitialized) {
            shareDataViewModel = getSharedViewModel { parametersOf() }
        }
        shareDataViewModel.setArticle(article)
        view?.let { Navigation.findNavController(it).navigate(R.id.articleFragment) }
    }

    override fun onSaveClick(article: Article) {
        viewModel.saveNews(article)
    }

    override fun onListUpdated(size: Int, networkState: NetworkState?) {
        setUpProgressBar(size, networkState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> requireActivity().onBackPressed()
        }
        return false
    }

    private fun getNews() {
        newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter(this)
        newsRecyclerView.adapter = adapter

        viewModel.networkState?.observe(viewLifecycleOwner, Observer(adapter::updateNetworkState))
        viewModel.news.observe(viewLifecycleOwner, Observer(adapter::submitList))
    }

    private fun setUpProgressBar(size: Int, networkState: NetworkState?) {
        progressBar?.visibility =
            if (size == 0 && networkState == NetworkState.RUNNING) View.VISIBLE else View.GONE
    }

}