package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.ProductDetailScreen.BlueButton
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw



@Composable
fun VariantItemRow() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(fw(10))
        ) {
        VariantItem(isSelected = true, imageRes = R.drawable.watch_1, colorText = "Серый")
        VariantItem(isSelected = false, imageRes = R.drawable.watch_2, colorText="Чёрный") // Замените на черный вариант
        VariantItem(isSelected = false, imageRes = R.drawable.watch_3, colorText="Серебристый") // Замените на серебряный
    }
}

@Composable
fun VariantItem(
    isSelected: Boolean=true,
    imageRes:Int=R.drawable.watch_1,
    colorText:String="Серый"
) {
    Column(
        Modifier
            .height(fh(113))
            .width(fw(70))
    ) {
        Box(
            modifier = Modifier
                .height(fh(93))
                .fillMaxWidth()
                .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) BlueButton else Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(2.dp)
                .clip(RoundedCornerShape(6.dp))
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text=colorText,
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 10.sp
            )
        }


    }
}

@Composable
@Preview
private fun VariantItemPreview(){
    VariantItemRow()
}