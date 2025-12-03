package com.fortgame.ksynic.UI


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.ui.theme.DiscountRed

// ----------------------------------------------------------------
// Карточки товаров
// ----------------------------------------------------------------
@Composable
fun ProductGrid() {
    // Один ряд из двух карточек, как на макете
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Товар 1: Часы
        ProductCard(
            modifier = Modifier.weight(1f),
            title = "Часы наручные Кварцевые",
            price = 4_200,
            oldPrice = 21_000,
            discount = "-80%",
            rating = 5.0,
            reviews = 23,
            imageUrl = null,
            isTimeLimited = true
        )

        // Товар 2: Джинсы
        ProductCard(
            modifier = Modifier.weight(1f),
            title = "Брюки прямые TBOE",
            price = 900,
            oldPrice = 3_000,
            discount = "-70%",
            rating = 5.0,
            reviews = 23,
            imageUrl = null,
            isTimeLimited = false,
            badgeContent = {
                // Красный бейдж бренда, как на скриншоте
                Box(
                    modifier = Modifier
                        .background(Color.Red, RoundedCornerShape(4.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Text(
                        "TBOE",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        )
    }
}

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    title: String,
    price: Int,
    oldPrice: Int,
    discount: String,
    rating: Double,
    reviews: Int,
    // imageUrl зарезервирован под загрузку из сети, сейчас всегда рисуем заглушку
    imageUrl: String?,
    isTimeLimited: Boolean = false,
    badgeContent: @Composable (() -> Unit)? = null
) {
    Card(
        modifier = modifier.height(320.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Область "картинки" — сейчас всегда заглушка под будущую загрузку
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .background(Color(0xFFE5E5E5), RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
            ) {
                // Заглушка вместо AsyncImage(model = imageUrl ...)
                Text(
                    text = "Изображение",
                    color = Color(0xFF888888),
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Center)
                )

                // Иконка лайка
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                )

                // Бейдж бренда (если есть)
                if (badgeContent != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                    ) {
                        badgeContent()
                    }
                }
            }

            // Информация о товаре
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                // Рейтинг
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Build,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("$reviews отзыва", fontSize = 10.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Color(0xFFFFD700)
                    )
                    Text(
                        "$rating",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Цена
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "$oldPrice ₽",
                        style = TextStyle(textDecoration = TextDecoration.LineThrough),
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$price ₽",
                        color = DiscountRed,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Скидка и таймер (всегда прижаты к низу карточки)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = DiscountRed.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(4.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, DiscountRed.copy(alpha = 0.3f))
                    ) {
                        Text(
                            text = discount,
                            color = DiscountRed,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                    if (isTimeLimited) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "Время ограничено",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}