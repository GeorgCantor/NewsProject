package com.georgcantor.newsproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.model.ViewState
import com.georgcantor.newsproject.util.observeNotNull
import com.georgcantor.newsproject.view.adapter.NewsAdapter
import com.georgcantor.newsproject.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    private lateinit var adapter: NewsAdapter
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NewsAdapter(requireContext())

        setupRecyclerView()
        loadNews()
    }

    private fun setupRecyclerView() {
        val manager = LinearLayoutManager(requireContext())
        newsRecyclerView.setHasFixedSize(true)
        newsRecyclerView.layoutManager = manager
        newsRecyclerView.adapter = adapter
    }

    private fun loadNews() {
        viewModel.getArticles().observeNotNull(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Success -> adapter.setNews(state.data)
                is ViewState.Loading -> Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                is ViewState.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

}