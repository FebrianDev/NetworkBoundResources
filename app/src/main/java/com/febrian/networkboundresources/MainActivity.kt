package com.febrian.networkboundresources

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.febrian.networkboundresources.data.Restaurant
import com.febrian.networkboundresources.databinding.ActivityMainBinding
import com.febrian.networkboundresources.ui.RestaurantAdapter
import com.febrian.networkboundresources.ui.RestaurantViewModel
import com.febrian.networkboundresources.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val restaurantViewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantAdapter = RestaurantAdapter(ArrayList())

        binding.apply {
            recyclerView.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            restaurantViewModel.restaurant.observe(this@MainActivity) {
                it.data?.let { it1 -> restaurantAdapter.setData(it1 as ArrayList<Restaurant>) }

                progressBar.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
                textViewError.isVisible = it is Resource.Error && it.data.isNullOrEmpty()
                textViewError.text = it.error?.localizedMessage
            }
        }
    }
}