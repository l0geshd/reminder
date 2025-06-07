package com.example.reminder

import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timePicker: TimePicker = findViewById(R.id.timePicker)
        val setButton: Button = findViewById(R.id.setReminderButton)

        setButton.setOnClickListener {
            val hour = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                timePicker.hour
            } else {
                @Suppress("DEPRECATION")
                timePicker.currentHour
            }
            val minute = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                timePicker.minute
            } else {
                @Suppress("DEPRECATION")
                timePicker.currentMinute
            }
            val scheduler = ReminderScheduler(this)
            scheduler.scheduleDaily(hour, minute)
        }
    }
}
