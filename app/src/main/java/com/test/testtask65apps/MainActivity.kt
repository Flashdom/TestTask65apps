package com.test.testtask65apps

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.testtask65apps.databinding.ActivityMainBinding
import com.test.testtask65apps.fragments.JobListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this@MainActivity))
        switchFragment(JobListFragment())
        setContentView(binding.root)

    }

    fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack("MAIN")
            .replace(binding.fcvContainer.id, fragment).commit()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}