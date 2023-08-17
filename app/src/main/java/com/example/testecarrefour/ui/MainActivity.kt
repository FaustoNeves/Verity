package com.example.testecarrefour.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.testecarrefour.databinding.MainActivityBinding
import com.example.testecarrefour.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: MainActivityBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGetUsers.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val users = viewModel.getUsers()
                users.forEach {
                    Log.e("Users", it.userName!!)
                }
            }
        }
    }
}