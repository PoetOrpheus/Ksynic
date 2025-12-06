package com.fortgame.ksynic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.graphics.Color as AndroidColor
import androidx.core.view.WindowInsetsControllerCompat
import com.fortgame.ksynic.U_I.MainScreen.MainScreen
import com.fortgame.ksynic.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Цвет статус-бара (время/зарядка) под цвет верхней шапки
        window.statusBarColor = AndroidColor.parseColor("#5D76CB")
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        setContent {
            KsynicTheme {
                MainScreen()
            }
        }
    }
}