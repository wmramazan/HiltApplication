package com.accenture.hiltapplication.ui.main

import android.os.Bundle
import androidx.navigation.ui.setupWithNavController
import com.accenture.hiltapplication.R
import com.accenture.hiltapplication.common.BaseActivity
import com.accenture.hiltapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomNavigation.setupWithNavController(navController)
    }

}