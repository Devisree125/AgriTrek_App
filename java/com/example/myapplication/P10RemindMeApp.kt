package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class P10RemindMeApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p10_remind_me_app)

        val btn1 = findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener {
            val intent= Intent(this,P11Notification::class.java)
            startActivity(intent)
        }
        val btn2 = findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener {
            val intent= Intent(this,p12Alarm::class.java)
            startActivity(intent)
        }
        val btn3 = findViewById<Button>(R.id.btn3)
        btn3.setOnClickListener {
            val intent= Intent(this,P13JobSchedule::class.java)
            startActivity(intent)
        }

    }
}