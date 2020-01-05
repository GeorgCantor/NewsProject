package com.georgcantor.newsproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.util.PreferenceManager
import com.georgcantor.newsproject.view.adapter.TagsAdapter
import com.georgcantor.newsproject.viewmodel.ShareDataViewModel
import com.georgcantor.newsproject.viewmodel.TagsViewModel
import kotlinx.android.synthetic.main.fragment_tags.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class TagsFragment : Fragment() {

    private lateinit var viewModel: TagsViewModel
    private lateinit var shareDataViewModel: ShareDataViewModel
    private lateinit var adapter: TagsAdapter
    private var counter = 0
    private val selectedTopics by lazy { ArrayList<String>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = getViewModel { parametersOf() }
        shareDataViewModel = getSharedViewModel { parametersOf(PreferenceManager(requireActivity())) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tags, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(tagsToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tagsRecyclerView.setHasFixedSize(true)
        tagsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        viewModel.getTags().observe(viewLifecycleOwner, Observer {
            adapter = TagsAdapter(it) { tag ->
                when (counter) {
                    0 -> {
                        selectedTopics.add(tag)
                        counter = 1
                        firstTagTextView.text = tag
                    }
                    1 -> {
                        selectedTopics.add(tag)
                        counter = 2
                        secondTagTextView.text = tag
                    }
                    2 -> {
                        selectedTopics.add(tag)
                        counter = 3
                        thirdTagTextView.text = tag
                        shareDataViewModel.setFabVisibility(true)
                    }
                    3 -> {
                        selectedTopics.add(tag)
                        counter = 4
                        fourthTagTextView.text = tag
                    }
                    4 -> {
                        selectedTopics.add(tag)
                        counter = 5
                        fifthTagTextView.text = tag
                    }
                }
            }
            tagsRecyclerView.adapter = adapter
        })

        shareDataViewModel.isFabVisible.observe(viewLifecycleOwner, Observer { visible ->
            if (visible) tagsFab.show() else tagsFab.hide()
        })

        tagsFab.setOnClickListener {
            shareDataViewModel.setMainTags(selectedTopics)
            requireActivity().recreate()
            requireActivity().onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> requireActivity().onBackPressed()
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        shareDataViewModel.setFabVisibility(false)
    }

}