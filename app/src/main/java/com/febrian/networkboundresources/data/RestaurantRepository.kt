package com.febrian.networkboundresources.data

import androidx.room.withTransaction
import com.febrian.networkboundresources.api.ApiService
import com.febrian.networkboundresources.data.database.RestaurantDatabase
import com.febrian.networkboundresources.utils.networkBoundResource
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: ApiService,
    private val db: RestaurantDatabase
) {
    private val restaurantDao = db.restaurantDao()

    fun getRestaurant() = networkBoundResource(
        query = {
            restaurantDao.getAllRestaurants()
        },
        fetch = {
            api.getRestaurants()
        },
        saveFetchResult = { restaurant ->
            db.withTransaction {
                restaurantDao.deleteAllRestaurants()
                restaurantDao.insertRestaurants(restaurant)
            }
        }
    )
}