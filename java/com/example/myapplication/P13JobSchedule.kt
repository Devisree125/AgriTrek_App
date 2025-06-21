package com.example.myapplication

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
/*
JobScheduler is an API introduced in Android 5.0 (API level 21) that allows you to schedule
 background tasks efficiently. It ensures that jobs run under optimal conditions, like when
  the device is charging, connected to WiFi, or idle.

 Why Use JobScheduler?
    Runs jobs efficiently based on device conditions.
    Works even when the app is not running.
    Avoids wake locks (unlike AlarmManager).
    Supports network constraints (e.g., only run on WiFi).
    Allows periodic or one-time scheduling.
    Survives reboots (with setPersisted(true)).

How JobScheduler Works
    Jobs run asynchronously in the background.
    Android batches jobs together to save battery.
    Jobs are persisted across reboots (if configured).
 */
class P13JobSchedule : AppCompatActivity() {
    var jobScheduler: JobScheduler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_p13_job_schedule)
        val stopJob = findViewById<Button>(R.id.stopJob)
        val startJob = findViewById<Button>(R.id.startJob)

        startJob.setOnClickListener {
            jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            val componentName = ComponentName(this,P13MyJobService::class.java)
            val builder = JobInfo.Builder(123, componentName)
            builder.setMinimumLatency(8000)//8 seconds delay before the job can start
            builder.setOverrideDeadline(10000)//10 seconds max wait time before forcing execution
            builder.setPersisted(true)//The job will persist across device reboots
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)// service  runs on any network
            builder.setRequiresCharging(false)//an run even if the device is not charging
            jobScheduler!!.schedule(builder.build())// job is finally scheduled
        }
        stopJob.setOnClickListener {
            if (jobScheduler != null) {
                jobScheduler!!.cancel(123)
                jobScheduler = null
                Toast.makeText(this, "Job Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}