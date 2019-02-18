package com.sportpedia.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.sportpedia.model.User

class PrefManager(context: Context) {
    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences("SPORTPEDIA", Context.MODE_PRIVATE)
    }

    fun saveUser(user: User) {
        sharedPref.edit {
            putString(Const.userid, user.id)
            putString(Const.username, user.name)
            putString(Const.useremail, user.email)
            putString(Const.userphone, user.phone)
            putString(Const.useraddress, user.address)
            putString(Const.userimgurl, user.imgUrl)
        }
    }

    fun getUserString(key: String): String? {
        return sharedPref.getString(key, null)
    }
}