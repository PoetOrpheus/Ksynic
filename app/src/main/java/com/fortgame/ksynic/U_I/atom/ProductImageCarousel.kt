package com.fortgame.ksynic.U_I.atom

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fortgame.ksynic.R // Замените на ваш пакет R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductImageCarousel(
    images: List<Int>,
    modifier: Modifier = Modifier // 1. Добавляем modifier как параметр
) {
    val pagerState = rememberPagerState(pageCount = { images.size })


    Box(
        modifier = modifier
            .background(Color(0xFFE5E5E5))
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Индикатор страниц
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = fw(6), topEnd = fw(6)))
                .shadow(
                    elevation = 5.dp, // Figma: Blur 5
                    shape = RoundedCornerShape(topStart = fw(6), topEnd = fw(6)),
                    spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                )
                .background(Color.White)
                .padding(horizontal = 2.dp)
                .height(fh(8)),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),

            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) {
                        Color(0xFF5D76CB)
                    } else {
                        Color.Gray.copy(alpha = 0.5f)
                    }
                    Box(
                        modifier = Modifier
                            .width(fw(6))
                            .height(fh(6))
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun ProductImageCarouselPreview(){
    ProductImageCarousel(
        images = listOf(
            R.drawable.image_for_product_1,
            R.drawable.image_for_product_2,
            R.drawable.image_for_product_3,
        ),
        modifier = Modifier.fillMaxSize()
    )
}