package com.fortgame.ksynic.U_I.ProfileScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun CardPickUp(
    modifier: Modifier = Modifier,
    productName: String = "Брюки прямые ТВОЕ",
    image: Painter = painterResource(R.drawable.image_for_profile),
    stateDelivery: String = "Доставлено",
    stateColor: Color = Color(0xFF50C878)
) {
    // Убрали внешний Column с padding(horizontal = 30)
    Row(
        modifier = modifier
            .width(fw(200)) // Точно как в Figma
            .height(fh(100)) // Точно как в Figma
            .background(Color.White, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp)) // Обрезаем контент по краям
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .height(fh(100))
                .width(fw(100)) // Половина карточки под фото
                // Убрал border, так как на макете его обычно не видно, либо он тонкий,
                // но если нужен белый контур внутри:
                .border(4.dp, Color.White, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = fw(10), vertical = fh(10)), // Внутренние отступы текста
            verticalArrangement = Arrangement.SpaceBetween // Распределяем текст красиво по высоте
        ) {
            Text(
                text = productName,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 10.sp,
                maxLines = 3 // Ограничиваем строки, чтобы не вылезало
            )

            Column {
                Text(
                    text = "Доставка в ПВЗ:",
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF919191),
                    lineHeight = 9.sp
                )
                Text(
                    text = stateDelivery,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = stateColor,
                    lineHeight = 10.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun CardPickUpPreview() {
    // Для превью добавим фон, чтобы видеть белую карточку
    Box(modifier = Modifier.padding(10.dp).background(Color.Gray)) {
        CardPickUp()
    }
}