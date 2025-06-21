package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Handler
import android.os.Looper

class P2SlashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p2_slash_screen)

        //calling handler to run a task
        // for specific time interval

        //Handler is a class that allows you to schedule code execution on a specific thread.
        //Looper.getMainLooper() gets the main UI thread’s Looper, which is responsible for handling UI updates.
        //.postDelayed({...}, delayMillis) schedules the given block of code ({ ... }) to run after delayMillis milliseconds.

        Handler(Looper.getMainLooper()).postDelayed({
            // creating a new intent
            val i = Intent(
                this@P2SlashScreen,
                P1ScrollView::class.java
            )
          // starting a new activity.
            startActivity(i)

            // our current activity.
            finish()
        }, 2000)

    }
    }
