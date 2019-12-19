package com.georgcantor.newsproject.view.fragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.base.BaseFragment
import com.georgcantor.newsproject.util.loadImage
import com.georgcantor.newsproject.viewmodel.ShareDataViewModel
import kotlinx.android.synthetic.main.content_article.*
import kotlinx.android.synthetic.main.fragment_article.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.core.parameter.parametersOf

class ArticleFragment : BaseFragment() {

    private lateinit var viewModel: ShareDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getSharedViewModel { parametersOf() }
    }

    override fun getLayoutId(): Int = R.layout.fragment_article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.article.observe(viewLifecycleOwner, Observer { article ->
            requireActivity().loadImage(article.urlToImage ?: "", collapsingImage)
            titleTextView.text = article.title
            descriptionTextView.text = article.description

            fab.setOnClickListener {
                val webPage = Uri.parse(article.url)
                val intentBuilder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
                intentBuilder.setToolbarColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimary
                    )
                )
                intentBuilder.setSecondaryToolbarColor(
                    ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark)
                )

                val customTabsIntent: CustomTabsIntent = intentBuilder.build()
                customTabsIntent.launchUrl(requireContext(), webPage)
            }
        })
    }

}