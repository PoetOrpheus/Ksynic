package com.fortgame.ksynic.Navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.theme.*
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

// ----------------------------------------------------------------
// 5. Нижняя навигация
// ----------------------------------------------------------------

//enum class BottomNavItem {
//    Home, Cart, Favorites, Profile
//}

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
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavIcon(
                painter = painterResource(R.drawable.home_menu_icon),
                isSelected = selectedItem == BottomNavItem.Home,
                onClick = { onItemSelected(BottomNavItem.Home) }
            )
            BottomNavIcon(
                painter = painterResource(R.drawable.shop_menu_icon),
                isSelected = selectedItem == BottomNavItem.Cart,
                onClick = { onItemSelected(BottomNavItem.Cart) }
            )
            BottomNavIcon(
                painter = painterResource(R.drawable.lover_menu_icon),
                isSelected = selectedItem == BottomNavItem.Favorites,
                onClick = { onItemSelected(BottomNavItem.Favorites) }
            )
            BottomNavIcon(
                painter = painterResource(R.drawable.profile_menu_icon),
                isSelected = selectedItem == BottomNavItem.Profile,
                onClick = { onItemSelected(BottomNavItem.Profile) }
            )
        }
    }
}

@Composable
private fun BottomNavIcon(
    painter: Painter,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val tint = if (isSelected) BrandPurple else Color(0xFF000000)
    Box(
        modifier = Modifier
            .size(fh(40))
            .clickable(onClick=onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter=painter,
            contentDescription = null,
            modifier = Modifier.size(fh(30)),
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(tint)
        )
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar(selectedItem = BottomNavItem.Home, onItemSelected = {})
}
