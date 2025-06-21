package com.example.agritrek

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Vibrator
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val task = intent?.getStringExtra("task") ?: "Reminder Task"

        val builder = NotificationCompat.Builder(context, "reminder_channel")
            .setSmallIcon(R.drawable.baseline_circle_notifications_24)
            .setContentTitle("AgriTrek Reminder")
            .setContentText(task)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))

        // 🔐 Check for POST_NOTIFICATIONS permission on Android 13+
        val notificationManager = NotificationManagerCompat.from(context)
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU ||
            androidx.core.content.ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
        } else {
            Toast.makeText(
                context,
                "Permission to post notifications not granted",
                Toast.LENGTH_SHORT
            ).show()
        }

        // 🍃 Vibration with permission check
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        try {
            vibrator.vibrate(1000)
        } catch (e: SecurityException) {
            Toast.makeText(context, "Vibration permission not granted", Toast.LENGTH_SHORT).show()
        }

        Toast.makeText(context, "Reminder: $task", Toast.LENGTH_LONG).show()
    }
}

