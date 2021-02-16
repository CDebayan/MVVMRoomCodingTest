package com.example.mvvmroomcodingtest.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast

fun <T> Context.openActivity(
    it: Class<T>,
    clearTask: Boolean = false,
    bundleKey: String = "",
    bundle: Bundle? = null
) {
    val intent = Intent(this, it)
    if (clearTask) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    } else {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }
    intent.putExtra(bundleKey, bundle)
    startActivity(intent)
}

fun Context.showToast(message: String?) {
    if (message != null) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun validateEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validateMobile(mobile: String): Boolean {
    return Patterns.PHONE.matcher(mobile).matches()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}
