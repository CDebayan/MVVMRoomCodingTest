package com.example.mvvmroomcodingtest.ui.view

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmroomcodingtest.base.BaseActivity
import com.example.mvvmroomcodingtest.data.localdb.AppDatabase
import com.example.mvvmroomcodingtest.data.repository.UserRepository
import com.example.mvvmroomcodingtest.databinding.ActivityAddUserBinding
import com.example.mvvmroomcodingtest.ui.viewmodel.AddUserViewModel
import com.example.mvvmroomcodingtest.utils.*

class AddUserActivity : BaseActivity() {
    private lateinit var viewModel: AddUserViewModel
    private val binding: ActivityAddUserBinding by lazy {
        ActivityAddUserBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(binding.root)
        setBaseView(view = binding.root, title = "Add User", showBack = true)

        initializeViewModel()
        onClickListener()
        textChangeListener()
    }

    private fun initializeViewModel() {
        val appDAO = AppDatabase.invoke(this).appDAO()
        viewModel = ViewModelProvider(
            this,
            AddUserViewModel.ViewModelFactory(
                UserRepository.instance(
                    appDAO
                )
            )
        ).get(AddUserViewModel::class.java)
    }

    private fun textChangeListener() {
        binding.name.addTextChangedListener {
            if (binding.nameLayout.error != null) {
                binding.nameLayout.error = null
            }
        }

        binding.address.addTextChangedListener {
            if (binding.addressLayout.error != null) {
                binding.addressLayout.error = null
            }
        }

        binding.phone.addTextChangedListener {
            if (binding.phoneLayout.error != null) {
                binding.phoneLayout.error = null
            }
        }

        binding.email.addTextChangedListener {
            if (binding.emailLayout.error != null) {
                binding.emailLayout.error = null
            }
        }
    }

    private fun onClickListener() {
        binding.addUserButton.setOnClickListener {
            addUser()
        }
    }

    private fun addUser() {
        val name = binding.name.text.toString().trim()
        val address = binding.address.text.toString().trim()
        val phone = binding.phone.text.toString().trim()
        val email = binding.email.text.toString().trim()
        viewModel.addUser(name = name, address = address, phone = phone, email = email)
            .observe(this, Observer { state ->
                when (state) {
                    is AddUserViewModel.State.Loading -> setLoading(true)
                    is AddUserViewModel.State.Success -> {
                        setLoading(false)
                        showToast(state.message)
                        onBackPressed()
                    }
                    is AddUserViewModel.State.Error -> {
                        setLoading(false)
                        showToast(state.message)
                    }
                    is AddUserViewModel.State.ValidationError -> {
                        binding.nameLayout.error = state.nameError
                        binding.addressLayout.error = state.addressError
                        binding.phoneLayout.error = state.phoneError
                        binding.emailLayout.error = state.emailError
                    }
                }
            })
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.show()
            binding.addUserButton.disable()

        } else {
            binding.progressBar.gone()
            binding.addUserButton.enable()
        }
    }
}