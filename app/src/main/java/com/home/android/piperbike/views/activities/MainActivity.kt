package com.home.android.piperbike.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.home.android.piperbike.R
import com.home.android.piperbike.views.adapters.TabAdapter
import com.home.android.piperbike.views.fragments.ActivityFragment
import com.home.android.piperbike.views.fragments.CanvasFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val FRAGMENT_ACTIVITY_LIST = "Activities"
        const val FRAGMENT_CANVAS_DEMO = "Canvas"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(ActivityFragment(), FRAGMENT_ACTIVITY_LIST)
        tabAdapter.addFragment(CanvasFragment(), FRAGMENT_CANVAS_DEMO)

        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
