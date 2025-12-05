package com.fortgame.ksynic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.graphics.Color as AndroidColor
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsControllerCompat
import com.fortgame.ksynic.Layer.CategoriesRow
import com.fortgame.ksynic.Layer.ProductGrid
import com.fortgame.ksynic.Layer.Screen.MarketplaceScreen
import com.fortgame.ksynic.Layer.TopHeaderSection
import com.fortgame.ksynic.theme.*
import com.fortgame.ksynic.utils.fh

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Цвет статус-бара (время/зарядка) под цвет верхней шапки
        window.statusBarColor = AndroidColor.parseColor("#5D76CB")
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        setContent {
            KsynicTheme {
                MarketplaceScreen()
            }
        }
    }
}