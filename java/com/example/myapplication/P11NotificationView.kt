package com.example.myapplication

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class P11NotificationView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var notificationManager : NotificationManager
        setContentView(R.layout.activity_p11_notification_view)

         var txtView = findViewById<TextView>(R.id.tv)
        var inp = P11Notification()
//2
    //    var bundle: Bundle = RemoteInput.getResultsFromIntent(intent)
      //  if(bundle != null)
        //    txtView.setText(bundle.getString(inp.myKey))
    txtView.setText("Hey")

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager

        notificationManager.cancel(inp.notificationId)


    }
}