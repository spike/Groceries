package io.grocerieslist.groceries.data

import androidx.lifecycle.LiveData
import androidx.room.*
import io.grocerieslist.groceries.model.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItem(item: Item)

    @Update
    fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM item_table")
    suspend fun deleteAllItems()

    @Query( "SELECT * FROM item_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Item>>


}