package com.example.demomotion.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.demomotion.data.entity.DemoEntity

@Dao
interface DemoDao {

    @Query("select * from DemoEntity")
    fun getAllEntities(): LiveData<List<DemoEntity>>
}
