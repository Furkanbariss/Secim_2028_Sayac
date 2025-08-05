package com.furkanbarissonmezisik.secim2028sayac

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.google.android.gms.ads.MobileAds
import com.furkanbarissonmezisik.secim2028sayac.AdMobBanner
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.layout.ContentScale
import androidx.glance.appwidget.updateAll
import com.furkanbarissonmezisik.secim2028sayac.widget.Secim2028ExtendedWidget

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {}


        setContent {
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                Secim2028ExtendedWidget().updateAll(context)
            }
            val themeState = rememberThemeState()
            Secim2028SayacTheme(darkTheme = themeState.isDark) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "countdown_screen") {
                   /*
                    composable("splash_screen") {
                        SplashScreen(navController = navController)
                    }*/
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
/* Bu ksım iptal edilmiştir
@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(true) {
        kotlinx.coroutines.delay(50) // 1 saniye bekle
        navController.navigate("countdown_screen") {
            popUpTo("splash_screen") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "SEÇİM SAYAÇ",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
*/
@Composable
fun CountdownScreen(navController: NavController) {
    var remainingMillis by remember { mutableLongStateOf(0L) }
    var targetDatePassed by remember { mutableLongStateOf(0L) }

    DisposableEffect(Unit) {
        val targetCalendar = Calendar.getInstance().apply {
            set(2028, Calendar.JUNE, 14, 7, 0, 0)
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
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val screenWidth = maxWidth
            val x = null

            // 1. Arka plan görseli
            Image(
                painter = painterResource(id = R.drawable.main_background), // kendi dosya adınla değiştir
                contentDescription = "Arka Plan",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigate("info_screen") }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Bilgi",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    IconButton(onClick = { navController.navigate("settings_screen") }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Ayarlar",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                /*
                Image(
                    painter = painterResource(id = R.drawable.turkey_flag),
                    contentDescription = "Türk Bayrağı",
                    modifier = Modifier.size(width = 60.dp, height = 40.dp)
                )
                */
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "2028",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "CUMHURBAŞKANLIĞI SEÇİMİ",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Seçim tarihi: 14 Haziran 2028",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Geriye kalan süre",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

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
                            else -> CountdownDisplay(remainingMillis)
                        }
                    }
                }
            }

            AdMobBanner(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 50.dp)
            )
        }
    }
}

@Composable
fun CountdownDisplay(millisUntilFinished: Long) {
    val currentCalendar = Calendar.getInstance()
    val targetCalendar = Calendar.getInstance().apply {
        set(2028, Calendar.JUNE, 14, 0, 0, 0)
        set(Calendar.MILLISECOND, 0)
    }

    var diffYears = targetCalendar.get(Calendar.YEAR) - currentCalendar.get(Calendar.YEAR)
    var diffMonths = targetCalendar.get(Calendar.MONTH) - currentCalendar.get(Calendar.MONTH)
    var diffDays =
        targetCalendar.get(Calendar.DAY_OF_MONTH) - currentCalendar.get(Calendar.DAY_OF_MONTH)

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

    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f), // daha opak hale getirdik
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 4.dp,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = String.format("%02d %02d %02d %02d", diffYears, diffMonths, diffDays, hours),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.width(250.dp)
            ) {
                listOf("YIL", "AY", "GÜN", "SAAT").forEach {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = String.format("%02d:%02d", minutes, seconds),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.width(150.dp)
            ) {
                listOf("DAKİKA", "SANİYE").forEach {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
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
