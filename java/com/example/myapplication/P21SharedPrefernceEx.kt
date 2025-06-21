package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// to find Shared Preference are saved go to
// Device File Explorer --> data --> data -->
// "Your app package name " (like my app project name is com.example.myapplication)
// --> shared_prefs-->myrefernce NAME(mypref)
class P21SharedPrefernceEx : AppCompatActivity() {
    lateinit var sharedpreferences1: SharedPreferences
    lateinit var name: TextView
    lateinit var email: TextView
    val mypreference = "mypref"
    val Name = "nameKey"
    val Email = "emailKey"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p21_shared_prefernce_ex)
        name = findViewById(R.id.etName)
        email = findViewById(R.id.etEmail)
        //        Fetches the SharedPreferences instance using the name mypreference.
//        The Context.MODE_PRIVATE mode ensures that the shared preferences file is only accessible within the app.
           sharedpreferences1 = getSharedPreferences(mypreference, Context.MODE_PRIVATE)
         name.text = sharedpreferences1.getString(Name, "")
        email.text = sharedpreferences1.getString(Email, "") }
        fun save(view: View?) {
            val n = name.text.toString()
            val e = email.text.toString()
            //Calls edit() on sharedpreferences, which returns a SharedPreferences.Editor instance.
            //This editor allows us to modify the stored data.
            val editor = sharedpreferences1.edit()
            //stores the value of n under the key Name in SharedPreference
            editor.putString(Name, n)
            editor.putString(Email, e)
            editor.apply()
//apply() is preferred over commit() because:
//apply() saves the data in the background (non-blocking).
//apply() Saves the changes asynchronously.
//commit() is synchronous and blocks the main thread, which can slow down the UI.

        }

        fun clear(view: View?) {
            name.text = ""
            email.text = ""
        }

        fun get(view: View) {
            sharedpreferences1 = getSharedPreferences(mypreference, Context.MODE_PRIVATE)

            name.text = sharedpreferences1.getString(Name, "")
            email.text = sharedpreferences1.getString(Email, "")

        }
    }

