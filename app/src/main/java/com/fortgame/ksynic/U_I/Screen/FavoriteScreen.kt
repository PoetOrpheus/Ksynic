package com.fortgame.ksynic.U_I.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun FavoriteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2)) // Фон как на MarketplaceScreen
            .padding(horizontal = 16.dp)
    ) {
        // Заголовок "Избранное"
        Spacer(modifier = Modifier.height(fh(20)))
        Text(
            text = "Избранное",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(fh(10)))

        // Сетка/Список товаров
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Здесь будет отображаться список избранных товаров (например, ProductGrid/ProductCard)
            item {
                // Временно: сообщение, если избранного нет
                Text(
                    text = "Здесь пока нет избранных товаров.",
                    modifier = Modifier.padding(top = 50.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun FavoriteScreenPreview() {
    FavoriteScreen()
}