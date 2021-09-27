package com.example.cryptohooker.ui

import android.os.Bundle
import com.example.cryptohooker.R
import com.example.cryptohooker.base.BaseActivity
import com.example.cryptohooker.core.extensions.replaceFragmentSafely
import com.example.cryptohooker.databinding.ActivityMainBinding
import com.example.cryptohooker.ui.movies.MoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

/**
 * The MainActivity.kt, Main activity class, launcher activity
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        replaceFragmentSafely(MoviesFragment())
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_movies -> {
                    replaceFragmentSafely(MoviesFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_news_feed -> {

                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}