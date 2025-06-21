
package com.example.agritrek

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Set up Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable back button
        toolbar.setNavigationOnClickListener { onBackPressed() } // Handle back button click

        // Find views
        val imageView = findViewById<ImageView>(R.id.imageView)
        val tvPlantName = findViewById<TextView>(R.id.tvPlantName)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val cardCause = findViewById<CardView>(R.id.cardCause)
        val tvCause = findViewById<TextView>(R.id.tvCause)
        val cardSolution = findViewById<CardView>(R.id.cardSolution)
        val tvSolution = findViewById<TextView>(R.id.tvSolution)

        // Get data from intent
        val imageUri = Uri.parse(intent.getStringExtra("imageUri"))
        val plantName = intent.getStringExtra("plantName")
        val description = intent.getStringExtra("description")
        val cause = intent.getStringExtra("cause")
        val solution = intent.getStringExtra("solution")

        // Set data to views
        imageView.setImageURI(imageUri)
        tvPlantName.text = plantName
        tvDescription.text = description

        // Only show cause and solution if they exist (for Diagnose case)
        if (!cause.isNullOrEmpty()) {
            cardCause.visibility = CardView.VISIBLE
            tvCause.text = cause
        }
        if (!solution.isNullOrEmpty()) {
            cardSolution.visibility = CardView.VISIBLE
            tvSolution.text = solution
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}