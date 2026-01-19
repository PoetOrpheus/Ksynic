package com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.HistoryScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.theme.BlueGradient
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun FiltersRow(
    isSelect: Boolean,
    onClick: () -> Unit = {}

) {

    Row(
        Modifier
            .fillMaxWidth()
            .height(fh(40)),
        verticalAlignment = Alignment.CenterVertically,
    ){
        // Фильтр
        Spacer(Modifier.width(fw(25)))
        Box(
            Modifier
                .width(fw(30))
                .height(fh(30))
                .shadow(
                   elevation = 5.dp, // Figma: Blur 5
                    shape = RoundedCornerShape(10.dp),
                    spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                )
                .background(Color.White),
            contentAlignment = Alignment.Center
        ){
            Image(
                painterResource(R.drawable.filtr_setting),
                null,
                modifier=Modifier
                    .width(fw(18))
                    .height(fh(18))

            )
        }

        // Категории
        Spacer(Modifier.width(fw(25)))
        Box(
            Modifier
                .width(fw(30))
                .height(fh(30))
                .shadow(
                    elevation = 5.dp, // Figma: Blur 5
                    shape = RoundedCornerShape(10.dp),
                    spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                )
                .background(Color.White),
            contentAlignment = Alignment.Center
        ){
            Image(
                painterResource(R.drawable.category_favorite_setting),
                null,
                modifier=Modifier
                    .width(fw(18))
                    .height(fh(18))

            )
        }

        Spacer(Modifier.width(fw(25)))

        // Магазины
        SwitchButton(
            "Магазины",
            isSelect,
            onClick = onClick
        )
        Spacer(Modifier.width(fw(13)))

        // Разделительная линия
        Box(
            Modifier
                .fillMaxHeight()
                .width(fw(4))
                .background(Color(0xFFA2A2A2),RoundedCornerShape(10.dp))

        )
        Spacer(Modifier.width(fw(13)))

        // Бренды
        SwitchButton(
            "Бренды",
            !isSelect,
            onClick = onClick
        )
    }
}


@Composable
private fun SwitchButton(
    text: String = "Магазины",
    isSelect: Boolean = false,
    onClick: () -> Unit = {}
){
    Box(
        modifier= if (isSelect) {
            Modifier
                .width(fw(130))
                .height(fh(30))
                .shadow(
                    elevation = 5.dp, // Figma: Blur 5
                    shape = RoundedCornerShape(10.dp),
                    spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                )
                .background(BlueGradient)

        }
        else{
            Modifier
                .width(fw(130))
                .height(fh(40))
                .clickable {
                    onClick()
                }
        },
        contentAlignment = Alignment.Center

    ){
        Text(
            text=text,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 17.sp,
            color=if (isSelect) Color.White else Color.Black
        )

    }
}

@Composable
@Preview
private fun FiltersRowPreview(){
    Box(Modifier.background(Color.White)){
    FiltersRow(true)
}}