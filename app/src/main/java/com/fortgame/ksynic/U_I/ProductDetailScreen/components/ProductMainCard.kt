package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductMainCard(
    productName:String = "Часы наручные Calvin Klein ",
    sale: Int = 80,
    price:Int = 4200,
    oldPrice:Int = 21000,
    color:Color=Color(0xFFCC3333)
) {
    // Список изображений для карусели (пока дублируем одно, можно заменить на разные ресурсы)
    val images = listOf(
        R.drawable.watch_1, // Ваше изображение часов
        R.drawable.watch_1,
        R.drawable.watch_1,
        R.drawable.watch_1,
        R.drawable.watch_1
    )

    // Состояние пейджера (карусели)
    val pagerState = rememberPagerState(pageCount = { images.size })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            // Высота карточки может быть динамической, но оставим фиксированную для структуры
            // .height(fh(725))
            .background(Color.White, RoundedCornerShape(20.dp))
            .padding(bottom = 15.dp) // Отступ снизу для красоты
    ) {

        // --- КАРУСЕЛЬ (HorizontalPager) ---
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(fh(440)) // Высота изображения как в оригинале
                .clip(RoundedCornerShape(20.dp))
        ) { page ->
            Image(
                painter = painterResource(images[page]),
                contentDescription = "Product Image $page",
                contentScale = ContentScale.Crop, // Crop или Fit в зависимости от пропорций фото
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(4.dp))

        // --- ИНДИКАТОРЫ (Точки) ---
        // Сделано точно по Figma: Left 25px, Height 6px
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp), // Отступ слева 25px (как на скриншоте Layout)
            horizontalArrangement = Arrangement.spacedBy(4.dp), // Расстояние между точками
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(images.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    Color(0xFF5D76CB) // Активный цвет (Синий)
                } else {
                    Color(0xFFBDBDBD) // Неактивный цвет (Серый)
                }

                Box(
                    modifier = Modifier
                        .size(6.dp) // Размер точки 6px
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }

        Spacer(Modifier.height(fh(5)))
        Box(
            Modifier
                .height(fh(60))
                .fillMaxWidth()
                .padding(horizontal = fw(19))
        ){
            Text(
                text=productName,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                //.height(fh(70))
                .padding(horizontal = fw(23), vertical = fh(10)),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(Modifier
                .height(fh(50))
                .width(fw(150))
                .clip(RoundedCornerShape(10.dp))
                .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(10.dp),
                clip = false,
                ambientColor = Color.Black.copy(alpha = 0.25f),
                spotColor = Color.Black.copy(alpha = 0.25f)
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Box(Modifier
                    .width(fw(70))
                    .height(fh(50)),
                    contentAlignment = Alignment.Center){
                    Text(
                        text="Скидка",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 18.sp
                    )
                }
                Box(Modifier
                    .width(fw(70))
                    .height(fh(50)),
                    contentAlignment = Alignment.Center){
                    Text(
                        text="$sale %",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 22.sp,
                        color=color
                    )
                }
            }

            Spacer(Modifier.width(fw(10)))
            Column(Modifier
                    .height(fh(70))
                    .width(fw(150))
                    .padding(horizontal = fw(10), vertical = fh(10))
                    .clip(RoundedCornerShape(10.dp))
                    .shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(10.dp),
                        clip = false,
                        ambientColor = Color.Black.copy(alpha = 0.25f),
                        spotColor = Color.Black.copy(alpha = 0.25f)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                Text(
                    text="$price ₽",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 30.sp,
                    color=color
                )
                Box(
                    Modifier
                        .height(fh(20))
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Text(
                        text = "$oldPrice ₽",
                        style = TextStyle(textDecoration = TextDecoration.LineThrough),
                        color = Color.Gray,
                        fontSize = 15.sp,
                        lineHeight = 15.sp
                    )
                }
            }
        }

        // Далее здесь будет блок с ценой и названием...
    }
}

@Composable
@Preview
private fun ProductMainCardPreview() {
    Box(modifier = Modifier.background(Color.LightGray).padding(10.dp)) {
        ProductMainCard()
    }
}