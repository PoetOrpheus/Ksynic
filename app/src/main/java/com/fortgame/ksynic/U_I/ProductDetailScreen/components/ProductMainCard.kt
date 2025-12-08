package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh

@Composable
fun ProductMainCard(){
    Column(
        modifier=Modifier
            .fillMaxWidth()
            .height(fh(725))
            .background(Color.White, RoundedCornerShape(20.dp))
    ){

        // Основная фотография
        Image(
            painter = painterResource(R.drawable.watch_1),
            contentDescription = null,
            modifier=Modifier
                .fillMaxWidth()
                .height(fh(440))
                .clip(RoundedCornerShape(20.dp))
        )

        // Блок карусели
        Spacer(Modifier.height(fh(4)))

    }
}

@Composable
@Preview
private fun ProductMainCardPreview(){
    ProductMainCard()
}