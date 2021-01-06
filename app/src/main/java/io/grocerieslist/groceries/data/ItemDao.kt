package io.grocerieslist.groceries.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.grocerieslist.groceries.model.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItem(item: Item)

    @Update
    fun updateItem(item: Item)

    @Query( "SELECT * FROM item_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Item>>


}