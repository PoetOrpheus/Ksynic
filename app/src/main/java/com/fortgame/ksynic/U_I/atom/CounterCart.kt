package com.fortgame.ksynic.U_I.atom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun CounterCart(
    count: Int, // Текущее количество
    onDecrement: () -> Unit = {},
    onIncrement: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .width(fw(100)) // Общая ширина счетчика
            .height(fh(30)) // Высота счетчика
            // Фон: светло-серый или белый с тонкой рамкой, как в Figma
            .background(Color.White, RoundedCornerShape(50))
            .border(1.dp, Color(0xFFDCDCDC), RoundedCornerShape(50)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // --- Кнопка Декремента (Минус) ---
        Box(
            modifier = Modifier
                .width(fw(30))
                .fillMaxHeight()
                .clickable(onClick = onDecrement),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "—", // Эм-дэш
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black,
                lineHeight = 20.sp
            )
        }

        // --- Количество ---
        Box(
            modifier=Modifier
                .width(fw(40))
                .height(fh(30)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = count.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = fw(5))
            )
        }

        // --- Кнопка Инкремента (Плюс) ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable(onClick = onIncrement),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black, // Используем акцентный цвет
                lineHeight = 20.sp

            )
        }
    }
}

@Composable
@Preview
private fun CounterCartPreview(){
    CounterCart(1)
}