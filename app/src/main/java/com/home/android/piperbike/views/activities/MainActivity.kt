package com.home.android.piperbike.views.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.home.android.piperbike.R
import com.home.android.piperbike.views.adapters.TabAdapter
import com.home.android.piperbike.views.fragments.ActivityFragment
import com.home.android.piperbike.views.fragments.UserFragment
import com.home.android.piperbike.views.utils.EncryptionUtils
import kotlinx.android.synthetic.main.activity_main.tabLayout
import kotlinx.android.synthetic.main.activity_main.viewPager

class MainActivity : AppCompatActivity() {

    companion object {
        const val FRAGMENT_ACTIVITY_LIST = "Activities"
        const val FRAGMENT_USER_FEED = "You"
        private val TAG = MainActivity::class.java.simpleName
        private const val TEST_VALUE_TO_ENCRYPT = "TEST_VALUE_TO_ENCRYPT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val encryptedValue = EncryptionUtils.encrypt(TEST_VALUE_TO_ENCRYPT, applicationContext)
        Log.d(TAG, "Encrypted: $encryptedValue")

        val decryptedValue = EncryptionUtils.decrypt(encryptedValue, applicationContext)
        Log.d(TAG, "Decrypted: $decryptedValue")

        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(UserFragment(), FRAGMENT_USER_FEED)
        tabAdapter.addFragment(ActivityFragment(), FRAGMENT_ACTIVITY_LIST)

        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
