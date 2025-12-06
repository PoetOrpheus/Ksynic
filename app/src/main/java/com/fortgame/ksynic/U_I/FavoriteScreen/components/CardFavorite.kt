package com.fortgame.ksynic.U_I.FavoriteScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.atom.ProductImageCarousel
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

// CardFavorite.kt — КЛЮЧЕВЫЕ ИСПРАВЛЕНИЯ
@Composable
fun CardFavorite(
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
            .height(fh(460)),
        shape = RoundedCornerShape(fw(16)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

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

            Column(modifier = Modifier.fillMaxSize()) {

                // Картинка
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(fh(280))
                        .background(
                            Color(0xFFE5E5E5),
                            RoundedCornerShape(15.dp)
                        )
                ) {
                    ProductImageCarousel(
                        images = listOf(
                            R.drawable.image_for_product_3,
                            R.drawable.image_for_product_2,
                            R.drawable.image_for_product_1,
                        ),
                        modifier = Modifier.fillMaxSize()
                    )
                    // Иконка лайка
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = null,
                        tint = Color(0xFFCC3333),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    )

                    // Скидка (теперь в ПРАВОМ НИЖНЕМ углу, как на скриншоте)
                    if (discount > 0) {
                        Box(
                            modifier = Modifier
                                .width(fw(60))
                                .height(fh(35))
                                .align(Alignment.BottomEnd)
                                .offset(y = (-fh(20))) // Сдвиг вверх от низа
                                .background(
                                    Color.White,
                                    RoundedCornerShape(topStart = fw(10), bottomStart = fw(10))),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "-$discount%",
                                color = colorText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }
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
                        Image(
                            painter = painterResource( R.drawable.otz_icon),
                            contentDescription = "Отзыв",
                            modifier = Modifier.size(10.dp),
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
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Absolute.Right
                    ) {
                        Box(
                            modifier = Modifier
                                .height(fh(10)),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            if (oldPrice != 0) {
                                Text(
                                    text = "$oldPrice ₽",
                                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                                    color = Color.Gray,
                                    fontSize = 8.sp,
                                    lineHeight = 9.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(fw(5)))
                        Box(
                            modifier = Modifier
                                .height(fh(20)),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "$price ₽",
                                color = colorText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(Modifier.height(fh(12)))

                    // КНОПКА «3 декабря» — теперь прижата к низу!
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(fh(25))
                            .background(colorBottom, RoundedCornerShape(fw(10))),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(painter = painterResource(R.drawable.korzina_for_favorite), contentDescription = null, modifier = Modifier.size(fw(18)))
                        Spacer(Modifier.width(fw(10)))
                        Text(
                            text = "3 декабря",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun CardFavoritePreview(){
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