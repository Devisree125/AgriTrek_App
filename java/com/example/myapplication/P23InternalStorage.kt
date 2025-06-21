package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.FileInputStream
import java.io.FileOutputStream

class P23InternalStorage : AppCompatActivity() {
    lateinit var write:Button
    lateinit var name:EditText
    lateinit var tvread:TextView
    lateinit var readbutton:Button
    val filename="FileDemo"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p23_internal_storage)
        write=findViewById(R.id.write)
        name=findViewById(R.id.name)
        readbutton=findViewById(R.id.buttonRead)
        tvread=findViewById(R.id.read)
        readbutton.setOnClickListener {
            val data=takeData()
            tvread.text=data
        }
        write.setOnClickListener {
            try {
                val fos: FileOutputStream = openFileOutput(filename, MODE_PRIVATE)
                var data=name.text.toString()
                fos.write(data.toByteArray())
                Toast.makeText(this,"data is saved in file",Toast.LENGTH_SHORT).show()
                fos.close()
            }
            catch (e:Exception)
            {
                Toast.makeText(this,e.printStackTrace().toString(),Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun takeData():String{
        return try {
            val fis: FileInputStream =openFileInput(filename)
            val b=fis.readBytes()
            fis.close()
            String(b)
        }
        catch(e:Exception)
        {
            e.printStackTrace()
            "No Data Avail"
        }
    }
}