package com.fortgame.ksynic.U_I.CatalogScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.fortgame.ksynic.U_I.MainScreen.components.ProductGrid
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.TopHeaderWithReturn
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun CatalogScreen(
    onBackClick:()->Unit = {},
){
    Box(modifier = Modifier
        .background(BgGray)) {
        TopHeaderWithReturn(onBackClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = fw(5),
                    end = fw(5),
                    top = fh(60)
                ),
        ) {

            // Надпись История просмотров
            Spacer(Modifier.height(fh(10)))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(fh(50))
                    .padding(horizontal = fw(25)),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "История просмотров",
                    fontSize = 30.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.height(fh(10)))

            // Продукты
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = fw(20), vertical = fh(20)),
                horizontalArrangement = Arrangement.spacedBy(fw(25)),
                verticalArrangement = Arrangement.spacedBy(fh(20)),
                modifier = Modifier.fillMaxSize()
            ) {
                item { CatalogBox() }
                item { CatalogBox("Мужская одежда", Color(0xFFFF8888)) }
                item { CatalogBox("Детская одежда", Color(0xFFCBDB4D)) }
                item { CatalogBox("Обувь",Color(0xFFA3E580)) }
                item { CatalogBox("Аксессуары",Color(0xFF6CB8D4)) }
                item { CatalogBox("Бытовая техника",Color(0xFF288C53)) }
                item { CatalogBox("Электроника",Color(0xFF8D69E2)) }
                item { CatalogBox("Дом и сад",Color(0xFFB44460)) }
                item { CatalogBox("Мебель",Color(0xFF36709F)) }
                item { CatalogBox("Строитель-ство",Color(0xFF458461)) }
                item { CatalogBox("Автотовары",Color(0xFF492980)) }
                item { CatalogBox("Книги",Color(0xFF94774E)) }
                item { CatalogBox("Хобби",Color(0xFF7E2E2E)) }
                item { CatalogBox("Красота и здоровье",Color(0xFF63215F)) }
                item { CatalogBox("Канцелярия",Color(0xFF981A2A)) }

            }
        }
    }
}

@Composable
private fun CatalogBox(
    text:String = "Женская одежда",
    colorBox: Color = Color(0xFFFF977D)
){
    Box(
        Modifier
            .width(fw(120))
            .height(fh(120))
            .background(colorBox, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.TopStart
    ){
        Box(
            Modifier
                .width(fw(100))
                .height(fh(60))
                .padding(10.dp)
        ){
        Text(
            text=text,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 14.sp,
            color=Color.White,
        )
    }
    }
}

@Composable
@Preview
private fun CatalogScreenPreview(){
    CatalogScreen()
}