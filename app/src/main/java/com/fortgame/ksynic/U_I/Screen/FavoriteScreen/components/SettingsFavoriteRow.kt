// Файл SettingsFavoriteRow.kt

package com.fortgame.ksynic.U_I.Screen.FavoriteScreen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.atom.Switch
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@SuppressLint("UnrememberedMutableState")
@Composable
fun SettingsFavoriteRow() {

    var isInStockEnableby by remember { mutableStateOf(false) }
    var isSaleEnable by remember { mutableStateOf(false) }

    // Вся строка с отступами слева и справа
    Row(
        modifier = Modifier
            .fillMaxWidth() // Растягиваем на всю ширину родителя
            .height(fh(32)) // Фиксированная высота строки
            .padding(horizontal = fw(15))
    ) {

        // --- 1. КНОПКА "ФИЛЬТР" (Иконка с кликабельностью) ---
        Box(
            modifier = Modifier
                .width(fw(30))
                .height(fh(30))
                .clip(RoundedCornerShape(fw(10)))
                .background(Color.White)
                .clickable { /* TODO: Ваша логика открытия фильтра */ } // Делаем кликабельным
                ,
            contentAlignment = Alignment.Center

        ){
            Image(
                painter = painterResource(R.drawable.filtr_setting),
                contentDescription = "Фильтр",
                modifier = Modifier
                .width(fw(18))
                .height(fh(18))
            )
        }



        Spacer(modifier = Modifier.width(fw(20))) // Пробел между Фильтром и Категориями

        // --- 2. КНОПКА "КАТЕГОРИИ" (Иконка с кликабельностью) ---
        Box(
            modifier = Modifier
                .width(fw(30))
                .height(fh(30))
                .clip(RoundedCornerShape(fw(10)))
                .background(Color.White)
                .clickable { /* TODO: Ваша логика открытия фильтра */ } // Делаем кликабельным
            ,
            contentAlignment = Alignment.Center

        ){
            Image(
                painter = painterResource(R.drawable.category_favorite_setting),
                contentDescription = "Категории",
                modifier = Modifier
                    .width(fw(18))
                    .height(fh(18))
            )
        }

        // --- 3. РАЗДЕЛИТЕЛЬ И СВОБОДНОЕ ПРОСТРАНСТВО ---

        Spacer(modifier = Modifier.width(fw(40))) // Пробел между Категориями и В наличии

        // --- 4. КНОПКА "ПОКАЗЫВАТЬ ТОВАРЫ" (С фоном и иконкой) ---
        Box(
            modifier = Modifier
               // .width(fw(80)) // Фиксированная ширина кнопки (подстройте по Figma)
                .height(fh(30)), // Фиксированная высота кнопки
            contentAlignment = Alignment.CenterEnd // Выравнивание контента по центру
        ) {
            // Текст
            Text(
                text = "В наличии",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF353535)
            )

        }

        Spacer(modifier = Modifier.width(fw(10))) // Пробел между В налачии и переключалки

        Box(
            modifier = Modifier.height(fh(30)),
            contentAlignment = Alignment.Center
        ) {
            Switch(checked = isInStockEnableby, onCheckedChange = {isInStockEnableby=!isInStockEnableby})
        }

        Spacer(modifier = Modifier.width(fw(20))) // Пробел между Переключалкой и По скидке

        Box(
            modifier = Modifier
                //.width(fw(88)) // Фиксированная ширина кнопки (подстройте по Figma)
                .height(fh(30)), // Фиксированная высота кнопки
            contentAlignment = Alignment.Center // Выравнивание контента по центру
        ) {
            // Текст
            Text(
                text = "По скидке",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF353535)
            )

        }

        Spacer(modifier = Modifier.width(fw(10))) // Пробел между По скидке и Переключалкой
        Box(
            modifier = Modifier.height(fh(30)),
            contentAlignment = Alignment.Center
        ) {
            Switch(checked = isSaleEnable, onCheckedChange = {isSaleEnable=!isSaleEnable})
        }


    }
}

@Preview
@Composable
private fun SettingsFavoriteRowPreview() {
    SettingsFavoriteRow()
}