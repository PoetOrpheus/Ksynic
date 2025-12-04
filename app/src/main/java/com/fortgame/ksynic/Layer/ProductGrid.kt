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
            modifier = Modifier
                .width(fw(210))
                .height(fh(420)),
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
            modifier = Modifier
                .width(fw(210))
                .height(fh(420)),
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
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Область "картинки" — сейчас всегда заглушка под будущую загрузку
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(fh(280))
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

            Spacer(modifier = Modifier.height(fh(5)))

            // Информация о товаре
            Column(
                modifier = Modifier
                    .padding(horizontal = fw(15), vertical = fh(5))
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Build,
                        contentDescription = null,
                        modifier = Modifier
                            .size(fh(10)),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(fw(5)))
                    Box(
                        modifier = Modifier
                            .width(fw(75))
                            .height(fh(10)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text("$reviews отзыва", fontSize = 8.sp, color = Color.Gray)
                    }
                    Spacer(modifier=Modifier.width(fw(20)))
                    Box(
                        modifier=Modifier
                            .width(fw(50))
                            .height(fh(10)),
                        contentAlignment = Alignment.CenterEnd
                    ){
                        Text("$rating",
                            fontSize = 10.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold)

                    }
                    Spacer(modifier = Modifier.width(fw(5)))
                    Icon(
                        Icons.Default.Build,
                        contentDescription = null,
                        modifier = Modifier
                            .size(fh(10)),
                        tint = Color.Gray
                    )

                }

                Spacer(modifier = Modifier.height(fh(10)))
                // Название
                Box(
                    modifier = Modifier
                        .width(fw(180))
                        .height(fh(40)),
                    contentAlignment = Alignment.CenterStart
                ){
                Text(
                    title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )}

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
                             fontSize = 9.sp
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
                             fontSize = 15.sp,
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
                                fontSize = 16.sp
                            )
                        }
                    }
                    if (isTimeLimited) {
                        Box(
                            modifier = Modifier
                                .width(fw(120))
                                .height(fh(20)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Время ограничено",
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun ProductGirdPreview(){
    ProductGrid()
}