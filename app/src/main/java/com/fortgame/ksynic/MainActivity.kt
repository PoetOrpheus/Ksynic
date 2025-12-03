package com.fortgame.ksynic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.fortgame.ksynic.BottomNavItem
import com.fortgame.ksynic.BottomNavigationBar
import com.fortgame.ksynic.Layer.CategoriesRow
import com.fortgame.ksynic.Layer.ProductGrid
import com.fortgame.ksynic.Layer.TopHeaderSection
import com.fortgame.ksynic.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KsynicTheme {
                MarketplaceScreen()
            }
        }
    }
}



@Composable
fun MarketplaceScreen() {
    var selectedTab by remember { mutableStateOf(BottomNavItem.Home) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedTab,
                onItemSelected = { selectedTab = it }
            )
        },
        containerColor = BackgroundLight
    ) { paddingValues ->
        // Основной контент с прокруткой
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Верхняя секция с градиентом
            TopHeaderSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Категории (иконки)
            CategoriesRow()

            Spacer(modifier = Modifier.height(16.dp))

            // Сетка товаров
            ProductGrid()


            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}



// Для превью в Android Studio
@Preview(showBackground = true)
@Composable
fun MarketplacePreview() {
    MarketplaceScreen()
}