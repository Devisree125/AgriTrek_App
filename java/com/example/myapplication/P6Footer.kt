package com.example.myapplication

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class P6Footer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p6_footer)

        val list1=findViewById<ListView>(R.id.list1)
        val arr= arrayOf(
            "MP","SP","Tusharika","Subhita","Surbhi","Reena","Ram","Sham","Sita","Anuj"
        )
        // adapter connects a data source to a user interface like list,spinner etc
        val mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)
        // Inflating the Footer file
        val mFooter = layoutInflater.inflate(R.layout.p6footerlayout, list1, false) as ViewGroup
        // Adding the footer to the ListView
        list1.addFooterView(mFooter)
        // Setting the adapter
        list1.adapter = mAdapter
    }
}