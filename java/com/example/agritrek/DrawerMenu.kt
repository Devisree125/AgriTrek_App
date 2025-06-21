package com.example.agritrek


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DrawerMenu : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_menu)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)

        // Open drawer when toolbar nav icon is clicked
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Handle navigation item clicks
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_language -> openLanguageSettings()
                R.id.nav_theme -> showToast("Theme settings coming soon!")
                R.id.nav_permissions -> openAppPermissions()
                R.id.nav_contact -> openEmail()
                R.id.nav_feedback -> openEmail("Feedback")
                R.id.nav_rate -> openPlayStore()
                R.id.nav_faq -> showToast("FAQ coming soon!")
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun openLanguageSettings() {
        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
    }

    private fun openAppPermissions() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    private fun openEmail(subject: String = "Contact") {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:example@email.com")
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        startActivity(emailIntent)
    }

    private fun openPlayStore() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
        startActivity(intent)
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
