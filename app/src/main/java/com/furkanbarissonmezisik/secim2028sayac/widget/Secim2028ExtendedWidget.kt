package com.furkanbarissonmezisik.secim2028sayac.widget

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight as GlanceFontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle as GlanceTextStyle
import androidx.glance.unit.ColorProvider as GlanceColorProvider
import com.furkanbarissonmezisik.secim2028sayac.MainActivity
import java.time.LocalDateTime
import java.time.Duration
import androidx.glance.ImageProvider
import com.furkanbarissonmezisik.secim2028sayac.R

class Secim2028ExtendedWidget : GlanceAppWidget() {
    override val sizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val targetDate = LocalDateTime.of(2028, 5, 14, 8, 0)
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
                    .background(ImageProvider(R.drawable.widget_background))
                    .padding(12.dp)
                    .clickable(actionStartActivity<MainActivity>()),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Seçime Kalan Süre:",
                        style = GlanceTextStyle(
                            color = GlanceColorProvider(Color.White),
                            fontSize = 16.sp
                        )
                    )
                    // Yıl, ay, gün bilgilerini daha büyük ve tek satırda gösteriyoruz
                    Text(
                        text = "$years yıl $months ay $remainingDays gün",
                        style = GlanceTextStyle(
                            color = GlanceColorProvider(Color.White),
                            fontSize = 28.sp, // Yazı boyutunu büyüttük
                            fontWeight = GlanceFontWeight.Bold
                        )
                    )
                    // Saat, dakika, saniye bilgisi
                    Text(
                        text = "$hours saat $minutes dakika $seconds saniye",
                        style = GlanceTextStyle(
                            color = GlanceColorProvider(Color.White),
                            fontSize = 18.sp // Yazı boyutunu büyüttük
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