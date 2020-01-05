package com.georgcantor.newsproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.util.PreferenceManager
import com.georgcantor.newsproject.view.adapter.ViewPagerAdapter
import com.georgcantor.newsproject.viewmodel.ShareDataViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tabs.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.core.parameter.parametersOf

class TabsFragment : Fragment() {

    private lateinit var shareDataViewModel: ShareDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        shareDataViewModel =
            getSharedViewModel { parametersOf(PreferenceManager(requireActivity())) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tabs, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = getString(R.string.app_name)
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.setNavigationOnClickListener {
            requireActivity().drawerLayout.openDrawer(GravityCompat.START)
        }
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    Navigation.findNavController(view).navigate(R.id.searchFragment)
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
        tabLayout.setupWithViewPager(viewPager)

        shareDataViewModel.getMainTags().observe(viewLifecycleOwner, Observer {
            val adapter = ViewPagerAdapter(childFragmentManager)
            it.map {
                adapter.addFragment(
                    NewsFragment.newInstance(it),
                    it
                )
            }

            viewPager.adapter = adapter
        })
    }

}