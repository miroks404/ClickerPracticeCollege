package com.example.clickerpractice.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.clickerpractice.R
import com.example.clickerpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.main)

        viewModel.loadClicker()

        viewModel.uiState.observe(this@MainActivity) { state ->
            binding.tvCounter.text = "${state.countOfClicks}"
        }

        binding.bClickMe.setOnClickListener {
            viewModel.onClickMeClicked()
        }

    }

}