package com.example.myapplication

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class P17TimePicker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p17_time_picker)
        val textView: TextView = findViewById(R.id.textView)
        val timePicker: TimePicker = findViewById(R.id.timePicker)

        timePicker.setOnTimeChangedListener { _, hour, minute ->
            var hour1 = hour
            var amPm = ""
            // AM_PM decider logic
            when {
                hour1 == 0 -> {
                    hour1 += 12
                    amPm = "AM"
                }

                hour1 == 12 -> amPm = "PM"
                hour1 > 12 -> {
                    hour1 -= 12
                    amPm = "PM"
                }

                else -> amPm = "AM"
            }

            val hour2 = if (hour1 < 10) "0$hour1" else hour1
            val min = if (minute < 10) "0$minute" else minute
            // display format of time
            val msg = "Time is: $hour2 : $min $amPm"
            textView.text = msg
            textView.visibility = ViewGroup.VISIBLE
        }
    }
}