package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

class P24ExternalDetails : AppCompatActivity() {
    lateinit var fstream: FileInputStream
    private val filename = "SampleFile.txt"
    private val filepath = "MyFileStorage"
    lateinit var myExternalFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p24_external_details)
        val result = findViewById<TextView>(R.id.resultView)
        val back = findViewById<Button>(R.id.btnBack)
        try {
            myExternalFile = File(getExternalFilesDir(filepath), filename)
            fstream = FileInputStream(myExternalFile)
            val sbuffer = StringBuilder()
            var char = fstream.read()
            while (char != -1) {
                sbuffer.append(char.toChar())
                char = fstream.read()
            }
            fstream.close()
            val details = sbuffer.toString().split("\n").toTypedArray()
            var n = details.size
            var text = " "
            for (i in 0 until n-1) {
                text += "${details[i]}" + " "
            }
            result.text = """
                Name: "$text"
                Password: ${details[n-1]}
                """.trimIndent()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        back.setOnClickListener {
            intent = Intent(this, P24ExternalStorage::class.java)
            startActivity(intent)
        }
    }
}