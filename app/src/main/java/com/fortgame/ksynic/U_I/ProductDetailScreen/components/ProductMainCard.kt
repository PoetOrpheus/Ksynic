package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductMainCard(
    productName: String = "Часы наручные Calvin Klein черные мужские ",
    sale: Int = 80,
    price: Int = 4200,
    oldPrice: Int = 21000,
    accentColor: Color = Color(0xFFCC3333) // Красный цвет из макета
) {
    // Список изображений
    val images = listOf(
        R.drawable.watch_1, // Замените на ваши ресурсы
        R.drawable.watch_1,
        R.drawable.watch_1,
        R.drawable.watch_1,
        R.drawable.watch_1
    )

    val pagerState = rememberPagerState(pageCount = { images.size })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(20.dp))
            .padding(bottom = 20.dp) // Отступ снизу
    ) {

        // --- 1. КАРУСЕЛЬ ---
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(fh(440)) // Высота по визуальной пропорции
                .clip(RoundedCornerShape(20.dp))
        ) { page ->
            Image(
                painter = painterResource(images[page]),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(10.dp))

        // --- 2. ИНДИКАТОРЫ (Точки) ---
        // Figma spec: Left 25px, Height 6px
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = fw(20)), // Точный отступ из макета
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(images.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    Color(0xFF5D76CB) // Активный синий
                } else {
                    Color(0xFFBDBDBD) // Неактивный серый
                }

                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        // --- 3. НАЗВАНИЕ ---
        // Выровнено по левому краю вместе с точками (25px)
        Text(
            text = productName,
            fontSize = 18.sp, // Размер визуально меньше, чем 22sp
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        Spacer(Modifier.height(fh(10)))

        Row(
            Modifier
                .fillMaxWidth()
                //.height(fh(70))
                .padding(horizontal = fw(20)),
            horizontalArrangement = Arrangement.SpaceBetween,
            //verticalAlignment = Alignment.CenterVertically
        ){

            // Скидка и проценты
            Row(Modifier
                .height(fh(50))
                .width(fw(160))
                .shadow(
                    elevation = 5.dp, // Figma: Blur 5
                    shape = RoundedCornerShape(10.dp),
                    spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                )
                .background(Color.White, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Box(Modifier
                    .height(fh(50)),
                    contentAlignment = Alignment.Center){
                    Text(
                        text="Скидка",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp
                    )
                }
                Box(Modifier
                    .width(fw(70))
                    .height(fh(50)),
                    contentAlignment = Alignment.Center){
                    Text(
                        text="$sale%",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 22.sp,
                        color=accentColor
                    )
                }
            }

            Spacer(Modifier.width(10.dp))

            // Правый блок: ЦЕНА (Размеры из Figma: 140x70)
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(70.dp)
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    )
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp)) // Важно: обрезаем градиент по форме карточки
            ) {
                // --- ГРАДИЕНТ (СЛОЙ 1 - ВНИЗУ) ---
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(25.dp) // Увеличили высоту для плавности перехода (примерно 1/3 карточки)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFCC3333).copy(alpha = 0.0f), // Полностью прозрачный (Белый/Прозрачный)
                                    Color(0xFFCC3333).copy(alpha = 0.1f), // Промежуточный для мягкости
                                    Color(0xFFCC3333) // Насыщенный красный в самом низу
                                )
                            )
                        )
                )

                // --- КОНТЕНТ (СЛОЙ 2 - ПОВЕРХ ГРАДИЕНТА) ---
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    // Новая цена
                    Text(
                        text = "$price ₽",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor, // Убедитесь, что accentColor = Color(0xFFCC3333)
                        modifier = Modifier.align(Alignment.End),
                        lineHeight = 30.sp
                    )

                    // Старая цена (справа сверху)
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                        Text(
                            text = "$oldPrice ₽",
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            color = Color(0xFF999999),
                            fontSize = 15.sp,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

        }
    }
}

@Composable
@Preview
private fun ProductMainCardPreview() {
    Box(modifier = Modifier.background(Color(0xFFF2F2F2)).padding(10.dp)) {
        ProductMainCard()
    }
}