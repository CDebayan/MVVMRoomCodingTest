package com.example.mvvmroomcodingtest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmroomcodingtest.data.repository.UserRepository

class UserListViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userList get() = userRepository.userList()


    @Suppress("UNCHECKED_CAST")
    class ViewModelFactory(private val userRepository: UserRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
                return UserListViewModel(userRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}