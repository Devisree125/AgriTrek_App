package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class P4Rating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p4_rating)
        val simpleRatingBar:RatingBar = findViewById(R.id.simpleRatingBar)
        val simpleRating:RatingBar = findViewById(R.id.simpleRating)
        val submitButton:Button = findViewById(R.id.submitButton)



        submitButton.setOnClickListener {
            val totalStars = "Total Stars: " + simpleRatingBar.numStars
            val rating = "Rating: " + simpleRatingBar.rating
            Toast.makeText(this, """ $totalStars $rating""".trimIndent(),
                Toast.LENGTH_LONG).show()
        }

        simpleRating.setOnRatingBarChangeListener{ ratingBar, rating, fromUser ->
            Toast.makeText(this, "rating $rating, $fromUser",
                Toast.LENGTH_LONG).show()
        }

    }
}