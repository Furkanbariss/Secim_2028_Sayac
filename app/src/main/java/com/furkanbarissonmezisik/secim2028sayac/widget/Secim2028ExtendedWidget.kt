package com.furkanbarissonmezisik.secim2028sayac.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import java.time.LocalDateTime
import java.time.Duration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Secim2028ExtendedWidget : GlanceAppWidget() {
    override val sizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val targetDate = LocalDateTime.of(2028, 6, 23, 8, 0)
        val now = LocalDateTime.now()
        val duration = Duration.between(now, targetDate)

        val totalSeconds = duration.seconds
        val days = totalSeconds / (24 * 3600)
        val hours = (totalSeconds % (24 * 3600)) / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        val years = days / 365
        val months = (days % 365) / 30
        val remainingDays = (days % 365) % 30

        provideContent {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(ColorProvider(Color(0xFF228B22)))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Seçime Kalan Süre:",
                        style = TextStyle(color = ColorProvider(Color.White))
                    )
                    Text(
                        text = "$years yıl $months ay $remainingDays gün",
                        style = TextStyle(
                            color = ColorProvider(Color.White),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "$hours saat $minutes dakika $seconds saniye",
                        style = TextStyle(
                            color = ColorProvider(Color.White),
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }
}

class Secim2028ExtendedWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = Secim2028ExtendedWidget()
}
