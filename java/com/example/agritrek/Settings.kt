package com.example.agritrek



import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<CardView>(R.id.cardProfile).setOnClickListener {
            Toast.makeText(this, "Manage Profile clicked", Toast.LENGTH_SHORT).show()
        }

        findViewById<CardView>(R.id.cardPrivacy).setOnClickListener {
            Toast.makeText(this, "Privacy Policy clicked", Toast.LENGTH_SHORT).show()
        }

        findViewById<CardView>(R.id.cardSafety).setOnClickListener {
            Toast.makeText(this, "Safety Centre clicked", Toast.LENGTH_SHORT).show()
        }

        findViewById<CardView>(R.id.cardTerms).setOnClickListener {
            Toast.makeText(this, "Terms and Services clicked", Toast.LENGTH_SHORT).show()
        }

        findViewById<CardView>(R.id.cardHistory).setOnClickListener {
            Toast.makeText(this, "History Cleared", Toast.LENGTH_SHORT).show()
        }

        findViewById<CardView>(R.id.cardLogout).setOnClickListener {
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
            // You can also finish activity or navigate to login screen here
        }
    }
}
