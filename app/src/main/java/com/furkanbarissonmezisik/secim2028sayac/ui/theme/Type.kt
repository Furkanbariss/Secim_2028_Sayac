package com.furkanbarissonmezisik.secim2028sayac.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font // Bu satırı ekleyin
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle // Bu satırı ekleyin (İtalik font stilleri için)
import androidx.compose.ui.unit.sp
import com.furkanbarissonmezisik.secim2028sayac.R // Bu satırı ekleyin (font dosyalarını referans almak için)

// Calibri font ailesini tanımla
val Calibri = FontFamily(
    // res/font/calibri_regular.ttf dosyası
    Font(R.font.calibri_regular, FontWeight.Normal),

    // res/font/calibri_bold.ttf dosyası
    Font(R.font.calibri_bold, FontWeight.Bold),

    // res/font/calibri_italic.ttf dosyası
    Font(R.font.calibri_italic, FontWeight.Normal, FontStyle.Italic),

    // res/font/calibri_bold_italic.ttf dosyası
    Font(R.font.calibri_bold_italic, FontWeight.Bold, FontStyle.Italic)
)

// Uygulamanızın varsayılan Typography nesnesini güncelleyin
val Typography = Typography(
    // bodyLarge: Genellikle ana metinler için kullanılır
    bodyLarge = TextStyle(
        fontFamily = Calibri,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // titleLarge: Genellikle birincil başlıklar için kullanılır
    titleLarge = TextStyle(
        fontFamily = Calibri,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    // labelSmall: Genellikle küçük etiketler, düğme metinleri için kullanılır
    labelSmall = TextStyle(
        fontFamily = Calibri,
        fontWeight = FontWeight.Medium, // Bu stile göre ağırlığı ayarlayabilirsiniz
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    // Diğer tüm `display`, `headline`, `title`, `body`, `label` stillerini
    // projenizin ihtiyacına göre burada Calibri olarak ayarlayabilirsiniz.
    // Örneğin:
    // displayLarge = TextStyle(fontFamily = Calibri, fontWeight = FontWeight.Light, fontSize = 57.sp, lineHeight = 64.sp, letterSpacing = -0.25.sp),
    // headlineLarge = TextStyle(fontFamily = Calibri, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 40.sp, letterSpacing = 0.sp),
    // ...
)