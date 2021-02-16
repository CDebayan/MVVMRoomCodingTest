package com.example.mvvmroomcodingtest.data.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmroomcodingtest.data.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(userModel: UserModel) : Long

    @Query("SELECT * FROM user ORDER BY name ASC")
    fun userList(): Flow<List<UserModel>>
}