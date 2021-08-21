package com.apek.javat365.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.apek.javat365.R
import com.apek.javat365.activities.auth.SigninActivity
import com.apek.javat365.storage.UserSharedPrefManager

class WelcomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )

    }

    fun signinButton(view: View) {
        val intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()

        if(UserSharedPrefManager.getInstance(this).isLoggedIn) {
            navigateToActivity(this@WelcomeActivity, MainActivity::class.java)
        }
    }
}