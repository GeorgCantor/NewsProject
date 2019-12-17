package com.georgcantor.newsproject.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.base.BaseFragment
import com.georgcantor.newsproject.util.loadImage
import com.georgcantor.newsproject.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.content_article.*
import kotlinx.android.synthetic.main.fragment_article.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.core.parameter.parametersOf

class ArticleFragment : BaseFragment() {

    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getSharedViewModel { parametersOf("") }
    }

    override fun getLayoutId(): Int = R.layout.fragment_article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.article.observe(viewLifecycleOwner, Observer {
            requireActivity().loadImage(it.urlToImage ?: "", collapsingImage)
            titleTextView.text = it.title
        })
    }

}