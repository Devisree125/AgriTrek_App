package com.example.agritrek

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class DiseaseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_detail)

        // Setup toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Disease Details"
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Handles back button
        }

        // Load disease details
        val disease = intent.getSerializableExtra("disease") as? Disease

        disease?.let {
            findViewById<ImageView>(R.id.diseaseImageView).setImageResource(it.imageResId)
            findViewById<TextView>(R.id.diseaseNameTextView).text = it.name
            findViewById<TextView>(R.id.scientificNameTextView).text = it.scientificName
            findViewById<TextView>(R.id.descriptionTextView).text = it.description
            findViewById<TextView>(R.id.solutionTextView).text = it.solution
        }
    }
}
