package com.example.myapplication

import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class P16DatePicker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p16_date_picker)
        val datePicker: DatePicker = findViewById(R.id.datePicker)
        val today = Calendar.getInstance()
        //to get the current get in this format.
        datePicker.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )//to display the selected date from the calendar
        { view, year, month, day ->
            val msg = "You Selected: $day/${month+1}/$year"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

    }
}
//Date picker :android:datePickerMode="spinner" or "calender"
//android:calendarViewShown