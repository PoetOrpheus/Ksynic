package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
fun SizeVariants(
    sizes: List<com.fortgame.ksynic.mvvm.model.ProductSize> = emptyList(),
    selectedSizeId: String? = null,
    onSizeSelected: (String) -> Unit = {}
){
    if (sizes.isNotEmpty()) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(fh(30))
                .padding(horizontal = fw(10)),
            horizontalArrangement = Arrangement.spacedBy(fw(10))
        ){
            sizes.forEach { size ->
                Size(
                    text = size.value,
                    isSelected = size.id == selectedSizeId,
                    isAvailable = size.isAvailable,
                    onClick = {
                        if (size.isAvailable) {
                            onSizeSelected(size.id)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun Size(
    text: String = "34",
    isSelected: Boolean = false,
    isAvailable: Boolean = true,
    onClick: () -> Unit = {}
){
    Box(
        Modifier
            .fillMaxHeight()
            .width(fw(60))
            .clickable(enabled = isAvailable, onClick = onClick)
            .border(
                width = 3.dp,
                color = when {
                    isSelected -> BlueButton
                    !isAvailable -> Color.Gray.copy(alpha = 0.3f)
                    else -> Color.Gray.copy(alpha = 0.5f)
                },
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                if (!isAvailable) Color.Gray.copy(alpha = 0.2f) else Color.White,
                RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 14.sp,
            color = if (isAvailable) Color.Black else Color.Gray
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