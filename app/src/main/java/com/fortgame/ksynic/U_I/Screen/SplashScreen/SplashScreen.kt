package com.fortgame.ksynic.U_I.Screen.SplashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fortgame.ksynic.mvvm.ui.state.UiState
import com.fortgame.ksynic.mvvm.viewmodel.ProductViewModel
import com.fortgame.ksynic.mvvm.viewmodel.ViewModelFactory
import com.fortgame.ksynic.theme.lowWhite

@Composable
fun SplashScreen(
    onLoadingComplete: () -> Unit,
    viewModel: ProductViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    val productsState by viewModel.productsState.collectAsState()

    // Загружаем продукты при первом запуске
    LaunchedEffect(Unit) {
        if (productsState is UiState.Idle) {
            viewModel.loadProducts()
        }
    }

    // Когда загрузка завершена (успешно или с ошибкой), переходим на главный экран
    LaunchedEffect(productsState) {
        when (productsState) {
            is UiState.Success -> {
                onLoadingComplete()
            }
            is UiState.Error -> {
                // Даже при ошибке переходим на главный экран
                onLoadingComplete()
            }
            else -> {
                // Loading или Idle - ждем
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lowWhite),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

