package com.example.mvvmroomcodingtest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.mvvmroomcodingtest.data.localdb.AppDAO
import com.example.mvvmroomcodingtest.data.model.UserModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class UserRepository() {
    private lateinit var appDAO: AppDAO

    private constructor(appDAO: AppDAO) : this() {
        this.appDAO = appDAO
    }

    companion object {
        private lateinit var userRepository: UserRepository

        fun instance(appDAO: AppDAO): UserRepository {
            if (!::userRepository.isInitialized) {
                userRepository = UserRepository(appDAO)
            }
            return userRepository
        }
    }

    suspend fun addUser(userModel: UserModel): Long {
        return withContext(IO) {
            appDAO.addUser(userModel)
        }
    }

    fun userList(): LiveData<List<UserModel>> {
        return appDAO.userList().asLiveData()
    }
}