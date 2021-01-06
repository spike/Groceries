package io.grocerieslist.groceries.repository

import androidx.lifecycle.LiveData
import io.grocerieslist.groceries.data.ItemDao
import io.grocerieslist.groceries.model.Item

class ItemRepository(private val itemDao: ItemDao) {

    val readAllData: LiveData<List<Item>> = itemDao.readAllData()

    suspend fun addItem(item: Item) {
        itemDao.addItem(item)
    }

    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }

}