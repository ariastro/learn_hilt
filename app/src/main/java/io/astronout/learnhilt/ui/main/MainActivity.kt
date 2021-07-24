package io.astronout.learnhilt.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.astronout.learnhilt.data.model.User
import io.astronout.learnhilt.databinding.ActivityMainBinding
import io.astronout.learnhilt.utils.Status

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()

    }

    private fun setupObserver() {
        mainViewModel.users.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.rvUser.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvUser.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        adapter = MainAdapter(users)
        binding.rvUser.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}