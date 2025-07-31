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
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import androidx.compose.ui.graphics.Color // sadece renk için kalabilir
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Secim2028AppWidget : GlanceAppWidget() {
    override val sizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val targetDate = LocalDate.of(2028, 6, 23)
        val today = LocalDate.now()
        val daysLeft = ChronoUnit.DAYS.between(today, targetDate)

        provideContent {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(ColorProvider(Color(0xFFDC143C)))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Seçime Kalan Gün:",
                        style = TextStyle(color = ColorProvider(Color.White))
                    )
                    Text(
                        text = "$daysLeft Gün",
                        style = TextStyle(
                            color = ColorProvider(Color.White),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

class Secim2028WidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = Secim2028AppWidget()
}
