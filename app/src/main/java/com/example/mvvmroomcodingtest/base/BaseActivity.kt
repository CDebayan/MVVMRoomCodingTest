package com.example.mvvmroomcodingtest.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmroomcodingtest.databinding.ActivityBaseBinding

open class BaseActivity : AppCompatActivity() {
    private val baseBinding : ActivityBaseBinding by lazy {
        ActivityBaseBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(baseBinding.root)

        baseClickListener()
    }

    private fun baseClickListener() {
        baseBinding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    protected fun setBaseView(view: View, title: String = "", showBack : Boolean = false) {
        baseBinding.baseLayout.addView(view)
        baseBinding.baseTitle.text = title

        if(showBack){
            baseBinding.backButton.visibility = View.VISIBLE
        }else{
            baseBinding.backButton.visibility = View.GONE
        }
    }
}