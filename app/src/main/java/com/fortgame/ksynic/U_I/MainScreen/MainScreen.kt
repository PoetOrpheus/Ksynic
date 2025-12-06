package com.fortgame.ksynic.U_I.MainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.Navigation.BottomNavItem
import com.fortgame.ksynic.Navigation.BottomNavigationBar
import com.fortgame.ksynic.U_I.ProfileScreen.ProfileScreen
import com.fortgame.ksynic.U_I.FavoriteScreen.FavoriteScreen
import com.fortgame.ksynic.U_I.MainScreen.components.CategoriesRow
import com.fortgame.ksynic.U_I.MainScreen.components.ProductGrid
import com.fortgame.ksynic.U_I.TopHeaderSection
import com.fortgame.ksynic.utils.fh

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedTab,
                onItemSelected = { selectedTab = it }
            )
        },
        containerColor = Color(0xFFF2F2F2)
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when (selectedTab) {
                BottomNavItem.Home -> MarketplaceContent()
                BottomNavItem.Favorites -> FavoriteScreen()
                BottomNavItem.Profile -> ProfileScreen()

                // Проверяем другие пункты меню (Cart и Profile)
                // Если они нажимаются, должно появиться это сообщение:
                else -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Заглушка для: ${selectedTab.name}", // Видим название выбранной вкладки
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MarketplaceContent() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        // Верхняя секция с градиентом
        TopHeaderSection()

        // Категории (иконки)
        CategoriesRow()
        Spacer(modifier = Modifier.height(fh(10)))

        // Сетка товаров
        ProductGrid()
        Spacer(modifier = Modifier.height(fh(10)))
    }
}



// Для превью в Android Studio
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainScreen()
}