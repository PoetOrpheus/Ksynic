package com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.HistoryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.U_I.Screen.MainScreen.components.ProductGrid
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.TopHeaderWithReturn
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun HistoryScreen(
    onBackClick: () -> Unit = {},
    onProductClick:() -> Unit = {}
){
    Box(modifier = Modifier
        .background(BgGray)) {
        TopHeaderWithReturn(onBackClick)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = fw(5),
                    end = fw(5),
                    top = fh(60)
                ),
        ) {

            // Надпись История просмотров
            item {
                Spacer(Modifier.height(fh(10)))
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(fh(50))
                        .padding(horizontal = fw(25)),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text="История просмотров",
                        fontSize = 30.sp,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // Продукты
            item{
                ProductGrid(onProductClick = onProductClick) // Передаем функцию дальше
            }
        }
    }
}

@Composable
@Preview
private fun HistoryScreenPreview(){
    HistoryScreen()
}