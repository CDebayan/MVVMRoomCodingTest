package com.example.mvvmroomcodingtest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val userId: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "phone")
    val phone: String? = "",
    @ColumnInfo(name = "email")
    val email: String? = ""
)