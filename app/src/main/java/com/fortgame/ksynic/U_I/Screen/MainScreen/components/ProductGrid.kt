package com.fortgame.ksynic.U_I.Screen.MainScreen.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
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
    onProductClick: (Product) -> Unit = {}, // Теперь передаем Product
    onToggleFavorite: (String) -> Unit = {} // Функция для переключения избранного
) {
    // Используем обычную Column с Row для создания сетки вместо LazyVerticalGrid
    // чтобы избежать конфликта со скроллируемым родительским контейнером
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = fw(10))
    ) {
        // Разбиваем продукты на пары для отображения в ряд
        products.chunked(2).forEachIndexed { rowIndex, rowProducts ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = fh(10)),
                horizontalArrangement = Arrangement.spacedBy(fw(10))
            ) {
                rowProducts.forEachIndexed { itemIndex, product ->
                    val cardIndex = rowIndex * 2 + itemIndex
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product) },
                        onFavoriteClick = { onToggleFavorite(product.id) },
                        modifier = Modifier.weight(1f),
                        appearanceDelay = cardIndex * 50L // Задержка появления для каждой карточки
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
    onFavoriteClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    appearanceDelay: Long = 0L,
    enableAppearanceAnimation: Boolean = true // Флаг для отключения анимации появления (для оптимизации)
) {
    val oldPrice = product.oldPrice ?: 0
    val discount = product.calculateDiscountPercent() ?: 0
    val colorBottom = product.accentColor
    val colorText = product.accentColor
    
    // Анимация появления (оптимизированная - полностью пропускаем вычисления если отключена)
    val appearanceScale: Float
    val appearanceAlpha: Float
    
    if (enableAppearanceAnimation) {
        var isVisible by remember { mutableFloatStateOf(0f) }
        var alpha by remember { mutableFloatStateOf(0f) }
        
        LaunchedEffect(Unit) {
            delay(appearanceDelay)
            alpha = 1f
            isVisible = 1f
        }
        
        val scale by animateFloatAsState(
            targetValue = isVisible,
            animationSpec = spring(
                dampingRatio = 0.7f,
                stiffness = 300f
            ),
            label = "appearance_scale"
        )
        
        val alphaAnimated by animateFloatAsState(
            targetValue = alpha,
            animationSpec = tween(durationMillis = 300),
            label = "appearance_alpha"
        )
        
        appearanceScale = scale
        appearanceAlpha = alphaAnimated
    } else {
        // Когда анимация отключена - сразу используем финальные значения без анимаций
        appearanceScale = 1f
        appearanceAlpha = 1f
    }
    
    // Анимация нажатия
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressScale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 150),
        label = "card_scale"
    )
    val elevation by animateFloatAsState(
        targetValue = if (isPressed) 2f else 8f,
        animationSpec = tween(durationMillis = 150),
        label = "card_elevation"
    )
    
    // Комбинированный scale
    val combinedScale = appearanceScale * pressScale
    
    Card(
        modifier = modifier
            .width(fw(210))
            .height(fh(420))
            .scale(combinedScale)
            .alpha(appearanceAlpha)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation.dp)
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
                            Color.White,
                            RoundedCornerShape(15.dp)
                        )
                ) {
                    // Используем изображения из продукта для карусели
                    val productImages = product.imagesRes.ifEmpty {
                        // Если у продукта нет изображений, используем дефолтное
                        listOf(R.drawable.watch_1)
                    }
                    ProductImageCarousel(
                        images = productImages,
                        modifier = Modifier.fillMaxSize() // Говорим карусели занять всё место в родительском Box
                    )
                    // Иконка лайка с обработчиком клика и анимацией
                    FavoriteIconButton(
                        isFavorite = product.isFavorite,
                        onClick = onFavoriteClick,
                        modifier = Modifier
                            .height(fh(45))
                            .width(fw(45))
                            .align(Alignment.TopEnd)
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
                            Modifier
                                .height(fh(10))
                                .width(fw(50)),
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
private fun FavoriteIconButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val favoriteInteractionSource = remember { MutableInteractionSource() }
    val isFavoritePressed by favoriteInteractionSource.collectIsPressedAsState()
    
    val favoriteScale by animateFloatAsState(
        targetValue = if (isFavoritePressed) 0.8f else 1f,
        animationSpec = spring(
            dampingRatio = 0.6f,
            stiffness = 500f
        ),
        label = "favorite_scale"
    )
    
    Box(modifier = modifier) {
        Box(
            Modifier
                .height(fh(30))
                .width(fw(30))
                .scale(favoriteScale)
                .background(Color(0xB2F2F2F2), CircleShape)
                .clickable(
                    indication = null,
                    interactionSource = favoriteInteractionSource,
                    onClick = onClick
                )
                .align(Alignment.TopEnd)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    if (isFavorite) R.drawable.lover else R.drawable.unlover
                ),
                contentDescription = if (isFavorite) "Убрать из избранного" else "Добавить в избранное",
                modifier = Modifier.size(fw(20))
            )
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