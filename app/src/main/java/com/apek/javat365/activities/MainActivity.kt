package com.apek.javat365.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.apek.javat365.R
import com.apek.javat365.fragments.*
import com.apek.javat365.storage.UserSharedPrefManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_profile.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragment()
        val groupFragment = GroupFragment()
        val chatFragment = ChatFragment()
        val notificationFragment = NotificationFragment()
        val profileFragment = ProfileFragment()

        makeCurrentFragment(homeFragment)


        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> makeCurrentFragment(homeFragment)
                R.id.action_groups -> makeCurrentFragment(groupFragment)
                R.id.action_chat -> makeCurrentFragment(chatFragment)
                R.id.action_notification -> makeCurrentFragment(notificationFragment)
                R.id.action_profile -> makeCurrentFragment(profileFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }

}