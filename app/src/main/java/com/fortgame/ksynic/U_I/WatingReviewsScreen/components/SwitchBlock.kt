package com.fortgame.ksynic.U_I.WatingReviewsScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun SwitchBlock(
    isSelect: Boolean,
    onClick: () -> Unit) {

    Row(
        Modifier
            .height(fh(60))
            .width(fw(420))
            .background(Color.White, RoundedCornerShape(20.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        //Spacer(Modifier.width(fw(10)))

        Block("Ждут отзыва", "5 товаров ", isSelect, onClick)

        //Spacer(Modifier.width(fw(20)))

        Block("Мои отзывы", "2 332 товара ", !isSelect, onClick)


    }
}

@Composable
private fun Block(
    text:String,
    textCount:String,
    isSelect:Boolean,
    onClick:() -> Unit = {},
){
    Column(
        Modifier
            .height(fh(40))
            .width(fw(190))
            .shadow(
                elevation = 5.dp, // Figma: Blur 5
                shape = RoundedCornerShape(10.dp),
                spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
            )
            .background(
                color= if (isSelect) Color(0xFF5D76CB) else Color.White
            )
            .clickable {
                if (!isSelect) {
                    onClick()
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            Modifier
                .height(fh(25))
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text=text,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color= if (isSelect) Color.White else Color.Black
            )
        }

        Box(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text=textCount,
                fontSize = 8.sp,
                lineHeight = 8.sp,
                fontWeight = FontWeight.Light,
                color= if (isSelect) Color.White else Color.Black
            )
        }
    }
}