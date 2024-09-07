package com.example.hh_clone_effective_test.core.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.hh_clone_effective_test.R
import com.example.hh_clone_effective_test.databinding.ActivityMainBinding
import com.example.hh_clone_effective_test.details.ui.DetailsFragment
import com.example.hh_clone_effective_test.favorites.ui.FavoritesFragment
import com.example.hh_clone_effective_test.login.ui.CodeFragment
import com.example.hh_clone_effective_test.login.ui.LoginFragment
import com.example.hh_clone_effective_test.search.ui.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentFragmentTag: String = "search"
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
        changeFragment(DetailsFragment(), "search")
        initNavigation()
    }

    private fun checkIfNavigationIsAllowed(): Boolean = currentFragmentTag != "login" && currentFragmentTag != "code"

    private fun initNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.search -> {
                        if (checkIfNavigationIsAllowed()) {
                            val tag = "search"
                            val fragment = checkFragmentExistence(tag)
                            changeFragment(fragment ?: SearchFragment(), tag)
                            true
                        } else
                            false
                    }
                    R.id.favorites -> {
                        if (checkIfNavigationIsAllowed()) {
                            val tag = "favorites"
                            val fragment = checkFragmentExistence(tag)
                            changeFragment(fragment ?: FavoritesFragment(), tag)
                            true
                        } else
                            false
                    }
                    R.id.responses -> {
                        val tag = "responses"
                        checkIfNavigationIsAllowed()
                    }
                    R.id.messages -> {
                        val tag = "messages"
                        checkIfNavigationIsAllowed()
                    }
                    R.id.profile -> {
                        val tag = "profile"
                        checkIfNavigationIsAllowed()
                    }
                    else -> false
                }
        }
    }

    fun launchSearchFragment() {
        val fragment = checkFragmentExistence("search") ?: SearchFragment()
        changeFragment(fragment , "search")
        currentFragmentTag = "search"
    }

    fun launchCodeFragment(email: String) {
        val bundle = Bundle()
        bundle.putString("email", email)
        val fragment = checkFragmentExistence("code") ?: CodeFragment()
        fragment.arguments = bundle
        changeFragment(fragment , "code")
        currentFragmentTag = "code"
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