package com.fortgame.ksynic.U_I.MainScreen.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.fortgame.ksynic.U_I.atom.ProductImageCarousel
import com.fortgame.ksynic.theme.DiscountRed
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.ProductDetailScreen.ProductDetailScreen

// ----------------------------------------------------------------
// Карточки товаров
// ----------------------------------------------------------------
@Composable
fun ProductGrid(
    onProductClick: () -> Unit = {} // ДОБАВЬТЕ этот параметр

) {
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
            colorText=Color(0xFF000000),
            onClick = onProductClick // Передаем функцию
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
            colorText=Color(0xFFCC3333),
            onClick = onProductClick // Передаем функцию
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
    onClick: () -> Unit = {} // ДОБАВЬТЕ этот параметр

) {
    Card(
        modifier = Modifier
            .width(fw(210))
            .height(fh(460))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        //elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        // Ипользуем Box, чтобы накладывать слои (Z-index)
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

            // --- СЛОЙ 2: КОНТЕНТ ---
            // Отрисовывается поверх градиента
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Область "картинки"
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
                        modifier = Modifier.fillMaxSize() // Говорим карусели занять всё место в родительском Box
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

                    Spacer(modifier = Modifier.height(fh(5)))

                    // Скидка и таймер
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(fh(35)),
                        contentAlignment = Alignment.BottomStart
                    ) {

                        // ---  СКИДКА ---
                        if (discount != 0) {
                            Surface(
                                modifier = Modifier
                                    .width(fw(60))
                                    .height(fh(35))
                                    .align(Alignment.BottomStart), // Прижимаем влево
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp),
                                shadowElevation = 4.dp
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "-$discount%",
                                        color = DiscountRed,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
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