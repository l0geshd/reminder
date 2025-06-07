package com.example.reminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        createChannel(context)

        val snoozeIntent5 = Intent(context, SnoozeReceiver::class.java).apply {
            putExtra("delay", 5 * 60 * 1000L)
        }
        val snooze5 = PendingIntent.getBroadcast(
            context,
            1,
            snoozeIntent5,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val snoozeIntent30 = Intent(context, SnoozeReceiver::class.java).apply {
            putExtra("delay", 30 * 60 * 1000L)
        }
        val snooze30 = PendingIntent.getBroadcast(
            context,
            2,
            snoozeIntent30,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val incrementIntent = Intent(context, SnoozeReceiver::class.java).apply {
            putExtra("delay", 60 * 60 * 1000L)
            putExtra("increment", true)
        }
        val increment = PendingIntent.getBroadcast(
            context,
            3,
            incrementIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .setContentTitle("Reminder")
            .setContentText("It's time for your task")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .addAction(0, "Snooze 5m", snooze5)
            .addAction(0, "Snooze 30m", snooze30)
            .addAction(0, "+1 hr", increment)
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(1001, notification)
        }
    }

    private fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Reminders", NotificationManager.IMPORTANCE_HIGH)
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "reminder_channel"
    }
}
