package com.example.mvvmroomcodingtest.ui.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmroomcodingtest.base.BaseActivity
import com.example.mvvmroomcodingtest.data.localdb.AppDatabase
import com.example.mvvmroomcodingtest.data.model.UserModel
import com.example.mvvmroomcodingtest.data.repository.UserRepository
import com.example.mvvmroomcodingtest.databinding.ActivityUserListBinding
import com.example.mvvmroomcodingtest.ui.adapter.UserListAdapter
import com.example.mvvmroomcodingtest.ui.viewmodel.UserListViewModel
import com.example.mvvmroomcodingtest.utils.gone
import com.example.mvvmroomcodingtest.utils.show

class UserListActivity : BaseActivity() {
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var viewModel: UserListViewModel
    private val userList = mutableListOf<UserModel>()
    private val binding: ActivityUserListBinding by lazy {
        ActivityUserListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(binding.root)
        setBaseView(view = binding.root, title = "User List", showBack = true)
        initializeViewModel()
        initializeRecyclerView()
        observers()
    }

    private fun observers() {
        viewModel.userList.observe(this, Observer { list ->
            if (!list.isNullOrEmpty()) {
                userList.clear()
                userList.addAll(list)
                if (::userListAdapter.isInitialized) {
                    userListAdapter.notifyDataSetChanged()
                    binding.recyclerView.show()
                    binding.placeHolder.gone()
                }
            } else {
                binding.recyclerView.gone()
                binding.placeHolder.show()
            }
        })
    }

    private fun initializeRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        userListAdapter = UserListAdapter(userList)
        if (::userListAdapter.isInitialized) {
            binding.recyclerView.adapter = userListAdapter
        }
    }

    private fun initializeViewModel() {
        val appDAO = AppDatabase.invoke(this).appDAO()
        viewModel = ViewModelProvider(
            this,
            UserListViewModel.ViewModelFactory(
                UserRepository.instance(
                    appDAO
                )
            )
        ).get(UserListViewModel::class.java)
    }
}