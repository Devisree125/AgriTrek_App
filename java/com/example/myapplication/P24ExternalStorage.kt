package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class P24ExternalStorage : AppCompatActivity() {
    lateinit var uname: EditText
    lateinit var pwd: EditText
    lateinit var saveBtn: Button
    lateinit var fstream: FileOutputStream
    private val filename = "SampleFile.txt"
    private val filepath = "MyFileStorage"
    lateinit var myExternalFile: File
   var mPermission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_p24_external_storage)
        uname = findViewById(R.id.txtName)
        pwd = findViewById(R.id.txtPwd)
        saveBtn = findViewById(R.id.btnSave)
        saveBtn.setOnClickListener {
            val username = """
                ${uname.text}
                
                """.trimIndent()
            val password = pwd.text.toString()
            try {
                    if (username.isEmpty() || password.isEmpty())
                        Toast.makeText(this, "either of field is empty", Toast.LENGTH_SHORT).show()
                    else {
                        myExternalFile = File(getExternalFilesDir(filepath), filename)
                        fstream = FileOutputStream(myExternalFile)
                        fstream.write(username.toByteArray())
                        fstream.write(password.toByteArray())
                        fstream.close()
                        Toast.makeText(applicationContext, "Details Saved in " +
                                myExternalFile!!.absolutePath, Toast.LENGTH_LONG).show()
                        intent = Intent(this,
                            P24ExternalDetails::class.java)
                        startActivity(intent)
                    }
             //   }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}