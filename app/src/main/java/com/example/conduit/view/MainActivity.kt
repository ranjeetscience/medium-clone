package com.example.conduit.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.api.model.entity.User
import com.example.conduit.R
import com.example.conduit.viewmodel.LoginViewModel
import com.example.conduit.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.function.BinaryOperator

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val loginViewModel: LoginViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private lateinit var navView: NavigationView
    lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_feed,
                R.id.nav_login,
                R.id.nav_setting,
                R.id.nav_profile
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (sharedPref.getString("auth_toke", null) != null) {
            updateMenu(true)
        }
        subscribeToLogin()

    }

    private fun subscribeToLogin() {

        loginViewModel.userResponse.observe(this) {
            with(sharedPref.edit()) {
                putString(getString(R.string.auth_token), it.user.token)
                apply()
            }
            updateMenu(true)
        }

        signUpViewModel.user.observe(this) {
            with(sharedPref.edit()) {
                putString(getString(R.string.auth_token), it.token)
                apply()
            }
            updateMenu(true)
        }


    }

    private fun updateMenu(isLoggedIn: Boolean) {
        navView.menu.clear()
        if (isLoggedIn) {
            navView.inflateMenu(R.menu.activity_main_logged_in_drawer)
        } else {
            navView.inflateMenu(R.menu.activity_main_drawer)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}