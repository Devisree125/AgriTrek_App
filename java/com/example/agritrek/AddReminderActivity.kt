

package com.example.agritrek

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AddReminderActivity : AppCompatActivity() {

    private lateinit var etTask: EditText
    private lateinit var tvDate: TextView
    private lateinit var tvTime: TextView
    private lateinit var btnSave: Button

    private var selectedCalendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)

        etTask = findViewById(R.id.etTask)
        tvDate = findViewById(R.id.tvDate)
        tvTime = findViewById(R.id.tvTime)
        btnSave = findViewById(R.id.btnSave)

        tvDate.setOnClickListener {
            showDatePicker()
        }

        tvTime.setOnClickListener {
            showTimePicker()
        }

        btnSave.setOnClickListener {
            val task = etTask.text.toString()
            if (task.isNotEmpty()) {
                Toast.makeText(this, "Reminder Set for: $task", Toast.LENGTH_SHORT).show()
                // You can trigger AlarmManager or save to DB here
                finish()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedCalendar.set(Calendar.YEAR, year)
                selectedCalendar.set(Calendar.MONTH, month)
                selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                tvDate.text = "$dayOfMonth/${month + 1}/$year"
            },
            selectedCalendar.get(Calendar.YEAR),
            selectedCalendar.get(Calendar.MONTH),
            selectedCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showTimePicker() {
        val timePicker = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                selectedCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedCalendar.set(Calendar.MINUTE, minute)
                tvTime.text = String.format("%02d:%02d", hourOfDay, minute)
            },
            selectedCalendar.get(Calendar.HOUR_OF_DAY),
            selectedCalendar.get(Calendar.MINUTE),
            true
        )
        timePicker.show()
    }
}
