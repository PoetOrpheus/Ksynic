package com.fortgame.ksynic.Navigation

import androidx.annotation.DrawableRes
import com.fortgame.ksynic.R

// Это определение обычно находится в отдельном файле (например, BottomNavItem.kt) или прямо в BottomNavigationBar.kt

sealed class BottomNavItem(val name: String, @DrawableRes val icon: Int) {
    object Home : BottomNavItem("Главная", R.drawable.home_menu_icon)
    object Favorites : BottomNavItem("Избранное", R.drawable.lover_menu_icon) // Иконка, используемая в вашем BottomNavigationBar
    object ShopCart : BottomNavItem("Корзина", R.drawable.shop_menu_icon) //
    object Profile : BottomNavItem("Профиль", R.drawable.profile_menu_icon) //
}