// FavoriteScreen.kt
package com.fortgame.ksynic.U_I.Screen.FavoriteScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.ui.state.UiState
import com.fortgame.ksynic.mvvm.viewmodel.ProductViewModel
import com.fortgame.ksynic.mvvm.viewmodel.ViewModelFactory
import com.fortgame.ksynic.U_I.Screen.FavoriteScreen.components.SettingsFavoriteRow
import com.fortgame.ksynic.U_I.Screen.MainScreen.components.ProductGrid
import com.fortgame.ksynic.U_I.TopHeaderWithoutSearch
import com.fortgame.ksynic.utils.fh

@Composable
fun FavoriteScreen(
    onProductClick: (Product) -> Unit = {},
    viewModel: ProductViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    // Получаем состояние избранных продуктов из ViewModel
    val favoriteProductsState by viewModel.favoriteProductsState.collectAsState()

    // Загружаем избранные продукты при первом запуске
    LaunchedEffect(Unit) {
        // Загружаем только если состояние Idle (первый запуск)
        if (favoriteProductsState is UiState.Idle) {
            viewModel.loadFavoriteProducts()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
    ) {
        TopHeaderWithoutSearch()
        Spacer(modifier = Modifier.height(fh(10)))

        SettingsFavoriteRow()

        Spacer(modifier = Modifier.height(fh(10)))

        // Обрабатываем различные состояния UI
        when (val state = favoriteProductsState) {
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
                if (state.data.isEmpty()) {
                    // Пустое состояние - нет избранных продуктов
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(fh(20)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Нет избранных товаров",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(fh(10)))
                            Text(
                                text = "Добавьте товары в избранное",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                } else {
                    // Отображаем избранные продукты
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        ProductGrid(
                            products = state.data,
                            onProductClick = onProductClick,
                            onToggleFavorite = { productId ->
                                // Удаляем из избранного при клике на иконку лайка
                                viewModel.removeFromFavorites(productId)
                            }
                        )
                    }
                }
            }
            is UiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(fh(20)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Ошибка загрузки: ${state.message ?: "Неизвестная ошибка"}",
                        color = Color.Red
                    )
                }
            }
            is UiState.Idle -> {
                // Начальное состояние - ничего не показываем
            }
        }
    }
}