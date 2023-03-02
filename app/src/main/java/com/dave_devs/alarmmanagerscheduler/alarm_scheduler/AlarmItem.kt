package com.dave_devs.alarmmanagerscheduler.alarm_scheduler

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val message: String
)
