package com.fortgame.ksynic.Layer


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
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
        // Товар 1: кеды
        ProductCard(
            title = "Кеды adidas Sportswear Hoops 3.0",
            price = 3743,
            rating = 4.9,
            reviews = 457,
            imageUrl = null,
            isTimeLimited = false,
            colorBottom = Color(0xFF000000),
            colorText=Color(0xFF000000)
        )

        // Товар 2: Часы
        ProductCard(
            title = "Часы наручные Кварцевые",
            price = 4200,
            oldPrice = 21000,
            discount = 80,
            rating = 5.0,
            reviews = 23,
            imageUrl = null,
            isTimeLimited = true,
            colorBottom = Color(0xFFCC3333),
            colorText=Color(0xFFCC3333)
        )


    }
}

@Composable
fun ProductCard(
    title: String,
    price: Int,
    oldPrice: Int = 0,
    discount: Int = 0,
    rating: Double,
    reviews: Int,
    imageUrl: String?,
    isTimeLimited: Boolean = false,
    colorBottom:Color=Color.White,
    colorText:Color,
) {
    Card(
        modifier = Modifier
            .width(fw(210))
            .height(fh(420)),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        // Ипользуем Box, чтобы накладывать слои (Z-index)
        Box(modifier = Modifier.fillMaxSize()) {

            // --- СЛОЙ 1: ГРАДИЕНТ (ФОН) ---
            // Он лежит внизу Box, поэтому отрисовывается первым.
            // Мы выравниваем его по низу карточки.
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(fh(70)) // Высота градиента (примерно под контент)
                    .align(Alignment.BottomCenter) // Прижимаем к низу
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                // Начинаем с полностью прозрачного белого, чтобы был плавный переход
                                Color.White.copy(alpha = 0f),
                                // Переход в светло-красный
                                Color(0xFFFFECEC),
                                // Внизу насыщенный красный (из вашего кода), но чуть прозрачнее для мягкости
                                colorBottom
                            )
                        )
                    )
            )

            // --- СЛОЙ 2: КОНТЕНТ ---
            // Отрисовывается поверх градиента
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Область "картинки"
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(fh(240))
                        .background(
                            Color(0xFFE5E5E5),
                            RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
                        )
                ) {
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
                    // Рейтинг и отзывы
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(fw(5)))
                        Text(
                            text = "$reviews отзыва",
                            fontSize = 10.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = String.format("%.1f", rating),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(fw(5)))

                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            tint = Color(0xFFFFD700)
                        )
                    }

                    Spacer(modifier = Modifier.height(fh(10)))

                    // Название
                    Box(
                        modifier = Modifier.width(fw(180)),
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

                    // Цены
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
                            if (oldPrice != 0) {
                                Text(
                                    text = "$oldPrice ₽",
                                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                                    color = Color.Gray,
                                    fontSize = 8.sp
                                )
                            }
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
                                color = colorText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(fh(5)))

                    // Скидка и таймер
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(fh(35)),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        if (discount!=0){
                        // Для плашки скидки добавляем белый фон, чтобы она не сливалась с градиентом
                        Surface(
                            modifier = Modifier
                                .width(fw(60))
                                .height(fh(35)),
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xfffffff)),
                            shadowElevation = 7.dp
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "$discount%",
                                    color = DiscountRed,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                        if (isTimeLimited) {

                            Box(
                                modifier = Modifier
                                    .width(fw(150))
                                    .height(fh(20))
                                    .shadow(
                                        elevation = 0.3.dp,
                                        RoundedCornerShape(fh(5)),
                                        spotColor = Color(0x4D000000)
                                    ),
                                contentAlignment = Alignment.Center,

                            ) {
                                // Если фон очень темный, возможно, стоит поменять цвет текста на белый?
                                // Но пока оставим Gray/DarkGray как в оригинале, так как градиент внизу не черный.
                                Text(
                                    "Время ограничено",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.DarkGray // Чуть темнее, чтобы читалось на красном фоне
                                )
                            }
                        }}
                    }
                    Spacer(modifier = Modifier.height(fh(10)))
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