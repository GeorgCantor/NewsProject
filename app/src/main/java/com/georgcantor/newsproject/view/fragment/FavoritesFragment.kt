package com.georgcantor.newsproject.view.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.model.local.Article
import com.georgcantor.newsproject.util.shortToast
import com.georgcantor.newsproject.view.adapter.FavoritesAdapter
import com.georgcantor.newsproject.viewmodel.FavoritesViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class FavoritesFragment : Fragment() {

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = getViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        (activity as AppCompatActivity).setSupportActionBar(newsToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        newsToolbar.title = getString(R.string.favorites)

        refreshLayout.setOnRefreshListener {
            setupRecyclerView()
            refreshLayout.isRefreshing = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> requireActivity().onBackPressed()
        }
        return false
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        newsRecyclerView.layoutManager = layoutManager
        newsRecyclerView.setHasFixedSize(true)

        viewModel.getAllArticles()
        viewModel.articles.observe(viewLifecycleOwner, Observer {
            newsRecyclerView.adapter = FavoritesAdapter(
                requireContext(),
                it as MutableList<Article>,
                { article ->
                    requireActivity().shortToast(article.title)
                }, { article ->
                    viewModel.deleteByUrl(article.url)
                    Handler().postDelayed(
                        this::setupRecyclerView,
                        500
                    )
                })
        })
    }

}