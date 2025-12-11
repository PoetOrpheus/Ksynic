package com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.WatingReviewsScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
fun ReviewBlock(
    textProduct:String = "Часы наручные Кварцевые"
){
    var countStar by remember { mutableStateOf(0) }

    Column(
        Modifier
            .width(fw(420))
            .background(Color.White,RoundedCornerShape(20.dp))
    ){
        Row() {
            Image(
                painterResource(R.drawable.image_for_product_3),
                null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(fw(100))
                    .height(fh(100))
                    .border(4.dp, Color.White,RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
            )
            Spacer(Modifier.width(fw(10)))
            Column(Modifier.padding(vertical = fh(5))) {
                Box(
                    Modifier
                        .width(fw(120))
                        .height(fh(40))
                ) {
                    Text(
                        text = textProduct,
                        fontSize = 10.sp,
                        lineHeight = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                    )
                }
                Row(
                    Modifier
                        .height(fh(40)),
                    horizontalArrangement = Arrangement.spacedBy(fw(10)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .width(fw(40))
                            .height(fh(40)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(R.drawable.star),
                            null,
                            colorFilter = if (countStar>=1) ColorFilter.tint(Color(0xFFF8C82D)) else null,
                            modifier = Modifier.clickable(onClick = {
                               countStar=1
                            })
                        )
                    }
                    Box(
                        Modifier
                            .width(fw(40))
                            .height(fh(40)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(R.drawable.star),
                            null,
                            colorFilter = if (countStar>=2) ColorFilter.tint(Color(0xFFF8C82D)) else null,
                            modifier = Modifier.clickable(onClick = {
                               countStar=2
                            })
                        )
                    }
                    Box(
                        Modifier
                            .width(fw(40))
                            .height(fh(40)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(R.drawable.star),
                            null,
                            colorFilter = if (countStar>=3) ColorFilter.tint(Color(0xFFF8C82D)) else null,
                            modifier = Modifier.clickable(onClick = {
                               countStar=3
                            })
                        )
                    }
                    Box(
                        Modifier
                            .width(fw(40))
                            .height(fh(40)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(R.drawable.star),
                            null,
                            colorFilter = if (countStar>=4) ColorFilter.tint(Color(0xFFF8C82D)) else null,
                            modifier = Modifier.clickable(onClick = {
                               countStar=4
                            })
                        )
                    }
                    Box(
                        Modifier
                            .width(fw(40))
                            .height(fh(40)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(R.drawable.star),
                            null,
                            colorFilter = if (countStar>=5) ColorFilter.tint(Color(0xFFF8C82D)) else null,
                            modifier = Modifier.clickable(onClick = {
                                countStar=5
                            })
                        )
                    }
                    Spacer(Modifier.width(fw(10)))
                    if (countStar>0){
                        Box(
                            Modifier
                                .width(fw(30))
                                .height(fh(30))
                                //.padding(top=fh(50),start=fw(20))
                                .shadow(
                                    elevation = 5.dp, // Figma: Blur 5
                                    shape = CircleShape,
                                    spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                                )
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ){
                            Image(
                                painterResource(R.drawable.right),
                                null
                            )
                        }
                    }
                }
            }
        }

        if (countStar>0) {
            Spacer(Modifier.height(fh(10)))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(fh(32))
                    .padding(horizontal = fw(10))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = CircleShape,
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    )
                    .background(Color.White),
               //contentAlignment = Alignment.Center
            ){
                Text(
                    text="Напишите свое мнение о данном товаре...",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 12.sp,
                    color = Color(0xFFA2A2A2),
                    modifier = Modifier.padding(fw(10)),
                )
            }
            Spacer(Modifier.height(fh(12)))

        }

    }
}


@Composable
@Preview
private fun ReviewBlockPreview(){
    Box(Modifier.fillMaxSize()) {
        ReviewBlock()
    }
}