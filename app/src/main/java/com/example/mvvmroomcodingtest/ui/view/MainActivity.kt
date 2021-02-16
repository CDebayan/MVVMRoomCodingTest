package com.example.mvvmroomcodingtest.ui.view

import android.os.Bundle
import com.example.mvvmroomcodingtest.base.BaseActivity
import com.example.mvvmroomcodingtest.databinding.ActivityMainBinding
import com.example.mvvmroomcodingtest.utils.openActivity

class MainActivity : BaseActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(binding.root)
        setBaseView(view = binding.root,title = "User App")
        onClickListener()
    }

    private fun onClickListener() {
        binding.addUserButton.setOnClickListener {
            openActivity(AddUserActivity::class.java)
        }

        binding.userListButton.setOnClickListener {
            openActivity(UserListActivity::class.java)
        }
    }
}