package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.RemoteInput
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Icon
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews

class P11Notification : AppCompatActivity() {

    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    lateinit var btnNotify: Button
    lateinit var remoteCollapsedViews: RemoteViews
    lateinit var remoteExpandedViews: RemoteViews
    lateinit var pendingIntent: PendingIntent
    lateinit var soundUri: Uri
    lateinit var audioAttr: AudioAttributes
     lateinit var remoteInput: RemoteInput
    private val channelId = "My Channel Id"
    private val description = "Test Notification"
    private val title = "Notification"

      val myKey = "Remote Key"
    val notificationId = 1234
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p11_notification)
        btnNotify = findViewById(R.id.btnNotify)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        btnNotify.setOnClickListener {
            val intent = Intent(this,P11NotificationView::class.java)
            pendingIntent = PendingIntent.getActivity(this,0,intent,
                PendingIntent.FLAG_MUTABLE)//

            //Setting Custom Sound for Notification
            soundUri = Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+
                        applicationContext.packageName+"/"+R.raw.alarm)

                //Defining Audio Attributes
            audioAttr = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
//1
          // remoteCollapsedViews = RemoteViews(packageName, R.layout.activity_splash_screen_ex_one)
          //remoteExpandedViews = RemoteViews(packageName, R.layout.activity_splash_screen_ex_two)
            myNotificationChannel()
     //2 : for reply as whats app , changes in another file also P11NotificationView(uncomment line no 22-24) and comment : 25
  /*      remoteInput = RemoteInput.Builder(myKey).setLabel("Replying...")
                .build()
            val action: Notification.Action =
                Notification.Action.Builder(R.drawable.check, "Reply", pendingIntent)
                    .addRemoteInput(remoteInput).build()
            builder.addAction(action)
*/
            //Displaying the Notification
            notificationManager.notify(notificationId,builder.build())
        }
    }

    private fun myNotificationChannel() {
/*Only runs on Android 8.0+ (API 26+).
Creates a notification channel using channelId.
Enables LED light notifications (enableLights(true)).
Disables vibration (enableVibration(false)).
Sets the custom sound for notifications (setSound(soundUri, audioAttr)).
Registers the channel with notificationManager.createNotificationChannel(notificationChannel).
*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationChannel.setSound(soundUri,audioAttr)
            notificationManager.createNotificationChannel(notificationChannel)
/*Building the Notification
Uses Notification.Builder(this, channelId), linking it to the channel.
Sets the small icon, title, text, and large icon.
setAutoCancel(true): Dismisses the notification when clicked.
 */
            builder = Notification.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_baseline_announcement)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.logo_toolbar))
                .setContentIntent(pendingIntent)
//4: for text enlarge

        /*       .setStyle(
                     Notification.BigTextStyle()
                         .bigText("The road may be long, tortuous and wearied. But the resulting success is enduring, sure and sweet. The fool abandons hope in the wearied journey of life. The wise gets going - holding firmly to the promise\n" +
                                 "  of a better tomorrow. He that gives up too soon fails to understand that life rewards with success\n" +
                                 "  only those who cling on to hope against hope. Those who hope when it is unfashionable to hope.")
                         )
*/
                //3 : for image enlarge
              /*  .setStyle(Notification.BigPictureStyle()
                   .bigPicture(BitmapFactory.decodeResource(this.resources,R.drawable.logo_toolbar))
                   .bigLargeIcon(null as Icon?))*/
//1 : give different pic when expand
        //        .setCustomContentView(remoteCollapsedViews)
          //      .setCustomBigContentView(remoteExpandedViews)

                .setAutoCancel(true)

        }

        else{
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_baseline_announcement)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.logo_toolbar))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }
    }
}
/*
In Android notifications, Channel ID and Notification ID serve different purposes:

Channel ID:

Used for notification channels introduced in Android 8.0 (API level 26).
Helps group notifications into categories (e.g., "Messages", "Promotions", "Updates").
Set when creating a NotificationChannel.
Remains the same for all notifications that belong to that channel.
Required to post a notification.

Notification ID:

A unique identifier (integer) for each notification.
Used to update or cancel a specific notification.
If you reuse the same ID, the existing notification is updated instead of creating a new one.
Used in NotificationManager.notify(notificationId, notification).

// Creating a notification channel (Only needed for API 26+)
String channelId = "my_channel"; // Channel ID
NotificationChannel channel = new NotificationChannel(channelId,
"My Channel", NotificationManager.IMPORTANCE_HIGH);
notificationManager.createNotificationChannel(channel);

// Posting a notification
int notificationId = 1001; // Unique notification ID
Notification notification = new NotificationCompat.Builder
                                (context, channelId)
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle("New Message")
        .setContentText("You have a new message!")
        .build();

notificationManager.notify(notificationId, notification);
*/


