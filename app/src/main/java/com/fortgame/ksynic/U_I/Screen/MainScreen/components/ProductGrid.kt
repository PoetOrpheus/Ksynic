package com.fortgame.ksynic.U_I.Screen.MainScreen.components


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
import androidx.compose.foundation.shape.CircleShape
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
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.model.TestProducts
import com.fortgame.ksynic.theme.DiscountRed
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw
import com.fortgame.ksynic.R

// ----------------------------------------------------------------
// Карточки товаров
// ----------------------------------------------------------------
@Composable
fun ProductGrid(
    products: List<Product>,
    onProductClick: (Product) -> Unit = {} // Теперь передаем Product
) {
    // Используем обычную Column с Row для создания сетки вместо LazyVerticalGrid
    // чтобы избежать конфликта со скроллируемым родительским контейнером
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = fw(10))
    ) {
        // Разбиваем продукты на пары для отображения в ряд
        products.chunked(2).forEach { rowProducts ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = fh(10)),
                horizontalArrangement = Arrangement.spacedBy(fw(10))
            ) {
                rowProducts.forEach { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product) },
                        modifier = Modifier.weight(1f)
                    )
                }
                // Если в ряду только один элемент, добавляем пустое место
                if (rowProducts.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val oldPrice = product.oldPrice ?: 0
    val discount = product.calculateDiscountPercent() ?: 0
    val colorBottom = product.accentColor
    val colorText = product.accentColor
    Card(
        modifier = modifier
            .width(fw(210))
            .height(fh(420))
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
                    .height(fh(60)) // Высота градиента (примерно под контент)
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
                    Box(
                        Modifier
                            .height(fh(30))
                            .width(fw(30))
                            .background(Color(0xB2F2F2F2), CircleShape)
                            .align(Alignment.TopEnd),
                        contentAlignment = Alignment.Center
                    ) {
                        // Иконка лайка
                        Image(
                            if (product.isFavorite) painterResource(R.drawable.lover) else painterResource(R.drawable.unlover),
                            contentDescription = null,
                        )
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
                            modifier = Modifier.size(fw(10) ),
                        )
                        Spacer(modifier = Modifier.width(fw(5)))
                        Box(
                            Modifier
                                .height(fh(10))
                                .width(fw(75))
                        ) {
                            Text(
                                text = "${product.reviewsCount} отзыва",
                                fontSize = 8.sp,
                                color = Color.Gray,
                                lineHeight = 8.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(fw(25)))

                        Box(
                            Modifier.height(fh(10)).width(fw(50)),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                text = String.format("%.1f", product.rating),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                lineHeight = 10.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(fw(5)))

                        Image(
                            painterResource(R.drawable.star_profile_menu),
                            contentDescription = null,
                            modifier = Modifier.size(fw(10)),
                            //tint = Color(0xFFFFD700)
                        )
                    }

                    Spacer(modifier = Modifier.height(fh(10)))

                    // Название
                    Box(
                        modifier = Modifier
                            .width(fw(180))
                            .height(fh(40)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = product.name,
                            maxLines = 2,
                            overflow = TextOverflow.Clip,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 14.sp
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
                                text = "${product.price} ₽",
                                color = colorText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 14.sp
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
private fun ProductGirdPreview() {
    // Используем тестовые данные для Preview
    ProductGrid(
        products = TestProducts.allProducts.take(4)
    )
}