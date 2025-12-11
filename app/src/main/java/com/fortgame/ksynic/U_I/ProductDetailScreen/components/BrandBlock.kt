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

@Composable
fun BrandBlock(
    brand: com.fortgame.ksynic.mvvm.model.Brand
){
    Column(
        Modifier
            .fillMaxWidth()
            .height(fh(110))
            .background(Color.White,RoundedCornerShape(10.dp))
    ){
        Spacer(Modifier.height(fh(5)))

        //Текс Бренд
        Box(
            Modifier
                .height(fh(30))
                .padding(horizontal = fw(25)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text="Бренд",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 18.sp,
            )
        }

        Spacer(Modifier.height(fh(5)))

        // Информация о бренде
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
                painterResource(R.drawable.calvin_clein),
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
                        text = brand.name,
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
        }

    }
}

@Composable
@Preview
private fun BrandBlockPreview(){
    BrandBlock(
        brand = com.fortgame.ksynic.mvvm.model.TestBrands.calvinKlein
    )
}