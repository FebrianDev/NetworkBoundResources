package com.febrian.networkboundresources.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.febrian.networkboundresources.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

    val restaurant = repository.getRestaurant().asLiveData()
}