package com.example.newsapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.fragment_splash)
        /* Handler(Looper.myLooper()!!).postDelayed({
            setContentView(mBinding.root)
            mBinding.bottomNavMenu.setupWithNavController(
                navController = findNavController(R.id.nav_host_fragment)
            )
        }, 3000)
    */
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            setContentView(mBinding.root)
            mBinding.bottomNavMenu.setupWithNavController(
                navController =
                findNavController(R.id.nav_host_fragment)
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}