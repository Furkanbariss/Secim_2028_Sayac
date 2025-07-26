package com.furkanbarissonmezisik.secim2028sayac

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.furkanbarissonmezisik.secim2028sayac.ui.theme.Secim2028SayacTheme
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeState = rememberThemeState()

            Secim2028SayacTheme(darkTheme = themeState.isDark) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "countdown_screen") {
                    composable("countdown_screen") {
                        CountdownScreen(navController = navController)
                    }
                    composable("settings_screen") {
                        SettingsScreen(navController = navController, themeState = themeState)
                    }
                    composable("info_screen") {
                        InfoScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun CountdownScreen(navController: NavController) {
    var remainingMillis by remember { mutableLongStateOf(0L) }
    var targetDatePassed by remember { mutableLongStateOf(0L) }

    DisposableEffect(Unit) {
        val targetCalendar = Calendar.getInstance().apply {
            set(2028, Calendar.MAY, 14, 0, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val currentTimeMillis = System.currentTimeMillis()
        val targetTimeMillis = targetCalendar.timeInMillis

        if (targetTimeMillis <= currentTimeMillis) {
            targetDatePassed = 1L
            onDispose { }
            return@DisposableEffect onDispose {}
        }

        val totalTimeUntilFinished = targetTimeMillis - currentTimeMillis
        remainingMillis = totalTimeUntilFinished

        val timer = object : CountDownTimer(totalTimeUntilFinished, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingMillis = millisUntilFinished
            }

            override fun onFinish() {
                remainingMillis = 0L
                targetDatePassed = 2L
            }
        }.start()

        onDispose { timer.cancel() }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 36.dp, end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { navController.navigate("info_screen") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Bilgi",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Button(
                    onClick = { navController.navigate("settings_screen") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Ayarlar",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "2028 CUMHURBAŞKANLIĞI SEÇİMİ",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.turkey_flag),
                    contentDescription = "Türk Bayrağı",
                    modifier = Modifier.size(width = 80.dp, height = 50.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Geriye kalan süre",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(32.dp))

                when (targetDatePassed) {
                    1L -> Text(
                        text = "Hedef Tarih Geçti!",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    2L -> Text(
                        text = "Geri Sayım Tamamlandı!",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    else -> {
                        CountdownDisplay(remainingMillis)
                    }
                }
            }
        }
    }
}

@Composable
fun CountdownDisplay(millisUntilFinished: Long) {
    val currentCalendar = Calendar.getInstance()
    val targetCalendar = Calendar.getInstance().apply {
        set(2028, Calendar.MAY, 14, 0, 0, 0)
        set(Calendar.MILLISECOND, 0)
    }

    var diffYears = targetCalendar.get(Calendar.YEAR) - currentCalendar.get(Calendar.YEAR)
    var diffMonths = targetCalendar.get(Calendar.MONTH) - currentCalendar.get(Calendar.MONTH)
    var diffDays = targetCalendar.get(Calendar.DAY_OF_MONTH) - currentCalendar.get(Calendar.DAY_OF_MONTH)

    if (diffDays < 0) {
        diffMonths--
        val prevMonthCalendar = currentCalendar.clone() as Calendar
        prevMonthCalendar.add(Calendar.MONTH, -1)
        diffDays += prevMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }
    if (diffMonths < 0) {
        diffYears--
        diffMonths += 12
    }

    val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = String.format("%01d:%02d:%02d", diffYears, diffMonths, diffDays),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 56.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.width(280.dp)
        ) {
            listOf("YIL", "AY", "GÜN").forEach {
                Text(text = it, color = MaterialTheme.colorScheme.onBackground, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = String.format("%02d:%02d:%02d", hours, minutes, seconds),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 56.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.width(280.dp)
        ) {
            listOf("SAAT", "DAKİKA", "SANİYE").forEach {
                Text(text = it, color = MaterialTheme.colorScheme.onBackground, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Secim2028SayacTheme {
        val navController = rememberNavController()
        CountdownScreen(navController = navController)
    }
}
