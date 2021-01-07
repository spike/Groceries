package io.grocerieslist.groceries.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import io.grocerieslist.groceries.data.ItemDatabase
import io.grocerieslist.groceries.repository.ItemRepository
import io.grocerieslist.groceries.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application): AndroidViewModel (application) {
    val readAllData: LiveData<List<Item>>
    private val repository: ItemRepository

    init {
        val itemDao = ItemDatabase.getDatabase(application).itemDao()
        repository = ItemRepository(itemDao)
        readAllData = repository.readAllData
    }

    fun addItem(item: Item){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(item)
        }
    }

    fun updateItem(item: Item){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item)
        }
    }

    fun deleteAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllItems()
        }
    }
}