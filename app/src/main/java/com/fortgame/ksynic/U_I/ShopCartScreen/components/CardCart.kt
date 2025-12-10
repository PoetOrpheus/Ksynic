package com.fortgame.ksynic.U_I.ShopCartScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
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
import com.fortgame.ksynic.U_I.atom.CounterCart
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun CardCart(
    price: Int = 4200,
    oldPrice: Int = 21000,
    textProduct: String = "Часы наручные Кварцевые",
    sale: Int = 80,
    color: Color = Color(0xFFCC3333), // Акцентный цвет (Красный)
    isLover:Boolean = true
) {
    // --- ОСНОВНАЯ СТРУКТУРА: COLUMN ---
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .height(fh(150))
                .fillMaxWidth()
        ) {
            // СЛОЙ 1: КОНТЕНТ (Картинка и Текст)
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = fw(5), vertical = fh(5))
            ){
                Image(
                    painter = painterResource(R.drawable.image_for_product_3),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(fw(150))
                        .height(fh(150))
                        .clip(RoundedCornerShape(10.dp))
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = fw(10))
                ) {
                    
                    // Цена
                    Row(
                        Modifier
                            .height(fh(30))
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "$price ₽",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = color,
                            lineHeight = 22.sp
                        )
                        // СЛОЙ 3: ПЛАШКА СКИДКИ
                        Box(
                            modifier = Modifier
                                .width(fw(60))
                                .height(fh(30))
                                .shadow(
                                    elevation = 5.dp, // Figma: Blur 5
                                    shape = RoundedCornerShape(10.dp),
                                    spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                                )
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                               text="- $sale %",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                lineHeight = 16.sp,
                                color=color

                            )
                        }
                    }
                    Spacer(Modifier.height(fh(5)))

                    // Старая цена
                    Box(
                        Modifier
                            .height(fh(20))
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "$oldPrice ₽",
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            color = Color(0xFF999999),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Spacer(Modifier.height(fh(20)))
                    Box(
                        Modifier
                            .width(fw(150))
                            .height(fh(70))
                    ){
                    Text(
                        text = textProduct,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 19.sp,
                        color = Color.Black
                    )}
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    // Отступ 5.dp совпадает с padding картинки товара, чтобы визуально "сесть" на её угол
                    .size(35.dp) // Точный размер 35px по Figma
                    .background(
                        Color.White,
                        // Специфичная форма: BL (BottomStart) острый, остальные по 10dp
                        RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 0.dp
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Внутренний квадрат (Синий чекбокс)
                // "Border 4px" из Figma здесь реализован как padding, создавая белый просвет
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp) // Отступ 4px со всех сторон
                        .clip(RoundedCornerShape(6.dp)) // Скругление самого синего квадратика
                        .background(Color(0xCC5D76CB)) // Цвет "выбранного" состояния (синий)
                    // Если нужно состояние "не выбрано", меняйте цвет на Color(0xFFF2F2F2)
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    // Иконка галочки
                    // Убедитесь, что у вас есть векторная иконка или картинка для галочки
                    // Icon(painter = painterResource(R.drawable.ic_check), contentDescription = null, tint = Color.White)
                    // Или Image, если используете растр:
                    Image(
                        painter = painterResource(R.drawable.check_icon), // Замените на ваш ресурс
                        contentDescription = "Selected",
                        modifier = Modifier.size(14.dp) // Примерный размер галочки
                    )
                }
            }
        } // Конец Box для верхней части

        // --- 2. НИЖНЯЯ ЧАСТЬ (ACTION BAR С СЧЕТЧИКОМ) ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(fh(60)) // Высота для строки действий
                .padding(horizontal = fw(15)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier=Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(fh(30))
                        .width(fw(30))
                        .background(
                            if (isLover) Color(0xFFFFD4D4) else Color.White,
                            RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(if (isLover) R.drawable.lover else R.drawable.unlover),
                        contentDescription = "Favorite"
                    )
                }
                Spacer(Modifier.width(fw(20)))
                Box(
                    modifier = Modifier
                        .height(fh(30))
                        .width(fw(30))
                        .background(Color.White, RoundedCornerShape(10.dp))
                        .border(1.dp, Color(0xFFDCDCDC), RoundedCornerShape(10.dp)),                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(R.drawable.cart),
                        contentDescription = "Delete"
                    ) // Возможно, это иконка мусорки
                }

                Spacer(Modifier.width(fw(20)))
                // --- СЧЕТЧИК (ВАШ ЗАПРОС) ---
                CounterCart(count = 1)
            }

            // Кнопка "Купить" / Другой элемент (для примера)
            Box(
                modifier=Modifier
                    .height(fh(30))
                    .width(fw(130))
                    .border(1.dp, Color(0xFFDCDCDC), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Купить",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    lineHeight = 18.sp
                )
            }
        }
    } // Конец Column
}



@Composable
@Preview
private fun CartCardPreview() {
    Box(modifier = Modifier
        .padding(10.dp)
        .background(Color(0xFFF2F2F2))) {
        CardCart()
    }
}