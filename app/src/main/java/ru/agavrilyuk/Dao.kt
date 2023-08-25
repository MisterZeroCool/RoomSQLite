package ru.agavrilyuk

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM item")
    fun getAll(): Flow<List<Item>>

    @Insert
    fun insertItem(vararg item: Item)
}