package com.fortgame.ksynic.Layer


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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.theme.DiscountRed
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

// ----------------------------------------------------------------
// Карточки товаров
// ----------------------------------------------------------------
@Composable
fun ProductGrid() {
    // Один ряд из двух карточек, как на макете
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = fw(10)),
        horizontalArrangement = Arrangement.spacedBy(fw(10))
    ) {
        // Товар 1: Часы
        ProductCard(
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
            title = "Брюки прямые TBOE",
            price = 900,
            oldPrice = 3_000,
            discount = "-70%",
            rating = 5.0,
            reviews = 23,
            imageUrl = null,
            isTimeLimited = false,
        )
    }
}

@Composable
fun ProductCard(
    title: String,
    price: Int,
    oldPrice: Int,
    discount: String,
    rating: Double,
    reviews: Int,
    // imageUrl зарезервирован под загрузку из сети, сейчас всегда рисуем заглушку
    imageUrl: String?,
    isTimeLimited: Boolean = false,
) {
    Card(
        modifier = Modifier
            .width(fw(210))
            .height(fh(420)),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Область "картинки" — сейчас всегда заглушка под будущую загрузку
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(fh(240)) // чуть ниже, чтобы снизу было больше места под текст и блок скидки
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
            }

            Spacer(modifier = Modifier.height(fh(5)))

            // Информация о товаре
            Column(
                modifier = Modifier
                    .padding(horizontal = fw(15))
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Левая часть: иконка + кол-во отзывов
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$reviews отзыва",
                        fontSize = 10.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Правая часть: рейтинг со звездой
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Color(0xFFFFD700)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = String.format("%.1f", rating),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(fh(10)))
                // Название (до 2 строк, без жёсткого ограничения по высоте,
                // чтобы не обрезалось на устройствах с крупным шрифтом)
                Box(
                    modifier = Modifier
                        .width(fw(180)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                 Spacer(modifier = Modifier.height(fh(5)))
 
                 // Старая цена
                 Row(
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(start = fw(57)),
                     verticalAlignment = Alignment.Bottom
                 ) {
                     Box(
                         modifier = Modifier
                             .width(fw(55))
                             .height(fh(10)),
                         contentAlignment = Alignment.CenterEnd
                     ) {
                         Text(
                             text = "$oldPrice ₽",
                             style = TextStyle(textDecoration = TextDecoration.LineThrough),
                             color = Color.Gray,
                             fontSize = 8.sp
                         )
                     }
                     Spacer(modifier = Modifier.width(fw(5)))
                     Box(
                         modifier = Modifier
                             .width(fw(63))
                             .height(fh(20)),
                         contentAlignment = Alignment.Center
                     ) {
                         Text(
                             text = "$price ₽",
                             color = DiscountRed,
                             fontSize = 14.sp,
                             fontWeight = FontWeight.Bold
                         )
                     }
                 }

                Spacer(modifier = Modifier.height(fh(5)))

                // Скидка и таймер (всегда прижаты к низу карточки)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(fh(35)),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Surface(
                        modifier = Modifier
                            .width(fw(60))
                            .height(fh(35)),
                        color = DiscountRed.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(4.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, DiscountRed.copy(alpha = 0.3f))
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = discount,
                                color = DiscountRed,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                    if (isTimeLimited) {
                        Spacer(modifier = Modifier.width(fw(10)))
                        Box(
                            modifier = Modifier
                                .width(fw(120))
                                .height(fh(20)),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Text(
                                "Время ограничено",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )
                        }
                    }
                }

                // Отступ 10px до низа карточки
                Spacer(modifier = Modifier.height(fh(10)))
            }
        }
    }
}

@Composable
@Preview
private fun ProductGirdPreview(){
    ProductGrid()
}