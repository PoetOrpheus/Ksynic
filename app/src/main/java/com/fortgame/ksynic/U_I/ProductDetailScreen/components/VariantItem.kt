package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
fun VariantItemRow(
    variants: List<com.fortgame.ksynic.mvvm.model.ProductVariant> = emptyList(),
    selectedVariantId: String? = null,
    onVariantSelected: (String) -> Unit = {}
) {
    if (variants.isNotEmpty()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(fw(10))
        ) {
            items(variants) { variant ->
                VariantItem(
                    variant = variant,
                    isSelected = variant.id == selectedVariantId,
                    onClick = { onVariantSelected(variant.id) }
                )
            }
        }
    }
}

@Composable
fun VariantItem(
    variant: com.fortgame.ksynic.mvvm.model.ProductVariant,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        Modifier
            .height(fh(113))
            .width(fw(70))
            .clickable(enabled = variant.isAvailable, onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .height(fh(93))
                .fillMaxWidth()
                .border(
                    width = 3.dp,
                    color = if (isSelected) BlueButton else if (variant.isAvailable) Color.LightGray else Color.Gray.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(6.dp))
                .background(
                    if (!variant.isAvailable) Color.Gray.copy(alpha = 0.3f) else Color.Transparent
                )
        ) {
            // Отображаем первое изображение варианта для миниатюры
            val firstImageRes = variant.getFirstImageRes()
            if (firstImageRes != null) {
                Image(
                    painter = painterResource(id = firstImageRes),
                    contentDescription = variant.value,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Если изображений нет, показываем текстовое значение
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = variant.value,
                        fontSize = 10.sp,
                        color = if (variant.isAvailable) Color.Black else Color.Gray
                    )
                }
            }
        }

        // Текст под изображением
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = variant.value,
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 10.sp,
                color = if (variant.isAvailable) Color.Black else Color.Gray
            )
        }


    }
}

@Composable
@Preview
private fun VariantItemPreview(){
    VariantItemRow()
}