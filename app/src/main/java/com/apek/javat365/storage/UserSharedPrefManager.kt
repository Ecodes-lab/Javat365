package com.apek.javat365.storage

import android.content.Context
import com.apek.javat365.models.User
import com.apek.javat365.models.UserData

class UserSharedPrefManager private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_USER_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("token", null) != null
        }


    val user: User
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_USER_NAME, Context.MODE_PRIVATE)

            return User(
                UserData(
//                    sharedPreferences.getString("id", null),
//                    sharedPreferences.getBoolean("active", false),
//                    sharedPreferences.getBoolean("external", false),
//                    sharedPreferences.getString("passwordSetToken", null),
//                    sharedPreferences.getString("expires", null),
//                    sharedPreferences.getString("roles", null) as List<String>,
//                    sharedPreferences.getString("userGroup", null),
//                    sharedPreferences.getString("memberOf", null),
//                    sharedPreferences.getString("siteGroups", null),
                    sharedPreferences.getString("email", null)
//                    sharedPreferences.getString("firstName", null),
//                    sharedPreferences.getString("lastName", null),
//                    sharedPreferences.getString("phoneNumber", null),
//                    sharedPreferences.getBoolean("superAdmin", false),
//                    Device(
//                        sharedPreferences.getString("fcmToken", null),
//                        sharedPreferences.getString("type", null)
//                    ),
//                    sharedPreferences.getString("createdAt", null),
//                    sharedPreferences.getString("updatedAt", null),
//                    sharedPreferences.getString("mailboxId", null)
                ),
                sharedPreferences.getString("token", null)
            )
        }


    fun saveEmail(email: String) {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_USER_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("email", email)

        editor.apply()
    }

    fun saveUser(user: User) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_USER_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

//        editor.putString("id", user.user.id)
//        editor.putString("email", user.user.email)
//        editor.putString("name", user.user.firstName + " " + user.user.lastName)
        editor.putString("token", user.token)

        editor.apply()

    }

    fun clear(): Boolean {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_USER_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        return !isLoggedIn
    }

    companion object {
        private val SHARED_PREF_USER_NAME = "user"
        private var mInstance: UserSharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): UserSharedPrefManager {
            if (mInstance == null) {
                mInstance = UserSharedPrefManager(mCtx)
            }
            return mInstance as UserSharedPrefManager
        }
    }

}
