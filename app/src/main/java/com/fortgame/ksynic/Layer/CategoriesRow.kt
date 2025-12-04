package com.fortgame.ksynic.Layer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

// ----------------------------------------------------------------
// Категории (Кружочки)
// ----------------------------------------------------------------
@Composable
fun CategoriesRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(fh(115))
            .padding(horizontal = fw(10))
            .padding(top = fh(10)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CategoryItem("Стать\nпродавцом", Color(0xFFD15664), Icons.Default.Build)
        CategoryItem("Магазины\nи бренды", Color(0xFF9E56D1), Icons.Default.Build)
        CategoryItem("Финансы", Color(0xFFC7A656), Icons.Default.Build)
        CategoryItem("История\nпросмотров", Color(0xFF358C7F), Icons.Default.Build)
        CategoryItem("Каталог", Color(0xFF3B56D3), Icons.Default.Build)
    }
}

@Composable
fun CategoryItem(text: String, color: Color, icon: ImageVector) {
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
                    brush = Brush.linearGradient(
                        colors = listOf(color, color.copy(alpha = 0.7f))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.White)
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
                fontSize = 10.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
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