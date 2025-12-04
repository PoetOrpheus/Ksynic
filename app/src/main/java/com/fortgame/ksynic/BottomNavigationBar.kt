package com.fortgame.ksynic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fortgame.ksynic.theme.*
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

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
            .width(fw(450))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .width(fw(450))
                .height(fh(60))
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color(0xFFF2F2F2)),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavIcon(
                icon = Icons.Outlined.Home,
                isSelected = selectedItem == BottomNavItem.Home,
                onClick = { onItemSelected(BottomNavItem.Home) }
            )
            BottomNavIcon(
                icon = Icons.Outlined.ShoppingCart,
                isSelected = selectedItem == BottomNavItem.Cart,
                onClick = { onItemSelected(BottomNavItem.Cart) }
            )
            BottomNavIcon(
                icon = Icons.Outlined.FavoriteBorder,
                isSelected = selectedItem == BottomNavItem.Favorites,
                onClick = { onItemSelected(BottomNavItem.Favorites) }
            )
            BottomNavIcon(
                icon = Icons.Outlined.Person,
                isSelected = selectedItem == BottomNavItem.Profile,
                onClick = { onItemSelected(BottomNavItem.Profile) }
            )
        }
    }
}

@Composable
private fun BottomNavIcon(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val tint = if (isSelected) BrandPurple else Color.Gray
    Box(
        modifier = Modifier.size(fh(40)), // зона нажатия чуть больше 30px-иконки
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(fh(30))
        )
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar(selectedItem = BottomNavItem.Home, onItemSelected = {})
}
