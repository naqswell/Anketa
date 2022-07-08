package com.example.anketa

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.anketa.data.Role
import com.example.anketa.databinding.ActivityMainBinding
import com.example.anketa.screen.profile.NavBarCallbacks
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavBar : AppCompatActivity(), NavBarCallbacks{

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)


        checkLoginStatus()
        navView.setupWithNavController(navController)
    }


    private fun checkLoginStatus() {
        if (prefs.role == Role.Empty) {
            binding.navView.visibility = View.GONE
            findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_login)
        }
    }

    override fun showNavBar() {
        binding.navView.visibility = View.VISIBLE
    }

    override fun hideNavBar() {
        binding.navView.visibility = View.GONE
    }


}