package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class p12Alarm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_p12_alarm)
        var start = findViewById<Button>(R.id.button)
        var RStart = findViewById<Button>(R.id.RStart)
        var cancel = findViewById<Button>(R.id.cancel_button)
        var Etext = findViewById<EditText>(R.id.time)
        var alarmManager: AlarmManager
        val intent = Intent(this,P12AlarmBroadCast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this,0,
            intent, FLAG_IMMUTABLE)// flag_Update_current


        start.setOnClickListener {
            var i = Etext.text.toString().toInt()
            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            //RTC.WAKEUP:current time + if phone in sleeping mode then also alarm will ringing
            //RTC: current time
            alarmManager.set(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis()+(i*1000),pendingIntent)
            Toast.makeText(this, "Alarm set in $i seconds", Toast.LENGTH_LONG ).show()
        }

        RStart.setOnClickListener {
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),
                5000,pendingIntent)
            Toast.makeText(this,"Repeating Alarm 5 Seconds", Toast.LENGTH_LONG).show()
        }

        cancel.setOnClickListener {
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            Toast.makeText(this,"Alarm Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}