package com.fortgame.ksynic.U_I.BrandsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.fortgame.ksynic.U_I.HistoryScreen.components.BrandsCard
import com.fortgame.ksynic.U_I.HistoryScreen.components.FiltersRow
import com.fortgame.ksynic.U_I.HistoryScreen.components.SellerCard
import com.fortgame.ksynic.U_I.TopHeaderWithSearchAndReturn
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun BrandsScreen(
    onBackClick: () -> Unit = {}
){
    var isSelect by remember { mutableStateOf(true) }


    Column(
        Modifier.fillMaxSize().background(Color(0xFFF2F2F2))
    ){
        TopHeaderWithSearchAndReturn(onBackClick)
        Spacer(
            Modifier.height(fh(10))
        )
        FiltersRow(
            isSelect,
            { isSelect = !isSelect }
            )
        LazyColumn (
            Modifier
                .fillMaxSize()
                .padding(horizontal = fw(10),vertical = fh(10)),
            verticalArrangement = Arrangement.spacedBy(fh(10)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            items(30){
                if (isSelect) SellerCard() else BrandsCard()
            }


        }
    }
}

@Composable
@Preview
private fun BrandsScreenPreview(){
    BrandsScreen()
}
