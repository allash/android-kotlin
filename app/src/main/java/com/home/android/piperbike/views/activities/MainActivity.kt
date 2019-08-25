package com.home.android.piperbike.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.home.android.piperbike.R
import com.home.android.piperbike.views.adapters.TabAdapter
import com.home.android.piperbike.views.fragments.ActivityFragment
import com.home.android.piperbike.views.fragments.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val FRAGMENT_ACTIVITY_LIST = "Activities"
        const val FRAGMENT_USER_FEED = "You"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inject(this)

        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(UserFragment(), FRAGMENT_USER_FEED)
        tabAdapter.addFragment(ActivityFragment(), FRAGMENT_ACTIVITY_LIST)

        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
