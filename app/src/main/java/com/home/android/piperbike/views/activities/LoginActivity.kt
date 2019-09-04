package com.home.android.piperbike.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.home.android.piperbike.R
import com.home.android.piperbike.databinding.ActivityLoginBinding
import com.home.android.piperbike.di.inject
import com.home.android.piperbike.ui.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.btnLogin
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val TEST_USER_EMAIL = "john.doe@mail.com"
        private const val TEST_USER_PASSWORD = "asdf"
    }

    @Inject
    lateinit var loginViewModel: LoginViewModel

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        btnLogin.setOnClickListener {
            loginViewModel.login(TEST_USER_EMAIL, TEST_USER_PASSWORD)
        }

        loginViewModel.uiState.observe(this, Observer {
            val uiModel = it ?: return@Observer

            if (uiModel.success) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, uiModel.error, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
