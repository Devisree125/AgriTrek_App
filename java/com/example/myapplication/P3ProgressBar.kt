package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class P3ProgressBar : AppCompatActivity() {
    private var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_p3_progress_bar)

        val pgsBar = findViewById<ProgressBar>(R.id.pBar)// detreminate
        val pgsBar1 = findViewById<ProgressBar>(R.id.pBarCircular)//indeterminate
        val txtView = findViewById<TextView>(R.id.tView)
        val btn = findViewById<Button>(R.id.btnShow)
        val btnR = findViewById<Button>(R.id.btnReset)
        pgsBar1.visibility = View.INVISIBLE
        btn.setOnClickListener {
            pgsBar1.visibility = View.VISIBLE
            i= pgsBar.progress
            Thread{
                while(i<100){
                    i = i+10
                    Handler(Looper.getMainLooper()).post {
                        pgsBar.progress = i
                        txtView.text = i.toString() + "/" + pgsBar.max
                        if (i == 100) {
                            pgsBar1.visibility = View.INVISIBLE
                        }
                    }
                    try {
                        Thread.sleep(1000)
                    }
                    catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }.start()
        }

        btnR.setOnClickListener {
            //pgsBar.setProgress(0);
            pgsBar.progress = 0
            txtView.text = 0.toString() + "/" + pgsBar.max
            Toast.makeText(this,"Progress bar Reset Succefully", Toast.LENGTH_SHORT).show()
        }
    }

}
