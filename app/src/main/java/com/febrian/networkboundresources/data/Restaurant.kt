package com.febrian.networkboundresources.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class Restaurant(
    @PrimaryKey val name: String,
    val type: String,
    val logo: String,
    val address: String
)