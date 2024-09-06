package com.example.hh_clone_effective_test.core.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.hh_clone_effective_test.R
import com.example.hh_clone_effective_test.databinding.ActivityMainBinding
import com.example.hh_clone_effective_test.search.ui.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentFragmentTag: String = "LOGIN"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }
        changeFragment(SearchFragment(),"LOGIN")

    }

    private fun initNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.search -> {
                    val tag = "search"
                    val fragment = checkFragmentExistence(tag)
                   // changeFragment(fragment ?: HomeFragment(), tag)
                    true
                }
                R.id.favorites -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                   // changeFragment(fragment ?: FavoritesFragment(), tag)
                    true
                }
                R.id.responses -> {
                    val tag = "responses"
                    val fragment = checkFragmentExistence(tag)
                   // changeFragment(fragment ?: WatchLaterFragment(), tag)
                    true
                }
                R.id.messages -> {
                    val tag = "messages"
                    val fragment = checkFragmentExistence(tag)
                  //  changeFragment(fragment ?: SettingsFragment(), tag)
                    true
                }
                R.id.profile -> {
                    val tag = "profile"
                    val fragment = checkFragmentExistence(tag)
                  // changeFragment(fragment ?: SettingsFragment(), tag)
                    true
                }
                else -> false
            }
        }
    }

    private fun openCodeFragment(email: String) {
        //val fragment = checkFragmentExistence(tag)
        // changeFragment(fragment ?: SettingsFragment(), tag)
    }

    private fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .commit()
    }
}