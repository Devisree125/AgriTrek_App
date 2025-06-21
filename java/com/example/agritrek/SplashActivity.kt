package com.example.agritrek

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val splashDelay: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPref = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
            val username = sharedPref.getString("username", null)
            val password = sharedPref.getString("password", null)

            if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
                // If user is already logged in, go to MainActivity
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                // Otherwise, go to login page
                startActivity(Intent(this, login::class.java))
            }

            finish()
        }, splashDelay)
    }
}
