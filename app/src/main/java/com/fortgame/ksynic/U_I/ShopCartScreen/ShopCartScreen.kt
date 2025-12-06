package com.fortgame.ksynic.U_I.ShopCartScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.fortgame.ksynic.U_I.TopHeaderWithoutSearch
import com.fortgame.ksynic.utils.fh

@Composable
fun ShopCartScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
    ) {
        TopHeaderWithoutSearch()
        Spacer(modifier = Modifier.height(fh(10)))
    }
}

@Composable
@Preview
private fun ShopCartScreenPreview(){
    ShopCartScreen()
}