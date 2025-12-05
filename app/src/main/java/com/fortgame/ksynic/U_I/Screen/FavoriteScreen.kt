package com.fortgame.ksynic.U_I.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.U_I.components.CardFavorite
import com.fortgame.ksynic.U_I.components.ProductCard
import com.fortgame.ksynic.U_I.components.SettingsFavoriteRow
import com.fortgame.ksynic.U_I.components.TopHeaderWithoutSearch
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun FavoriteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2)) // Фон как на MarketplaceScreen
    ) {
        TopHeaderWithoutSearch()
        // Заголовок "Избранное"
        Spacer(modifier = Modifier.height(fh(10)))

        SettingsFavoriteRow()

        Spacer(modifier = Modifier.height(fh(10)))

        // Сетка/Список товаров
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Здесь будет отображаться список избранных товаров (например, ProductGrid/ProductCard)
            item {
                Row() {
                    // Временно: сообщение, если избранного нет
                    CardFavorite(
                        title = "Кеды adidas Sportswear Hoops 3.0",
                        price = 3743,
                        rating = 4.9,
                        reviews = 457,
                        imageUrl = null,
                        isTimeLimited = false,
                        colorBottom = Color(0xFF000000),
                        colorText = Color(0xFF000000)
                    )

                    // Товар 2: Часы
                    CardFavorite(
                        title = "Часы наручные Кварцевые",
                        price = 4200,
                        oldPrice = 21000,
                        discount = 80,
                        rating = 5.0,
                        reviews = 23,
                        imageUrl = null,
                        isTimeLimited = true,
                        colorBottom = Color(0xFFCC3333),
                        colorText = Color(0xFFCC3333)
                    )
                    CardFavorite(
                        title = "Кеды adidas Sportswear Hoops 3.0",
                        price = 3743,
                        rating = 4.9,
                        reviews = 457,
                        imageUrl = null,
                        isTimeLimited = false,
                        colorBottom = Color(0xFF000000),
                        colorText = Color(0xFF000000)
                    )

                    // Товар 2: Часы
                    CardFavorite(
                        title = "Часы наручные Кварцевые",
                        price = 4200,
                        oldPrice = 21000,
                        discount = 80,
                        rating = 5.0,
                        reviews = 23,
                        imageUrl = null,
                        isTimeLimited = true,
                        colorBottom = Color(0xFFCC3333),
                        colorText = Color(0xFFCC3333)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FavoriteScreenPreview() {
    FavoriteScreen()
}