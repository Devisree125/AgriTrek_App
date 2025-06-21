package com.example.agritrek

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Notifications : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

       val recyclerView = findViewById<RecyclerView>(R.id.recyclerNotifications)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val messages = listOf(
            "You created an account. Welcome to AgriTrek!",
            "Reminder set for crop watering.",
            "Weekly weather update available.",
            "Your disease diagnosis report is ready.",
            "New article on pest control is available."
        )

        recyclerView.adapter = NotificationAdapter(messages)
    }
}
