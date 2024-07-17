package com.example.capstone_project

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow


@Entity(tableName = "MenuItems")
data class MenuItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)


@Dao
interface MenuItemDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(menuItem: MenuItem);

    @Query("select * from MenuItems")
    fun getAllMenuItems(): Flow<List<MenuItem>>

}


@Database(entities = [MenuItem::class], version = 1)
abstract class MenuItemDatabase(): RoomDatabase(){
    abstract val menuItemDao: MenuItemDao
}













