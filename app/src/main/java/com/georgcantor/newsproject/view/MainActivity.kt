package com.georgcantor.newsproject.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.georgcantor.newsproject.R
import com.georgcantor.newsproject.util.PreferenceManager
import com.georgcantor.newsproject.viewmodel.ShareDataViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tabs.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var shareDataViewModel: ShareDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shareDataViewModel = getViewModel { parametersOf(PreferenceManager(this)) }

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        navView.itemIconTintList = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.abc -> openNews(item.title.toString())
            R.id.associated -> openNews(item.title.toString())
            R.id.bbc -> openNews(item.title.toString())
            R.id.cnn -> openNews(item.title.toString())
            R.id.google -> openNews(item.title.toString())
            R.id.independent -> openNews(item.title.toString())
            R.id.tags -> {
                findNavController(this, R.id.navHostFragment).navigate(R.id.action_tabsFragment_to_tagsFragment)
            }
            R.id.saved_news -> {
                findNavController(this, R.id.navHostFragment).navigate(R.id.action_tabsFragment_to_favoritesFragment)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun openNews(query: String) {
        shareDataViewModel.setQuery(query)
        findNavController(this, R.id.navHostFragment).navigate(R.id.action_tabsFragment_to_newsFragment)
    }
}
