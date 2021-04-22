package com.example.demomotion.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DemoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val attribute1: String,
    val attribute2: String
)
