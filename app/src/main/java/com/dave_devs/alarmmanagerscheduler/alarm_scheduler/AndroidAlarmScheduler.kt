package com.dave_devs.alarmmanagerscheduler.alarm_scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.ZoneId

class AndroidAlarmScheduler(
    private val context: Context
): AlarmScheduler {
    //Reference to the Alarm Manager coming from the OS
    //We now use it to schedule the alarm.
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    //Function to schedule alarm
    override fun schedule(item: AlarmItem) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", item.message)
        }
        //setExactAndAllowWhileIdle -> set the alarm to user exact specified time and allow while in low power mode.
        alarmManager.setExactAndAllowWhileIdle(
            //type = RTC_WAKEUP -> Wakes the device when it triggers
            AlarmManager.RTC_WAKEUP,
            /*
            * triggerAtMillis = Time to which user want the alarm to triggers
                THIS GET THE ZONE_ID OF THE USER FROM SYSTEM(Device) AND CALCULATE
            *   THE NUMBER OF EPOCH_SECOND IN IT AND MULTIPLY IT BY A 1000
            */
            item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            //Pending Intent/Message that goes off when alarm triggers
            PendingIntent.getBroadcast(
                /* context = */ context,
                /* requestCode = */ item.hashCode(),
                /* intent = */ intent,
                /* flags = */ PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    //Function to cancel alarm
    override fun cancel(item: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}