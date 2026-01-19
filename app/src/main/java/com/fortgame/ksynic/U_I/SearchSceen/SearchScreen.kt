package com.fortgame.ksynic.U_I.SearchSceen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.Screen.MainScreen.components.ProductGrid
import com.fortgame.ksynic.U_I.TopHeaderWithSearchAndReturn
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.ui.state.UiState
import com.fortgame.ksynic.mvvm.viewmodel.ProductViewModel
import com.fortgame.ksynic.mvvm.viewmodel.ViewModelFactory
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw
import android.util.Log
import androidx.compose.ui.res.stringResource
import com.fortgame.ksynic.R
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    onProductClick: (Product) -> Unit = {},
    viewModel: ProductViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    Log.d("SearchScreen", "SearchScreen: recompose, searchQuery = '$searchQuery', length = ${searchQuery.length}")
    val searchResultsState by viewModel.searchResultsState.collectAsState()
    
    // Автопоиск при изменении запроса (с задержкой)
    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotBlank()) {
            delay(300) // Задержка для debounce
            viewModel.searchProducts(searchQuery)
        } else {
            viewModel.clearSearchResults()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BgGray)) {
        Column {
            TopHeaderWithSearchAndReturn(
                searchQuery = searchQuery,
                onSearchQueryChange = { newQuery ->
                    Log.d("SearchScreen", "onSearchQueryChange вызван: newQuery = '$newQuery', length = ${newQuery.length}")
                    onSearchQueryChange(newQuery)
                    Log.d("SearchScreen", "onSearchQueryChange: callback вызван")
                },
                onBackClick = onBackClick
            )

            // Результаты поиска
            when (val state = searchResultsState) {
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
                    if (searchQuery.isBlank()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(fh(20)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text= stringResource(R.string.search_term),
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                    } else if (state.data.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(fh(20)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                stringResource(R.string.nothing_found),
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = fw(5))
                        ) {
                            item {
                                ProductGrid(
                                    products = state.data,
                                    onProductClick = onProductClick,
                                    onToggleFavorite = { productId ->
                                        viewModel.toggleFavorite(productId)
                                    }
                                )
                            }
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
                            text = "Ошибка поиска: ${state.message ?: "Неизвестная ошибка"}",
                            color = Color.Red
                        )
                    }
                }
                is UiState.Idle -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(fh(20)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            stringResource(R.string.search),
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}
