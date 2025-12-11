package com.fortgame.ksynic.U_I.ProductDetailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.ProductDetailScreen.components.BrandBlock
import com.fortgame.ksynic.U_I.ProductDetailScreen.components.InfoCardsSection
import com.fortgame.ksynic.U_I.ProductDetailScreen.components.ProductMainCard
import com.fortgame.ksynic.U_I.ProductDetailScreen.components.SellerBlock
import com.fortgame.ksynic.U_I.ProductDetailScreen.components.StartCardRow
import com.fortgame.ksynic.U_I.ProductDetailScreen.components.VariantItem
import com.fortgame.ksynic.U_I.ProductDetailScreen.components.VariantItemRow
import com.fortgame.ksynic.U_I.TopHeaderWithReturn
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

// Заглушки для цветов, чтобы соответствовать стилю
val PurpleHeader = Color(0xFF9C89F6) // Примерный цвет хедера
val MainRed = Color(0xFFD32F2F)
val TextGray = Color(0xFF999999)
val BgGray = Color(0xFFF2F2F2)
val BlueButton = Color(0xFF5D76CB)

@Composable
fun ProductDetailScreen(
    product: com.fortgame.ksynic.mvvm.model.Product,
    onBackClick: () -> Unit = {}
) {
    // Состояние выбранного варианта (выбираем первый доступный по умолчанию)
    var selectedVariantId by remember {
        mutableStateOf(
            product.variants.firstOrNull { it.isAvailable }?.id
        )
    }

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

            // 2. Карточка товара (Фото + Цена)
            item {
                Spacer(Modifier.height(fh(10)))
                ProductMainCard(
                    product = product,
                    selectedVariantId = selectedVariantId
                )
            }

            // Варианты
            item{
                Spacer(Modifier.height(fh(10)))
                VariantItemRow(
                    variants = product.variants,
                    selectedVariantId = selectedVariantId,
                    onVariantSelected = { variantId ->
                        selectedVariantId = variantId
                    }
                )
            }

            // 3. Варианты, Рейтинг, Табы, Описание
            item {
                Spacer(Modifier.height(fh(10)))
                StartCardRow(
                    rating = product.rating,
                    reviewsCount = product.reviewsCount
                )
            }


            // 4. Описание и характеристики
            item {
                Spacer(Modifier.height(fh(10)))
                InfoCardsSection(description = product.description)
            }

            // Продавец
            item{
                Spacer(Modifier.height(fh(10)))
                if (product.seller != null) {
                    SellerBlock(seller = product.seller)
                }
            }

            // Бренд
            item{
                Spacer(Modifier.height(fh(10)))
                if (product.brand != null) {
                    BrandBlock(brand = product.brand)
                }
                Spacer(Modifier.height(fh(20)))
            }
/*
            // 5. История просмотров
            item {
                HistorySection()
            }*/
        }
    }
}

@Preview
@Composable
fun ProductScreenPreview() {
    ProductDetailScreen(
        product = com.fortgame.ksynic.mvvm.model.TestProducts.calvinKleinWatch,
        onBackClick = {}
    )
}