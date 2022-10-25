package com.febrian.networkboundresources.api

import com.febrian.networkboundresources.data.Restaurant
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "https://random-data-api.com/api/"
    }

    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurants(): ArrayList<Restaurant>
}