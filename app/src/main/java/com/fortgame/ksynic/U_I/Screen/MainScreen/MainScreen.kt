package com.fortgame.ksynic.U_I.Screen.MainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fortgame.ksynic.Navigation.BottomNavItem
import com.fortgame.ksynic.Navigation.BottomNavigationBar
import com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.BrandsScreen.BrandsScreen
import com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.CanBeSeller.CanBeSeller
import com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.CatalogScreen.CatalogScreen
import com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen.EditingProfileScreen
import com.fortgame.ksynic.U_I.Screen.ProfileScreen.ProfileScreen
import com.fortgame.ksynic.U_I.Screen.FavoriteScreen.FavoriteScreen
import com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.HistoryScreen.HistoryScreen
import com.fortgame.ksynic.U_I.Screen.MainScreen.components.CategoriesRow
import com.fortgame.ksynic.U_I.Screen.MainScreen.components.ProductGrid
import com.fortgame.ksynic.U_I.ProductDetailScreen.ProductDetailScreen
import com.fortgame.ksynic.U_I.Screen.ShopCartScreen.ShopCartScreen
import com.fortgame.ksynic.U_I.TopHeaderSection
import com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.WatingReviewsScreen.WatingReviewScreen
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.ui.state.UiState
import com.fortgame.ksynic.mvvm.viewmodel.ProductViewModel
import com.fortgame.ksynic.mvvm.viewmodel.ViewModelFactory
import com.fortgame.ksynic.utils.fh

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    var showProductDetail by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    var showHistory by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    var showCanBeSeller by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    var showCategory by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    var showBrands by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    var showWaitingReview by remember{mutableStateOf(false)}
    var showEditingProfile by remember{mutableStateOf(false)}



    Scaffold(
        bottomBar = {
            if (!showProductDetail and !showHistory and !showCanBeSeller and !showCategory and !showBrands and !showWaitingReview and !showEditingProfile) { // Скрываем навигацию на экране деталей
                BottomNavigationBar(
                    selectedItem = selectedTab,
                    onItemSelected = { selectedTab = it }
                )
            }
        },
        containerColor = Color(0xFFF2F2F2)
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (showProductDetail) {
                // Показываем экран деталей товара
                ProductDetailScreen(
                    onBackClick = { showProductDetail = false } // Функция для возврата
                )
            }
            else if (showHistory){
                HistoryScreen(
                    onBackClick = { showHistory = false } ,// Функция для возврата
                    onProductClick = { showProductDetail = true } // Передаем функцию
                    )
            }
            else if (showCanBeSeller){
                CanBeSeller(
                    onBackClick = { showCanBeSeller = false } ,// Функция для возврата
                )
            }
            else if(showCategory){
                CatalogScreen(
                    onBackClick = {showCategory = false}
                )
            }
            else if(showBrands){
                BrandsScreen(
                    onBackClick = {showBrands = false}
                )
            }

            else if (showWaitingReview){
                WatingReviewScreen(
                    onBackClick = {showWaitingReview = false}
                )
            }

            else if (showEditingProfile){
                EditingProfileScreen(
                    onBackClick = {showEditingProfile=false}
                )
            }

            else {
                when (selectedTab) {
                    BottomNavItem.Home -> MarketplaceContent(
                        onProductClick = { product -> 
                            // TODO: Сохранить выбранный продукт для экрана деталей
                            showProductDetail = true 
                        },
                        onHistoryClick = { showHistory = true },
                        onCanBeSeller = { showCanBeSeller = true },
                        onCategoryClick = { showCategory = true },
                        onBrandsClick = { showBrands = true }
                    )
                    BottomNavItem.Favorites -> FavoriteScreen(
                        onProductClick = { product ->
                            // TODO: Сохранить выбранный продукт для экрана деталей
                            showProductDetail = true
                        }
                    )
                    BottomNavItem.Profile -> ProfileScreen(
                        onReviewClick = {showWaitingReview=true},
                        onEditingClick = {showEditingProfile=true}

                    )
                    BottomNavItem.ShopCart -> ShopCartScreen()

                    else -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(
                                text = "Заглушка для: ${selectedTab.name}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MarketplaceContent(
    onProductClick: (Product) -> Unit = {}, // Теперь принимаем Product
    onHistoryClick: () -> Unit = {},
    onCanBeSeller: () -> Unit = {},
    onCategoryClick: () -> Unit = {},
    onBrandsClick: () -> Unit = {},
    viewModel: ProductViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    // Получаем состояние продуктов из ViewModel
    val productsState by viewModel.productsState.collectAsState()

    // Загружаем продукты только один раз при первом запуске
    // ViewModel сама проверяет, были ли данные уже загружены
    LaunchedEffect(Unit) {
        // Загружаем только если состояние Idle (первый запуск)
        if (productsState is UiState.Idle) {
            viewModel.loadProducts()
        }
    }

    // Обрабатываем различные состояния UI
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
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                TopHeaderSection()
                CategoriesRow(
                    onHistoryClick = onHistoryClick,
                    onCanBeSeller = onCanBeSeller,
                    onCategoryClick = onCategoryClick,
                    onBrandsClick = onBrandsClick
                )
                Spacer(modifier = Modifier.height(fh(10)))
                ProductGrid(
                    products = state.data,
                    onProductClick = onProductClick,
                    onToggleFavorite = { productId ->
                        viewModel.toggleFavorite(productId)
                    }
                )
            }
        }
        is UiState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                TopHeaderSection()
                CategoriesRow(
                    onHistoryClick = onHistoryClick,
                    onCanBeSeller = onCanBeSeller,
                    onCategoryClick = onCategoryClick,
                    onBrandsClick = onBrandsClick
                )
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
        }
        is UiState.Idle -> {
            // Начальное состояние - ничего не показываем
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainScreen()
}