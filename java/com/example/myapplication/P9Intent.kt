package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class P9Intent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p9_intent)
        val btnintent = findViewById<Button>(R.id.btnintent)
        btnintent.setOnClickListener {
            val intent = Intent(this, P5CustomToast::class.java)
            startActivity(intent)

        }
    }
}