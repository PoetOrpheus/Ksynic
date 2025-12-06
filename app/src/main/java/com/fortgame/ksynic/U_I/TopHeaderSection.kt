package com.fortgame.ksynic.U_I

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

// ----------------------------------------------------------------
// Верхняя шапка (Адрес, Поиск, Баннер 11.11)
// ----------------------------------------------------------------



@Composable
fun TopHeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            // Высота блока по макету Figma: 220px на листе 450x900,
            // конвертируем через утилиту fh(...) в dp текущего экрана
            .height(fh(220))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF5D76CB), Color(0xFFFCB4D5)),
                    start = Offset(0f, 0f),  // Левый верхний
                    end = Offset(600f, 600f), // Правый нижний (или используйте Offset.Infinite)
                )
            )
    ) {
        Column(
            modifier = Modifier.padding(
                start = fw(15),
                end = fw(15),
                top = fh(10)
            )
        ) {
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
                    modifier = Modifier
                        .width(fw(40))
                        .height(fh(30))
                        //.padding(top = fh(10), end = fw(15))
                )

            }

            // Отступ 10px вниз от иконки чата до поисковой строки
            Spacer(modifier = Modifier.height(fh(10)))

            // Поисковая строка
            Surface(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(fw(420))
                    .height(fh(40)),
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

            // Отступ 10px вниз от поисковой строки до рекламного блока
            Spacer(modifier = Modifier.height(fh(10)))

            // Баннер 11.11
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(fw(420))
                    .height(fh(110))
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