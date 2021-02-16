package com.example.mvvmroomcodingtest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.mvvmroomcodingtest.data.model.UserModel
import com.example.mvvmroomcodingtest.data.repository.UserRepository
import com.example.mvvmroomcodingtest.utils.validateEmail
import com.example.mvvmroomcodingtest.utils.validateMobile
import kotlinx.coroutines.Dispatchers

class AddUserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private var nameError: String? = null
    private var addressError: String? = null
    private var phoneError: String? = null
    private var emailError: String? = null

    fun addUser(
        name: String,
        address: String,
        phone: String,
        email: String
    ) = liveData(Dispatchers.IO) {
        val hasError = validateFields(name, address, phone, email)
        if (hasError) {
            emit(
                State.ValidationError(
                    nameError,
                    addressError,
                    phoneError,
                    emailError
                )
            )
        } else {
            emit(State.Loading)
            val response = userRepository.addUser(
                UserModel(
                    name = name,
                    address = address,
                    phone = phone,
                    email = email
                )
            )
            if (response >= 1) {
                emit(State.Success(message = "Added successfully"))
            } else {
                emit(State.Error(message = "Added failed"))
            }
        }
    }

    private fun validateFields(
        name: String,
        address: String,
        phone: String,
        email: String
    ): Boolean {
        var hasError = false
        if (name.isEmpty()) {
            hasError = true
            nameError = "Please enter name"
        }else{
            nameError = null
        }
        if (address.isEmpty()) {
            hasError = true
            addressError = "Please enter address"
        }else{
            addressError = null
        }
        if (phone.isEmpty()) {
            hasError = true
            phoneError = "Please enter phone"
        } else if (!validateMobile(phone)) {
            hasError = true
            phoneError = "Please enter valid phone"
        }else{
            phoneError = null
        }
        if (email.isEmpty()) {
            hasError = true
            emailError = "Please enter email"
        } else if (!validateEmail(email)) {
            hasError = true
            emailError = "Please enter valid email"
        }else{
            emailError = null
        }
        return hasError
    }

    sealed class State {
        object Loading : State()
        data class Success(val message: String?) : State()
        data class ValidationError(
            val nameError: String?,
            val addressError: String?,
            val phoneError: String?,
            val emailError: String?
        ) : State()

        data class Error(val message: String?) : State()
    }

    @Suppress("UNCHECKED_CAST")
    class ViewModelFactory(private val userRepository: UserRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddUserViewModel::class.java)) {
                return AddUserViewModel(userRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}