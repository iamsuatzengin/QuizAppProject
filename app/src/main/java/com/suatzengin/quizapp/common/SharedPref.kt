package com.suatzengin.quizapp.common

import android.content.Context
import androidx.fragment.app.FragmentActivity

object SharedPref {

    fun login(activity: FragmentActivity?, isLoggedIn: Boolean = false, userId: Int){
        val sharedPref = activity?.getSharedPreferences("logged_in", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()){
            putBoolean("isLoggedIn", isLoggedIn)
            putInt("userId", userId)
            apply()
        }
    }

    fun logout(activity: FragmentActivity?) {
        val sharedPref = activity?.getSharedPreferences("logged_in", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean("isLoggedIn", false)
            remove("userId")
            apply()
        }
    }

    fun getUserId(activity: FragmentActivity?): Int {
        val sharedPref = activity?.getSharedPreferences("logged_in", Context.MODE_PRIVATE)
        return sharedPref!!.getInt("userId", 0)
    }

    fun isLoggedIn(activity: FragmentActivity?): Boolean {
        val sharedPref = activity?.getSharedPreferences("logged_in", Context.MODE_PRIVATE)
        return sharedPref!!.getBoolean("isLoggedIn", false)
    }
}