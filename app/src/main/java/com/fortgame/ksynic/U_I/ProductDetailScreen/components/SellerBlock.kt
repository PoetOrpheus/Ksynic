package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

/**
 * Форматирует количество заказов для отображения (например, 3400 -> "3.4k")
 */
private fun formatOrdersCount(count: Int): String {
    return when {
        count >= 1000 -> String.format("%.1fk", count / 1000.0)
        else -> count.toString()
    }
}

@Composable
fun SellerBlock(
    seller: com.fortgame.ksynic.mvvm.model.Seller
){
    Column(
        Modifier
            .fillMaxWidth()
            .height(fh(134))
            .background(Color.White,RoundedCornerShape(10.dp))
    ){
        Spacer(Modifier.height(fh(5)))
        //Текс Продавей
        Box(
            Modifier
                .height(fh(30))
                .padding(horizontal = fw(25)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text="Продавец",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 18.sp,
            )
        }

        Spacer(Modifier.height(fh(5)))

        // Информация о прадавце
        Row(
            Modifier
                .fillMaxWidth()
                .height(fh(60))
                .padding(horizontal = fw(15))
                .shadow(
                    elevation = 5.dp, // Figma: Blur 5
                    shape = RoundedCornerShape(10.dp),
                    spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                )
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(Modifier.width(fw(10)))
            Image(
                painterResource(R.drawable.ava_seller),
                null,
                Modifier
                    .width(fw(40))
                    .height(fh(40))
            )
            Spacer(Modifier.width(fw(10)))
            Column(
                Modifier
                    .width(fw(230))
                    .fillMaxHeight()
            ){
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(fh(35)),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = seller.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 16.sp,
                    )
                }


                Text(
                    text="Перейти",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 10.sp,
                )
            }

            Spacer(Modifier.width(fw(10)))

            // Отзывы
            Column(
                Modifier
                    .width(fw(40))
                    .height(fh(40))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    )
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    painter =painterResource(R.drawable.star_profile_menu),
                    null,
                    Modifier
                        .width(fw(16))
                        .height(fh(16))

                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(fh(18)),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = String.format("%.1f", seller.rating),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 10.sp,
                    )
                }
            }

            Spacer(Modifier.width(fw(15)))

            Column(
                Modifier
                    .width(fw(40))
                    .height(fh(40))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    )
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    painter =painterResource(R.drawable.chat_blue),
                    null,
                    Modifier
                        .width(fw(20))
                        .height(fh(16))

                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(fh(18)),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text="Чат",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 10.sp,
                    )
                }
            }
        }

        Spacer(Modifier.height(fh(10)))
        // Нижняя панель
        
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ){
            Image(
                painter =painterResource(R.drawable.delivery),
                null,

            )
            Spacer(Modifier.width(fw(10)))
            Text(
                text="Заказов",
                fontSize=10.sp,
                color=Color(0xFF727272),
                lineHeight = 10.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(Modifier.width(fw(10)))

            Text(
                text = formatOrdersCount(seller.ordersCount),
                fontSize=10.sp,
                color=Color(0xFF727272),
                lineHeight = 10.sp,
            )

            Spacer(Modifier.width(fw(30)))
            Image(
                painterResource(R.drawable.like_and_dislike),
                null
            )
            Spacer(Modifier.width(fw(10)))
            Text(
                "Отзывов",
                fontSize=10.sp,
                color=Color(0xFF727272),
                lineHeight = 10.sp
            )
            Spacer(Modifier.width(fw(10)))

            Text(
                text = seller.reviewsCount.toString(),
                fontSize = 10.sp,
                color=Color(0xFF727272),
                lineHeight = 10.sp
            )
            Spacer(Modifier.width(fw(25)))

        }

    }
}

@Composable
@Preview
private fun SellerBlockPreview(){
    SellerBlock(
        seller = com.fortgame.ksynic.mvvm.model.TestSellers.operatorZamesov
    )
}