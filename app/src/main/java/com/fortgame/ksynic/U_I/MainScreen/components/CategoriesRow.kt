package com.fortgame.ksynic.U_I.MainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

// ----------------------------------------------------------------
// Категории (Кружочки)
// ----------------------------------------------------------------
@Composable
fun CategoriesRow(
    onHistoryClick: () -> Unit = {}, // ДОБАВЬТЕ этот параметр
    onCanBeSeller: () -> Unit = {}, // ДОБАВЬТЕ этот параметр
    onCategoryClick:() -> Unit = {},
    onBrandsClick:() -> Unit = {}
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(fh(115))
            .padding(horizontal = fw(10))
            .padding(top = fh(10)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CategoryItem("Стать\nпродавцом", Color(0xFF5D76CB),Color(0xFFD23E41), painterResource(R.drawable.seller_icon), 30,30, onCanBeSeller)
        CategoryItem("Магазины\nи бренды", Color(0xFF5D76CB),Color(0xFFCC5086), painterResource(R.drawable.shops_and_brans),30,30, onBrandsClick)
        CategoryItem("Финансы", Color(0xFF5D76CB), Color(0xFFDAA636),painterResource(R.drawable.finances_icon_home),30,30)
        CategoryItem("История\nпросмотров", Color(0xFF5D76CB), Color(0xFF21936F),painterResource(R.drawable.history_icon),40,40, onHistoryClick)
        CategoryItem("Каталог", Color(0xFF5D76CB), Color(0xFF1D36D7),painterResource(R.drawable.category_icon),30,44, onCategoryClick)
    }
}

@Composable
fun CategoryItem(
    text: String,
    color1: Color,
    color2: Color,
    painter: Painter,
    width:Int,
    height:Int,
    onClick:()-> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(fw(70))
            .height(fh(110))
    ) {
        Box(
            modifier = Modifier
                .width(fw(70))
                .height(fh(70))
                .clip(RoundedCornerShape(18.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(color1, color2)
                    )
                )
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier
                .width(fw(width))
                .height(fh(height))){
            Image(painter = painter, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.height(fh(4)))
        Box(
            modifier = Modifier
                .width(fw(70))
                .height(fh(40)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 9.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

@Composable
@Preview
private fun CategoriesRowPreview(){
    CategoriesRow()
}