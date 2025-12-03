package com.fortgame.ksynic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fortgame.ksynic.theme.*

// ----------------------------------------------------------------
// 5. Нижняя навигация
// ----------------------------------------------------------------

enum class BottomNavItem {
    Home, Cart, Favorites, Profile
}

@Composable
fun BottomNavigationBar(
    selectedItem: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    // Фон под навигацией (эмуляция тёмного фона приложения)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
            containerColor = Color(0xFFF2F2F2),
            tonalElevation = 0.dp
        ) {
            NavigationBarItem(
                icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") },
                selected = selectedItem == BottomNavItem.Home,
                onClick = { onItemSelected(BottomNavItem.Home) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BrandPurple,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
            NavigationBarItem(
                icon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart") },
                selected = selectedItem == BottomNavItem.Cart,
                onClick = { onItemSelected(BottomNavItem.Cart) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BrandPurple,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
            NavigationBarItem(
                icon = { Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Fav") },
                selected = selectedItem == BottomNavItem.Favorites,
                onClick = { onItemSelected(BottomNavItem.Favorites) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BrandPurple,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
            NavigationBarItem(
                icon = { Icon(Icons.Outlined.Person, contentDescription = "Profile") },
                selected = selectedItem == BottomNavItem.Profile,
                onClick = { onItemSelected(BottomNavItem.Profile) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BrandPurple,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar(selectedItem = BottomNavItem.Home, onItemSelected = {})
}
