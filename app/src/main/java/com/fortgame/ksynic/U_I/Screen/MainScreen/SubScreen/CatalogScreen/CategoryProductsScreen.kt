package com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.CatalogScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.Screen.MainScreen.components.ProductGrid
import com.fortgame.ksynic.U_I.TopHeaderWithReturn
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.ui.state.UiState
import com.fortgame.ksynic.mvvm.viewmodel.ProductViewModel
import com.fortgame.ksynic.mvvm.viewmodel.ViewModelFactory
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun CategoryProductsScreen(
    categoryName: String = "",
    subcategoryName: String = "",
    onBackClick: () -> Unit = {},
    onProductClick: (Product) -> Unit = {},
    viewModel: ProductViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    val productsState by viewModel.productsState.collectAsState()

    // Загружаем продукты только один раз при первом запуске
    LaunchedEffect(Unit) {
        if (productsState is UiState.Idle) {
            viewModel.loadProducts()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgGray)
    ) {
        TopHeaderWithReturn(onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = fh(60))
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(fh(10)))
            
            // Заголовок с категорией и подкатегорией
            if (categoryName.isNotEmpty() || subcategoryName.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = fw(20), vertical = fh(10))
                ) {
                    val titleText = when {
                        categoryName.isNotEmpty() && subcategoryName.isNotEmpty() -> "$categoryName - $subcategoryName"
                        categoryName.isNotEmpty() -> categoryName
                        subcategoryName.isNotEmpty() -> subcategoryName
                        else -> ""
                    }
                    if (titleText.isNotEmpty()) {
                        Text(
                            text = titleText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
                Spacer(Modifier.height(fh(5)))
            }

            when (val state = productsState) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(fh(20)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Success -> {
                    // Пока что показываем все товары, так как в модели Product нет поля категории
                    // В будущем здесь можно добавить фильтрацию по категории
                    ProductGrid(
                        products = state.data,
                        onProductClick = onProductClick,
                        onToggleFavorite = { productId ->
                            viewModel.toggleFavorite(productId)
                        }
                    )
                }
                is UiState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(fh(20)),
                        contentAlignment = Alignment.Center
                    ) {
                        // Здесь можно добавить сообщение об ошибке
                    }
                }
                is UiState.Idle -> {
                    // Начальное состояние
                }
            }

            Spacer(Modifier.height(fh(20)))
        }
    }
}


@Preview
@Composable
private fun CategoryProductsScreenPreview(){
    CategoryProductsScreen()
}

