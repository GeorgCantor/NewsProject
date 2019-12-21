package com.georgcantor.newsproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.util.longToast
import com.georgcantor.newsproject.view.adapter.TagsAdapter
import com.georgcantor.newsproject.viewmodel.TagsViewModel
import kotlinx.android.synthetic.main.fragment_tags.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class TagsFragment : Fragment() {

    private lateinit var viewModel: TagsViewModel
    private lateinit var adapter: TagsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tags, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tagsRecyclerView.setHasFixedSize(true)
        tagsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        viewModel.setTags()
        viewModel.tags.observe(viewLifecycleOwner, Observer {
            adapter = TagsAdapter(it, requireActivity()::longToast)
            tagsRecyclerView.adapter = adapter
        })
    }

}