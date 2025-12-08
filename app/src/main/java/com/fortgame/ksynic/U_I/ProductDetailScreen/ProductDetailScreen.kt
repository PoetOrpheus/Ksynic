package com.fortgame.ksynic.U_I.ProductDetailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.ProductDetailScreen.components.ProductMainCard
import com.fortgame.ksynic.U_I.TopHeaderWithReturn
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

// Заглушки для цветов, чтобы соответствовать стилю
val PurpleHeader = Color(0xFF9C89F6) // Примерный цвет хедера
val MainRed = Color(0xFFD32F2F)
val TextGray = Color(0xFF999999)
val BgGray = Color(0xFFF2F2F2)
val BlueButton = Color(0xFF5D76CB)

@Composable
fun ProductDetailScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BgGray)
        .padding(horizontal = fw(5))) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            // 1. Хедер (Фиолетовый фон)
            item {
                Spacer(Modifier.height(fh(10)))
                TopHeaderWithReturn()
            }

            // 2. Карточка товара (Фото + Цена)
            item {
                ProductMainCard()
            }

            // 3. Варианты, Рейтинг, Табы, Описание
            item {
                DetailsSection()
            }

            // 4. Продавец и Бренд
            item {
                InfoCardsSection()
            }

            // 5. История просмотров
            item {
                HistorySection()
            }
        }
    }
}


@Composable
fun DetailsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(15.dp)
    ) {
        // Варианты (миниатюры)
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            VariantItem(isSelected = true, imageRes = R.drawable.image_for_product_3)
            VariantItem(isSelected = false, imageRes = R.drawable.image_for_product_3) // Замените на черный вариант
            VariantItem(isSelected = false, imageRes = R.drawable.image_for_product_3) // Замените на серебряный
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Статистика (Рейтинг, Вопросы, Фото)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatCard(icon = { Icon(Icons.Default.Star, null, tint = Color(0xFFFFD700)) }, text = "4.9\n1 337 отзывов")
            StatCard(icon = { Text("?", fontWeight = FontWeight.Bold, color = BlueButton) }, text = "69\nвопросов")
            StatCard(icon = { /* Иконка галереи */ }, text = "+ 42\nфото", isImage = true)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Табы
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(BlueButton, RoundedCornerShape(8.dp))
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Описание", color = Color.White, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Характеристики", color = Color.Gray, fontWeight = FontWeight.Medium)
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Описание с кнопкой "Развернуть"
        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = "Часы Calvin Klein - это сочетание минимализма, точности и современного стиля. Каждая модель создана для тех, кто ценит качество и безупречный внешний вид.\n" +
                            "В коллекции представлены мужские часы, которые подчеркивают мужественность и уверенность...",
                    fontSize = 13.sp,
                    color = Color.Black,
                    lineHeight = 18.sp,
                    maxLines = 6
                )
                Spacer(modifier = Modifier.height(25.dp)) // Место под кнопку
            }

            // Градиентная кнопка "Развернуть"
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(BlueButton.copy(alpha = 0.6f), BlueButton)
                        ),
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Развернуть", color = Color.White, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.KeyboardArrowDown, null, tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun InfoCardsSection() {
    Column(modifier = Modifier.padding(10.dp)) {
        Text("Продавец", fontSize = 16.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 8.dp))

        // Карточка продавца
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.check_icon), // Заглушка аватара
                    contentDescription = null,
                    modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Оператор замесов", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("Подача", fontSize = 12.sp, color = TextGray)
                }
                // Рейтинг и время
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, null, tint = Color(0xFFFFD700), modifier = Modifier.size(16.dp))
                    Text("4.9", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("1 ч", fontSize = 12.sp, color = TextGray)
                }
            }
            Row(modifier = Modifier.padding(start = 64.dp, bottom = 12.dp)) {
                Text("Заказов: 2.4k", fontSize = 11.sp, color = TextGray)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Отзывов: 143", fontSize = 11.sp, color = TextGray)
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text("Бренд", fontSize = 16.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 8.dp))

        // Карточка бренда
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(40.dp).border(1.dp, Color.LightGray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("CK", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Calvin Klein", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("Подача", fontSize = 12.sp, color = TextGray)
                }
            }
        }
    }
}

@Composable
fun HistorySection() {
    Column(modifier = Modifier.padding(10.dp)) {
        Text("История просмотров", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 10.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(3) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.width(160.dp).height(200.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Пустая карточка или градиент внизу как на скрине
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .height(40.dp)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, if (it == 0) MainRed.copy(alpha=0.5f) else Color.Green.copy(alpha=0.5f))
                                    )
                                )
                        )
                    }
                }
            }
        }
    }
}

// --- HELPERS ---

@Composable
fun VariantItem(isSelected: Boolean, imageRes: Int) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) BlueButton else Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(2.dp)
            .clip(RoundedCornerShape(6.dp))
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun StatCard(icon: @Composable () -> Unit, text: String, isImage: Boolean = false) {
    Row(
        modifier = Modifier
            .width(105.dp)
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(10.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(24.dp), contentAlignment = Alignment.Center) {
            icon()
            if (isImage) {
                // Placeholder for stacked images
                Row {
                    Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color.Gray))
                    Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color.DarkGray))
                }
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, fontSize = 10.sp, lineHeight = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Preview
@Composable
fun ProductScreenPreview() {
    ProductDetailScreen()
}