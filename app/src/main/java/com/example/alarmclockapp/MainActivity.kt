package com.example.alarmclockapp

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.children
import java.util.Calendar
import androidx.compose.runtime.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                    MainAlarmApp()

            }
        }
    }

@Composable
fun MainAlarmApp(){
    Scaffold(
        topBar = {
            AppHeader()
        }
    ){ paddingValues ->
        AlarmSetupScreen(
            uiModifier = Modifier.padding(paddingValues)
        )
    }
}

//Displays the app name
@Composable
private fun AppHeader(
    uiModifier: Modifier = Modifier
){
    Row(
        modifier = uiModifier
            .fillMaxWidth()
            .height(65.dp)
            .shadow(elevation = 4.dp)
            .background(Color(0xFF1565C0)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.padding(start = 8.dp),
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
    }
}


//Contains the time picker, alarm message field, and set alarm button
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
private fun AlarmSetupScreen(
    uiModifier: Modifier = Modifier
) {
    // Define a consistent blue color theme
    val mainBlueColor = MaterialTheme.colorScheme.primary
    val deepBlueColor = Color(0xFF1565C0)

    Box(modifier = uiModifier) {
        val appContext = LocalContext.current
        val initialHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val initialMinute = Calendar.getInstance().get(Calendar.MINUTE)
        val alarmTimeState = rememberTimePickerState(
            initialHour, initialMinute, is24Hour = true
        )

        // State for the alarm message text field
        var wakeupMessage by remember { mutableStateOf("") }

        val isButtonHighlighted = remember { mutableStateOf(false) }
        val btnElevationSize by animateDpAsState(
            targetValue = if (isButtonHighlighted.value) 8.dp else 4.dp,
            animationSpec = spring(stiffness = Spring.StiffnessLow),
            label = "btnElevation"
        )
        val btnBackgroundColor by animateColorAsState(
            targetValue = if (isButtonHighlighted.value)
                MaterialTheme.colorScheme.primaryContainer
            else
                mainBlueColor,
            label = "buttonColor"
        )

        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(
                modifier = Modifier
                    .height(24.dp),
            )

            val displayHour = if (alarmTimeState.hour < 10) "0${alarmTimeState.hour}" else alarmTimeState.hour.toString()
            val displayMinute = if (alarmTimeState.minute < 10) "0${alarmTimeState.minute}" else alarmTimeState.minute.toString()

            Text(
                text = "Selected Time: ${displayHour}:${displayMinute}",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(
                modifier = Modifier
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.8f)
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    TimePicker(
                        state = alarmTimeState,
                        colors = TimePickerDefaults.colors(
                            containerColor = deepBlueColor.copy(alpha = 0.1f),
                            clockDialColor = deepBlueColor.copy(alpha = 0.3f),
                            clockDialSelectedContentColor = Color.White,
                            clockDialUnselectedContentColor = Color.DarkGray,
                            selectorColor = deepBlueColor,
                            periodSelectorBorderColor = deepBlueColor,
                            periodSelectorSelectedContainerColor = deepBlueColor,
                            periodSelectorUnselectedContainerColor = deepBlueColor.copy(alpha = 0.2f),
                            periodSelectorSelectedContentColor = Color.White,
                            periodSelectorUnselectedContentColor = Color.DarkGray,
                            timeSelectorSelectedContainerColor = deepBlueColor,
                            timeSelectorUnselectedContainerColor = deepBlueColor.copy(alpha = 0.2f),
                            timeSelectorSelectedContentColor = Color.White,
                            timeSelectorUnselectedContentColor = Color.DarkGray
                        )
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(8.dp),
            )

            Button(
                onClick = { scheduleAlarm(appContext, alarmTimeState.hour, alarmTimeState.minute, "Time to wake up !!") },
                modifier = Modifier
                    .padding(16.dp)
                    .height(56.dp)
                    .fillMaxWidth(0.7f)
                    .shadow(btnElevationSize, RoundedCornerShape(28.dp)),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = btnBackgroundColor
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "⏰ ",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "SET ALARM",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


fun scheduleAlarm(appContext: Context, alarmHour: Int, alarmMinute: Int, alarmMessage: String) {
    val alarmIntent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
        putExtra(AlarmClock.EXTRA_HOUR, alarmHour)
        putExtra(AlarmClock.EXTRA_MINUTES, alarmMinute)
        putExtra(AlarmClock.EXTRA_MESSAGE, alarmMessage)
    }

    try {
        appContext.startActivity(alarmIntent)
        Toast.makeText(appContext, "Alarm set for $alarmHour:$alarmMinute", Toast.LENGTH_SHORT).show()
    } catch (exception: ActivityNotFoundException) {
        Toast.makeText(appContext, "No alarm app available", Toast.LENGTH_SHORT).show()
    }
}


@Preview(showBackground = true)
@Composable
fun SimpleAlarmPreview(){
            MainAlarmApp()
}