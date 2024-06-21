package com.yes.effectivemobile.presentation.ui

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yes.effectivemobile.R
import com.yes.effectivemobile.databinding.ActivityMainBinding
import com.yes.mainfeature.presentation.ui.MainScreen
import com.yes.searchfeature.presentation.ui.SearchScreen
import com.yes.sharedmodule.NavCommand

class MainActivity : AppCompatActivity(), NavCommand {

    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navController?.let {
            binding.navView.setupWithNavController(it)
        }
    }

    override fun navigate(navigation: String) {
        navController?.navigate(
            NavDeepLinkRequest.Builder
                .fromUri(navigation.toUri())
                .build()
        )
    }
}