package com.dave_devs.alarmmanagerscheduler.alarm_scheduler

import com.dave_devs.alarmmanagerscheduler.alarm_scheduler.AlarmItem

interface AlarmScheduler {
    fun schedule(item: AlarmItem)
    fun cancel(item: AlarmItem)
}