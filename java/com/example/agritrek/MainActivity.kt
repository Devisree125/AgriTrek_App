package com.example.agritrek

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.VoiceInteractor
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.navigation.NavigationView
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.*
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var navView: NavigationView
    private lateinit var imageUri: Uri
    private lateinit var photoFile: File
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var tvLocation: TextView
    private lateinit var tvWeatherDetails: TextView
    private lateinit var ivWeatherIcon: ImageView
    private lateinit var progressBarWeather: ProgressBar
    private var cameraRequestType = ""

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_CAMERA_PERMISSION = 1002
    private val REQUEST_LOCATION_PERMISSION = 1003

    private val API_KEY = "5b64a46e61c86dcc932fea7a8656a042"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tvWatermark).text = "android-grp8"

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        navView = findViewById(R.id.navigation_view)
        tvLocation = findViewById(R.id.tvLocation)
        tvWeatherDetails = findViewById(R.id.tvWeatherDetails)
        ivWeatherIcon = findViewById(R.id.ivWeatherIcon)
        progressBarWeather = findViewById(R.id.progressBarWeather)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_language -> {
                    Toast.makeText(this, "Language settings clicked", Toast.LENGTH_SHORT).show()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_theme -> {
                    Toast.makeText(this, "Theme settings clicked", Toast.LENGTH_SHORT).show()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_permissions -> {
                    startActivity(Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.parse("package:$packageName")
                    })
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_contact -> {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:1234567890")
                    startActivity(intent)
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_feedback -> {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:")
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("support@example.com"))
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for AgriTrek")
                    startActivity(intent)
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_rate -> {
                    val uri = Uri.parse("market://details?id=$packageName")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_faq -> {
                    startActivity(Intent(this, FAQ::class.java))
                    drawerLayout.closeDrawers()
                    return@setNavigationItemSelectedListener true
                }
                else -> return@setNavigationItemSelectedListener false
            }
        }



        findViewById<CardView>(R.id.geolocationCard).setOnClickListener {
            requestLocationAndWeather()
        }

        findViewById<CardView>(R.id.btnIdentify).setOnClickListener {
            cameraRequestType = "identify"
            dispatchTakePictureIntent()
        }

        findViewById<CardView>(R.id.btnDiagnose).setOnClickListener {
            cameraRequestType = "diagnose"
            dispatchTakePictureIntent()
        }

        findViewById<CardView>(R.id.btnReminder).setOnClickListener {
            startActivity(Intent(this, AddReminderActivity::class.java))
        }

        findViewById<CardView>(R.id.btnDisease).setOnClickListener {
            val disease = Disease(
                name = "Leaf Spot",
                scientificName = "Cercospora spp.",
                imageResId = R.drawable.disease,
                description = "Fungal disease causing small brown or black spots on leaves.",
                solution = "Use a fungicide, remove infected leaves, and avoid overhead watering."
            )
            val intent = Intent(this, activity_disease_list::class.java)
            intent.putExtra("disease", disease)
            startActivity(intent)
        }
    }

    private fun requestLocationAndWeather() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            getLocationAndWeather()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocationAndWeather() {
        progressBarWeather.visibility = View.VISIBLE
        ivWeatherIcon.visibility = View.GONE
        tvLocation.text = "Fetching weather..."
        tvWeatherDetails.text = ""

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val lat = location.latitude
                    val lon = location.longitude
                    fetchWeather(lat, lon)
                } else {
                    progressBarWeather.visibility = View.GONE
                    ivWeatherIcon.visibility = View.VISIBLE
                    tvLocation.text = "Unable to get location"
                    tvWeatherDetails.text = "Tap to retry"
                }
            }
            .addOnFailureListener {
                progressBarWeather.visibility = View.GONE
                ivWeatherIcon.visibility = View.VISIBLE
                tvLocation.text = "Location fetch failed"
                tvWeatherDetails.text = "Tap to retry"
            }
    }

    private fun fetchWeather(lat: Double, lon: Double) {
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=$API_KEY&units=metric"
        val client = OkHttpClient()
        val request = Request
            .Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    progressBarWeather.visibility = View.GONE
                    ivWeatherIcon.visibility = View.VISIBLE
                    tvLocation.text = "Failed to fetch weather"
                    tvWeatherDetails.text = "Tap to retry"
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonData = response.body?.string()
                if (jsonData != null) {
                    val jsonObject = JSONObject(jsonData)
                    val cityName = jsonObject.getString("name")
                    val weather = jsonObject.getJSONArray("weather").getJSONObject(0)
                    val weatherDesc = weather.getString("description")
                    val weatherMain = weather.getString("main").toLowerCase()
                    val temp = jsonObject.getJSONObject("main").getDouble("temp")

                    runOnUiThread {
                        progressBarWeather.visibility = View.GONE
                        ivWeatherIcon.visibility = View.VISIBLE
                        tvLocation.text = cityName
                        tvWeatherDetails.text = "$weatherDesc, $temp°C"

                        // Set weather icon based on condition
                        ivWeatherIcon.setImageResource(
                            when (weatherMain) {
                                "clear" -> R.drawable.img_28
                                "clouds" -> R.drawable.img_29
                                "rain" -> R.drawable.img_30
                                "thunderstorm" -> R.drawable.img_31
                                "snow" -> R.drawable.img_32
                                else -> R.drawable.img_33
                            }
                        )
                    }
                }
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    launchCamera()
                } else {
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_LOCATION_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocationAndWeather()
                } else {
                    tvLocation.text = "Location permission denied"
                    tvWeatherDetails.text = "Tap to enable location"
                }
            }
        }
    }



    private fun dispatchTakePictureIntent() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        } else {
            launchCamera()
        }
    }

    private fun launchCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            photoFile = createImageFile()
            imageUri = FileProvider.getUriForFile(
                this,
                "${applicationContext.packageName}.provider",
                photoFile
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        } else {
            Toast.makeText(this, "Camera not available on this device", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir = getExternalFilesDir("Pictures")
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("imageUri", imageUri.toString())
                when (cameraRequestType) {
                    "identify" -> {
                        putExtra("plantName", "Neem Plant")
                        putExtra(
                            "description",
                            "Neem is a fast-growing tree that can reach a height of 15–20 meters. It has antifungal, antibacterial, and anti-inflammatory properties."
                        )
                    }
                    "diagnose" -> {
                        putExtra("plantName", "Sick Plant")
                        putExtra("description", "The plant shows signs of Leaf Spot disease.")
                        putExtra("cause", "Caused by the fungus Cercospora spp.")
                        putExtra("solution", "Use a fungicide and remove affected leaves.")
                    }
                }
            }
            startActivity(intent)
        }
    }
    // ... [Your full MainActivity code above remains unchanged]

// Add this below your `onActivityResult` function

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_notification -> {
                // ✅ Launch the NotificationsActivity
                val intent = Intent(this, Notifications::class.java)
                startActivity(intent)
                true
            }
            R.id.action_settings -> {
                startActivity(Intent(this, Settings::class.java)) // 👈 Add this line here
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}