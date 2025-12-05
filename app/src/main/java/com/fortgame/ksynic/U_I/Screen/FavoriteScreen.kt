// FavoriteScreen.kt
package com.fortgame.ksynic.U_I.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fortgame.ksynic.U_I.components.CardFavorite
import com.fortgame.ksynic.U_I.components.SettingsFavoriteRow
import com.fortgame.ksynic.U_I.components.TopHeaderWithoutSearch
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun FavoriteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
    ) {
        TopHeaderWithoutSearch()
        // Заголовок "Избранное"
        Spacer(modifier = Modifier.height(fh(10)))

        SettingsFavoriteRow()

        Spacer(modifier = Modifier.height(fh(10)))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = fw(15), vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(fw(12)),
            verticalArrangement = Arrangement.spacedBy(fh(16)),
            modifier = Modifier.fillMaxSize()
        ) {
            items(listOf(
                FavoriteItem("Брюки прямые TBOE", 800, oldPrice = 3000, discount = 70, rating = 4.8, reviews = 944, isTimeLimited = true),
                FavoriteItem("Часы наручные Кварцевые", 4200, oldPrice = 21000, discount = 80, rating = 5.0, reviews = 23, isTimeLimited = true, colorBottom = Color(0xFFCC3333), colorText = Color(0xFFCC3333)),
                FavoriteItem("Кеды adidas Sportswear Hoops 3.0", 3743, rating = 4.9, reviews = 457, isTimeLimited = true),
                FavoriteItem("Xiaomi 15T + часы Xiaomi Watch", 40000, oldPrice = 60000, discount = 20, rating = 4.9, reviews = 437, isTimeLimited = true),
            )) { item ->
                CardFavorite(
                    title = item.title,
                    price = item.price,
                    oldPrice = item.oldPrice,
                    discount = item.discount,
                    rating = item.rating,
                    reviews = item.reviews,
                    imageUrl = null,
                    isTimeLimited = item.isTimeLimited,
                    colorBottom = item.colorBottom,
                    colorText = item.colorText
                )
            }
        }
    }
}

// Временная модель для превью
data class FavoriteItem(
    val title: String,
    val price: Int,
    val oldPrice: Int = 0,
    val discount: Int = 0,
    val rating: Double,
    val reviews: Int,
    val isTimeLimited: Boolean,
    val colorBottom: Color = Color(0xFF5D76CB),
    val colorText: Color = Color(0xFF5D76CB)
)