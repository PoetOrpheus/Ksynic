package com.fortgame.ksynic.U_I.Screen.ProfileScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun BottomProfileMenuNavigation() {
    // Список названий меню точно как на скриншоте
    val menuItems = listOf(
        "Заказы",
        "Возвраты",
        "Купленные товары",
        "Избранные магазины и бренды",
        "Отзывы",
        "Сертификаты и промокоды"
    )

    Box(
        modifier = Modifier
            // Высоту лучше сделать wrapContent или достаточно большой,
            // 200 может не хватить на 6 элементов.
            // fh(220) будет надежнее, или убрать фиксированную высоту.
            // Но оставляю по вашему шаблону, возможно придется увеличить до 220-230.
            // .height(fh(230)) // Рекомендую убрать фикс. высоту, если контент динамический
            .fillMaxWidth()
            .padding(horizontal = fw(15))
            .background(Color.White, RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier.padding(vertical = fh(5)) // Чуть уменьшил вертикальный паддинг контейнера
        ) {
            // Проходимся по списку и создаем элементы
            menuItems.forEachIndexed { index, item ->
                RowCategory(
                    text = item,
                    // Линию рисуем всем, КРОМЕ последнего элемента
                    line = index != menuItems.lastIndex
                )
            }
        }
    }
}

@Composable
private fun RowCategory(
    text: String,
    line: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Сама строка с текстом и иконкой
        Row(
            modifier = Modifier
                .height(fh(35)) // Чуть увеличил высоту строки для удобства нажатия (было 30)
                .fillMaxWidth()
                .padding(horizontal = fw(15)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 14.sp,
                color = Color.Black // Явно задаем цвет текста
            )

            Image(
                painter = painterResource(R.drawable.profile_right_2),
                contentDescription = null,
            )
        }

        // Разделительная линия
        if (line) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    // Чтобы линия была внутри паддингов (как текст), добавьте padding здесь.
                    // Если хотите от края до края карточки — уберите padding.
                    // Обычно красиво, когда линия выровнена с текстом:
                    .padding(horizontal = fw(15))
                    .height(1.dp) // Ваше требование 1dp (можно использовать 0.5.dp для "hairline")
                    .background(Color(0xFFF0F0F0)) // Очень светло-серый цвет, как на макетах
            )
        }
    }
}

@Composable
@Preview
private fun BottomProfileMenuNavigationPreview() {
    // Добавил фон для превью, чтобы было видно белую карточку
    Box(modifier = Modifier.background(Color(0xFFF2F2F2)).padding(20.dp)) {
        BottomProfileMenuNavigation()
    }
}