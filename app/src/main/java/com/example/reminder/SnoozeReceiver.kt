package com.example.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class SnoozeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val delay = intent.getLongExtra("delay", 0L)
        val increment = intent.getBooleanExtra("increment", false)

        val scheduler = ReminderScheduler(context)
        if (increment) {
            scheduler.scheduleInMillis(delay)
        } else {
            scheduler.scheduleInMillis(delay)
        }
    }
}
