package com.georgcantor.newsproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.view.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tabs.*

class TabsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        tabLayout.setupWithViewPager(viewPager)

        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(NewsFragment.newInstance("nba"), "NBA")
        adapter.addFragment(NewsFragment.newInstance("nfl"), "NFL")
        adapter.addFragment(NewsFragment.newInstance("nhl"), "NHL")

        viewPager.adapter = adapter
    }

}