package io.grocerieslist.groceries.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val quantity: Double,
   // val quantityMeasurement: String,
)
