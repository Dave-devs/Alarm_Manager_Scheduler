package com.dave_devs.alarmmanagerscheduler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dave_devs.alarmmanagerscheduler.alarm_scheduler.AlarmItem
import com.dave_devs.alarmmanagerscheduler.alarm_scheduler.AndroidAlarmScheduler
import com.dave_devs.alarmmanagerscheduler.ui.theme.AlarmManagerSchedulerTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scheduler = AndroidAlarmScheduler(this)
        var alarmItem: AlarmItem? = null
        setContent {
            AlarmManagerSchedulerTheme {
                var secondsText by remember { mutableStateOf("") }
                var message by remember { mutableStateOf("") }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = secondsText,
                        onValueChange = {secondsText = it},
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text( text = "Trigger alarm in seconds" ) }
                    )
                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = message,
                        onValueChange = {message = it} ,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text( text = "Message" ) }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Button(onClick = { alarmItem =
                            AlarmItem(
                                time = LocalDateTime.now().plusSeconds(secondsText.toLong()),
                                message = message
                        )
                            alarmItem?.let(scheduler::schedule)
                            secondsText = ""
                            message = ""
                        }
                        ) {
                            Text(
                                text = "Schedule"
                            )
                        }
                        Spacer(Modifier.width(8.dp))

                        Button(onClick = {
                            alarmItem?.let(scheduler::cancel)

                        }) {
                            Text(
                                text = "Cancel"
                            )
                        }
                    }
                }
            }
        }
    }
}