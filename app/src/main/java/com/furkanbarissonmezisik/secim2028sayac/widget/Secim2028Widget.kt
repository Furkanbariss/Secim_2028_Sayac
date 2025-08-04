package com.furkanbarissonmezisik.secim2028sayac.widget

import android.content.Context
import android.util.Log
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
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight as GlanceFontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle as GlanceTextStyle
import androidx.glance.unit.ColorProvider as GlanceColorProvider
import com.furkanbarissonmezisik.secim2028sayac.MainActivity // MainActivity sınıfını import et
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Secim2028AppWidget : GlanceAppWidget() {
    override val sizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        Log.d("WidgetDebug", "Secim2028AppWidget güncellendi.")
        val targetDate = LocalDate.of(2028, 5, 14)
        val today = LocalDate.now()
        val daysLeft = ChronoUnit.DAYS.between(today, targetDate)

        provideContent {
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(GlanceColorProvider(Color(0xFFDC143C)))
                    .padding(8.dp)
                    // Burası önemli: Widget'a tıklama özelliği ekliyor
                    .clickable(actionStartActivity<MainActivity>()),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Seçime Kalan Gün:",
                        style = GlanceTextStyle(color = GlanceColorProvider(Color.White))
                    )
                    Text(
                        text = "$daysLeft Gün",
                        style = GlanceTextStyle(
                            color = GlanceColorProvider(Color.White),
                            fontSize = 24.sp,
                            fontWeight = GlanceFontWeight.Bold
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