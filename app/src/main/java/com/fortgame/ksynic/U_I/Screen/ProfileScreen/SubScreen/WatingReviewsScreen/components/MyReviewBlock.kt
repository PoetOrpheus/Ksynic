package com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.WatingReviewsScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import kotlin.String

@Composable
fun MyReviewBlock(
    countStar:Int=1,
    textProduct:String = "Часы наручные Кварцевые",
    textPreview:String="гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! гавно! "
){
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
                        )
                    }
                    Spacer(Modifier.width(fw(10)))
                }
            }
        }

        if (countStar>0) {
            Spacer(Modifier.height(fh(10)))
            if (textPreview!=null)
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = fw(10))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    )
                    .background(Color.White),
                //contentAlignment = Alignment.Center
            ){
                Text(
                    text=textPreview,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 12.sp,
                    modifier = Modifier.padding(fw(10)),
                )
            }
            Spacer(Modifier.height(fh(5)))
            Text(
                text="09.12.2025",
                fontSize = 9.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 12.sp,
                modifier=Modifier.padding(horizontal = fw(17)),
            )
            Spacer(Modifier.height(fh(5)))

        }

    }
}

@Composable
@Preview
private fun MyReviewBlockPreview(){
    MyReviewBlock()
}
