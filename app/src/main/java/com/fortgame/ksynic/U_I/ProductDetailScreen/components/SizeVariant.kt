package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.U_I.ProductDetailScreen.BlueButton
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun SizeVariants(){
    Row(
        Modifier
            .fillMaxWidth()
            .height(fh(30))
            .padding(horizontal = fw(10)),
        horizontalArrangement = Arrangement.spacedBy(fw(10))
    ){
        Size(text = "34", isSelected = true)
        Size(text = "35")

    }
}

@Composable
private fun Size(
    text: String="34",
    isSelected: Boolean = false,
){
    Box(
        Modifier.fillMaxHeight().width(fw(60))
            .border(
                width =3.dp,
                color = if (isSelected) BlueButton  else Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(10.dp)
            )
            .background(Color.White, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 14.sp,



        )
    }
}

@Composable
@Preview
private fun SizeVariantPreview() {
    Box(
        Modifier.fillMaxSize().background(Color.Gray)
    ) {
        SizeVariants()
    }
}