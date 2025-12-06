package com.fortgame.ksynic.U_I.ProfileScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw
import org.w3c.dom.Text

@Composable
fun BottomProfileMenuNavigation(){
    Box(
        modifier= Modifier
            .height(fh(200))
            .fillMaxWidth()
            .padding(horizontal = fw(15))
            .background(Color.White, RoundedCornerShape(10.dp))
    ){
        Column(
            modifier = Modifier.padding(
                vertical = fh(10)
            )
        ) {
            RowCategory()
        }
    }
}

@Composable
private fun RowCategory(
    text: String= "Заказы",
    line:Boolean= true
){
    Row(
        modifier=Modifier
            .height(fh(30))
            .fillMaxWidth()
            .padding(horizontal = fw(15)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = text,
            fontSize =12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 14.sp

        )

        Image(
            painter = painterResource(R.drawable.profile_right_2),
            contentDescription = null,

        )
        if (line){

        }
    }
}

@Composable
@Preview
private fun BottomProfileMenuNavigationPreview(){
    BottomProfileMenuNavigation()
}