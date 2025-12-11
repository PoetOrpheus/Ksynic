package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.ProductDetailScreen.MainRed
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw


@Composable
fun InfoCardsSection(
    description: String? = null
){
    var showText by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(10.dp))
    ){
        Spacer(Modifier.height(fh(10)))
        Row(Modifier
            .height(fh(30))
            .padding(horizontal = fw(10)),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Box(
                Modifier
                    .width(fw(120))
                    .fillMaxHeight()
                    .background(Color(0xFF5D76CB),RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text="Описание",
                    fontSize = 16.sp,
                    color=Color.White,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(
                Modifier.width(fw(10))
            )

            Box(
                Modifier
                    .width(fw(175))
                    .fillMaxHeight()
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    )
                    .background(Color.White),
                contentAlignment = Alignment.Center

            ){
                Text(
                    text="Характеристики",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 18.sp
                )
            }
        }
        Spacer (Modifier.height(fh(10)))

        Box(
            modifier= if (showText){
                Modifier
                    .fillMaxWidth()
            } else {
                Modifier.fillMaxWidth().height(fh(160))
            },
        ){
            Text(
                text = description ?: "Описание товара отсутствует",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 14.sp,
                modifier = Modifier
                    .padding(horizontal = fw(10))
            )
            if (!showText){
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(fh(50))
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.White.copy(alpha = 0.8f), Color(0xFF5D76CB))
                            )
                        )
                        .align(Alignment.BottomCenter)
                        .clickable(onClick = { showText =true}),
                    contentAlignment = Alignment.CenterEnd

                ){
                    Row(
                        Modifier
                            .height(fh(30))
                            .fillMaxWidth()
                            .padding(horizontal = fw(10))
                    ){
                        Box(
                            Modifier
                                .fillMaxHeight()
                                .width(fw(400)),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            Text(
                                text="Развернуть",
                                fontSize = 18.sp,
                                color=Color.White,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 20.sp,
                            )
                        }
                        Spacer(Modifier.width(fw(4)))
                        Image(
                            painter = painterResource(R.drawable.down_icon),
                            null,
                            Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun InfoCardsSectionPreview(){
    InfoCardsSection()
}