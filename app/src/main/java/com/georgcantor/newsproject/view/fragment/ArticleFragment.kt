package com.georgcantor.newsproject.view.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.util.loadImage
import com.georgcantor.newsproject.viewmodel.ShareDataViewModel
import kotlinx.android.synthetic.main.content_article.*
import kotlinx.android.synthetic.main.fragment_article.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.core.parameter.parametersOf

class ArticleFragment : Fragment() {

    private lateinit var viewModel: ShareDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getSharedViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_article, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(articleToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.article.observe(viewLifecycleOwner, Observer { article ->
            articleToolbar.title = article.source.name
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