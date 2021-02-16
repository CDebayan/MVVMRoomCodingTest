package com.example.mvvmroomcodingtest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmroomcodingtest.data.model.UserModel
import com.example.mvvmroomcodingtest.databinding.ChildUserListBinding

class UserListAdapter(private val userList: List<UserModel>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChildUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDataToViews(position)
    }


    inner class ViewHolder(private val binding: ChildUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataToViews(position: Int) {
            binding.name.text = userList[position].name
            binding.address.text = userList[position].address
            binding.phone.text = userList[position].phone
            binding.email.text = userList[position].email
        }

    }
}