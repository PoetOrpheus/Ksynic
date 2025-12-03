package com.fortgame.ksynic.Layer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R

// ----------------------------------------------------------------
// Верхняя шапка (Адрес, Поиск, Баннер 11.11)
// ----------------------------------------------------------------



@Composable
fun TopHeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp) // Высота фиолетовой подложки
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF6773E6), Color(0xFFEBA6EA))
                )
            )
    ) {
        Column(modifier = Modifier.padding(start = 10.dp,end=10.dp)) {
            // Строка адреса
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "ПВЗ: ул. Королева, 5",
                    //font=Font.Inter,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                // Иконка сообщений
                Image(
                    painter = painterResource(R.drawable.message_without_notification),
                    contentDescription = "Месседжер",
                    modifier = Modifier.padding(top = 6.dp, end = 6.dp)
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            // Поисковая строка
            Surface(
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ищите что то конкретное?...",
                        color = Color(0xFF6E6E6E),
                        fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Баннер 11.11
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFA8C0FF), Color(0xFF3F2B96))
                        )
                    )
                    .padding(horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                // Имитация контента баннера
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "11.11",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "распродажа",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun TopHeaderSectionPreview(){
    Box(modifier = Modifier.fillMaxSize()){
        TopHeaderSection()
    }
}