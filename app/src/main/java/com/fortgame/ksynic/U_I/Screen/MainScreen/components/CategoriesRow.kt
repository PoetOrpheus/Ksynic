package com.fortgame.ksynic.U_I.Screen.MainScreen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import kotlinx.coroutines.delay
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
        CategoryItem("Стать\nпродавцом", Color(0xFF5D76CB),Color(0xFFD23E41), painterResource(R.drawable.seller_icon), 30,30, onCanBeSeller, appearanceDelay = 0L)
        CategoryItem("Магазины\nи бренды", Color(0xFF5D76CB),Color(0xFFCC5086), painterResource(R.drawable.shops_and_brans),30,30, onBrandsClick, appearanceDelay = 50L)
        CategoryItem("Финансы", Color(0xFF5D76CB), Color(0xFFDAA636),painterResource(R.drawable.finances_icon_home),30,30, appearanceDelay = 100L)
        CategoryItem("История\nпросмотров", Color(0xFF5D76CB), Color(0xFF21936F),painterResource(R.drawable.history_icon),40,40, onHistoryClick, appearanceDelay = 150L)
        CategoryItem("Каталог", Color(0xFF5D76CB), Color(0xFF1D36D7),painterResource(R.drawable.category_icon),30,44, onCategoryClick, appearanceDelay = 200L)
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
    onClick:()-> Unit = {},
    appearanceDelay: Long = 0L
) {
    // Анимация появления
    var isVisible by remember { mutableFloatStateOf(0f) }
    var alpha by remember { mutableFloatStateOf(0f) }
    
    LaunchedEffect(Unit) {
        delay(appearanceDelay)
        alpha = 1f
        isVisible = 1f
    }
    
    val appearanceScale by animateFloatAsState(
        targetValue = isVisible,
        animationSpec = spring(
            dampingRatio = 0.6f,
            stiffness = 400f
        ),
        label = "category_appearance_scale"
    )
    
    val appearanceAlpha by animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(durationMillis = 300),
        label = "category_appearance_alpha"
    )
    
    // Анимация нажатия с bounce эффектом
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressScale by animateFloatAsState(
        targetValue = if (isPressed) 0.85f else 1f,
        animationSpec = spring(
            dampingRatio = 0.5f,
            stiffness = 500f
        ),
        label = "category_press_scale"
    )
    
    // Комбинированный scale
    val combinedScale = appearanceScale * pressScale
    
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
                .scale(combinedScale)
                .alpha(appearanceAlpha)
                .clip(RoundedCornerShape(18.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(color1, color2)
                    )
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                ),
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