package com.fortgame.ksynic.U_I.ShopCartScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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

@Composable
fun CardCart(
    price:Int = 4200,
    oldPrice: Int = 21000,
    textProduct:String = "Часы наручные Кварцевые",
    sale:Int = 80,
    color: Color = Color(0xFFCC3333),
) {
    Column(
        modifier = Modifier
            .height(fh(210))
            .width(fw(420))
            .background(Color.White, RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp))
    ) {
        Row(
            modifier=Modifier
                .height(fh(150))
        ) {
            Image(
                painter = painterResource(R.drawable.image_for_product_3),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier=Modifier
                    .width(fw(150))
                    .height(fh(150))
                    .padding(start = fw(5), top = fh(5))
                    .clip(RoundedCornerShape(10.dp))
            )
            Column(
                modifier=Modifier
                    .width(fw(150))
                    .fillMaxHeight()
                    .padding(top = fh(10), start = fw(10))
            ) {
                Box(
                    modifier=Modifier
                        .fillMaxWidth()
                        .height(fh(30)),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text="$price ₽",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color=color,
                        lineHeight = 22.sp
                    )
                }
                Spacer(Modifier.height(fh(5  )))

                Box(
                    modifier=Modifier
                        .fillMaxWidth()
                        .height(fh(20)),
                    contentAlignment = Alignment.TopStart
                ){
                    Text(
                        text = "$oldPrice ₽",
                        style = TextStyle(textDecoration = TextDecoration.LineThrough),
                        color = Color.Gray,
                        fontSize = 10.sp,
                        lineHeight = 12.sp
                    )
                }
                Spacer(Modifier.height(fh(20)))

                Text(
                    text=textProduct,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 18.sp

                )
            }

            Spacer(modifier = Modifier.width(fw(40)))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                // Начинаем с полностью прозрачного белого, чтобы был плавный переход
                                Color.White.copy(alpha = -1f),
                                // Переход в светло-красный
                                Color(0xFFFFECEC),
                                // Внизу насыщенный красный (из вашего кода), но чуть прозрачнее для мягкости
                                color
                            )
                        )
                    )
                ) {

                Box(
                    modifier=Modifier
                        .width(fw(60))
                        .height(fh(30))
                        .padding(top=fh(10))
                        .background(Color.White,RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text="-$sale %",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 18.sp,
                        color=color

                    )
                }

            }
        }
    }
}

@Composable
@Preview
private fun CartCardPreview() {
    CardCart()
}