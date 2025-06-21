package com.example.agritrek

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class login : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPref = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        // Check if already logged in
        val savedUsername = sharedPref.getString("username", "")
        val savedPassword = sharedPref.getString("password", "")
        if (!savedUsername.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        // Load saved credentials (optional)
        loadSavedCredentials()

        // Login button click logic
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                showSavePasswordDialog(username, password)
            } else {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadSavedCredentials() {
        val savedUsername = sharedPref.getString("username", "")
        val savedPassword = sharedPref.getString("password", "")
        usernameEditText.setText(savedUsername)
        passwordEditText.setText(savedPassword)
    }

    private fun showSavePasswordDialog(username: String, password: String) {
        AlertDialog.Builder(this)
            .setTitle("Save Password?")
            .setMessage("Do you want to save your login details?")
            .setPositiveButton("Yes") { _, _ ->
                sharedPref.edit().apply {
                    putString("username", username)
                    putString("password", password)
                    apply()
                }
                Toast.makeText(this, "Credentials Saved", Toast.LENGTH_SHORT).show()
                goToMain()
            }
            .setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                goToMain()
            }
            .show()
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
