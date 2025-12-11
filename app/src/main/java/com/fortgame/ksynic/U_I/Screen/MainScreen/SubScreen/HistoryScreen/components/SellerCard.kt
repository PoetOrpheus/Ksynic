package com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.HistoryScreen.components

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
fun SellerCard(){
    Row(
        Modifier
            .height(fh(60))
            .shadow(
                elevation = 5.dp, // Figma: Blur 5
                shape = RoundedCornerShape(10.dp),
                spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
            )
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Spacer(Modifier.width(fw(10)))
        Image(
            painterResource(R.drawable.ava_seller),
            null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(fh(40))
                .width(fw(40))

        )
        Spacer(Modifier.width(fw(10)))

        Column(
            Modifier.width(fw(235)),
            verticalArrangement = Arrangement.Center,
        ){
            Box(
                Modifier.height(fh(35)),
                contentAlignment = Alignment.CenterStart
            ){
                Text(
                    text="Оператор замесов",
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(
                Modifier
                    .fillMaxHeight()
                    .padding(bottom = fh(10)),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painterResource(R.drawable.delivery),
                    null
                )
                Spacer(Modifier.width(fw(10)))

                Text(
                    text="Заказов",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF727272),
                    lineHeight = 10.sp
                )
                Spacer(Modifier.width(fw(10)))

                Text(
                    text="3.4k",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF727272),
                    lineHeight = 10.sp
                )

                Spacer(Modifier.width(fw(30)))

                Image(
                    painterResource(R.drawable.like_and_dislike),
                    null
                )
                Spacer(Modifier.width(fw(10)))

                Text(
                    "Отзывов",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF727272),
                    lineHeight = 10.sp
                )

                Spacer(Modifier.width(fw(10)))

                Text(
                    "543",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF727272),
                    lineHeight = 10.sp
                )




            }
        }

        //Рейтинг
        Spacer(
            Modifier.width(fw(63))
        )
        Column(
            Modifier
                .width(fw(40))
                .height(fh(40))
                .shadow(
                    elevation = 5.dp, // Figma: Blur 5
                    shape = RoundedCornerShape(5.dp),
                    spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                )
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Image(
                painterResource(R.drawable.star_profile_menu),
                null,
            )

            Box(
                Modifier
                    .height(fh(18))
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    "4.9",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 10.sp,
                    color = Color.Black
                    )
            }

        }

        Spacer(Modifier.width(fw(10)))


    }
}

@Composable
@Preview
private fun SellerCardPreview(){
    SellerCard()
}