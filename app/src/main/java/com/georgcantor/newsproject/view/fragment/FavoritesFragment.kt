package com.georgcantor.newsproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        newsRecyclerView.layoutManager = layoutManager
        newsRecyclerView.setHasFixedSize(true)

        viewModel.getAllArticles()
        viewModel.articles.observe(viewLifecycleOwner, Observer {
            newsRecyclerView.adapter = FavoritesAdapter(
                requireContext(),
                it as MutableList<Article>
            ) { article ->
                requireActivity().shortToast(article.title)
            }
        })
    }

}