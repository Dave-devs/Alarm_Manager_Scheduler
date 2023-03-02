package com.dave_devs.alarmmanagerscheduler.alarm_scheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver: BroadcastReceiver() {
    //This is the call-back function that get called whenever our alarm is triggered.
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        println("Alarm Triggered: $message")
    }

}