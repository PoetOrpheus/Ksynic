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
import com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.CatalogScreen.CatalogSubScreen
import com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.CatalogScreen.CategoryProductsScreen
import com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen.EditingProfileScreen
import com.fortgame.ksynic.U_I.Screen.ProfileScreen.ProfileScreen
import com.fortgame.ksynic.U_I.Screen.FavoriteScreen.FavoriteScreen
import com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.HistoryScreen.HistoryScreen
import com.fortgame.ksynic.U_I.Screen.MainScreen.components.CategoriesRow
import com.fortgame.ksynic.U_I.Screen.MainScreen.components.ProductGrid
import com.fortgame.ksynic.U_I.ProductDetailScreen.ProductDetailScreen
import com.fortgame.ksynic.U_I.SearchSceen.SearchScreen
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
    var showProductDetail by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) } // Сохраняем выбранный продукт
    var showHistory by remember { mutableStateOf(false) }
    var showCanBeSeller by remember { mutableStateOf(false) }
    var showCategory by remember { mutableStateOf(false) }
    var showCatalogSubScreen by remember { mutableStateOf(false) }
    var showCategoryProducts by remember { mutableStateOf(false) }
    var selectedCategoryName by remember { mutableStateOf<String?>(null) }
    var selectedSubcategoryName by remember { mutableStateOf<String?>(null) }
    var showBrands by remember { mutableStateOf(false) }
    var showWaitingReview by remember{mutableStateOf(false)}
    var showEditingProfile by remember{mutableStateOf(false)}
    var showSearch by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    
    // Логирование изменений searchQuery
    androidx.compose.runtime.LaunchedEffect(searchQuery) {
        android.util.Log.d("MainScreen", "searchQuery изменен: '$searchQuery', length = ${searchQuery.length}")
    }



    Scaffold(
        bottomBar = {
            if (!showProductDetail and !showHistory and !showCanBeSeller and !showCategory and !showCatalogSubScreen and !showCategoryProducts and !showBrands and !showWaitingReview and !showEditingProfile and !showSearch) { // Скрываем навигацию на экране деталей
                BottomNavigationBar(
                    selectedItem = selectedTab,
                    onItemSelected = { selectedTab = it }
                )
            }
        },
        containerColor = Color(0xFFF2F2F2)
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (showProductDetail && selectedProduct != null) {
                // Показываем экран деталей товара с выбранным продуктом
                ProductDetailScreen(
                    product = selectedProduct!!,
                    onBackClick = { 
                        showProductDetail = false
                        selectedProduct = null
                    }
                )
            }
            else if (showHistory){
                HistoryScreen(
                    onBackClick = { showHistory = false },
                    onProductClick = { product ->
                        selectedProduct = product
                        showHistory = false
                        showProductDetail = true
                    }
                )
            }
            else if (showCanBeSeller){
                CanBeSeller(
                    onBackClick = { showCanBeSeller = false } ,// Функция для возврата
                )
            }
            else if(showCategory){
                CatalogScreen(
                    onBackClick = {showCategory = false},
                    onCategoryClick = { categoryName ->
                        selectedCategoryName = categoryName
                        showCategory = false
                        showCatalogSubScreen = true 
                    }
                )
            }
            else if(showCatalogSubScreen){
                CatalogSubScreen(
                    onBackClick = { 
                        showCatalogSubScreen = false
                        selectedCategoryName = null
                    },
                    onSubcategoryClick = { subcategoryName ->
                        selectedSubcategoryName = subcategoryName
                        showCatalogSubScreen = false
                        showCategoryProducts = true
                    }
                )
            }
            else if(showCategoryProducts){
                CategoryProductsScreen(
                    categoryName = selectedCategoryName ?: "",
                    subcategoryName = selectedSubcategoryName ?: "",
                    onBackClick = { 
                        showCategoryProducts = false
                        selectedSubcategoryName = null
                        // Возвращаемся к CatalogSubScreen
                        if (selectedCategoryName != null) {
                            showCatalogSubScreen = true
                        }
                    },
                    onProductClick = { product ->
                        selectedProduct = product
                        showProductDetail = true
                    }
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
            else if (showSearch) {
                android.util.Log.d("MainScreen", "Отображаем SearchScreen, searchQuery = '$searchQuery'")
                SearchScreen(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { newQuery ->
                        android.util.Log.d("MainScreen", "onSearchQueryChange в MainScreen: новое значение = '$newQuery', старое = '$searchQuery'")
                        searchQuery = newQuery
                        android.util.Log.d("MainScreen", "searchQuery обновлен в MainScreen: '$searchQuery'")
                    },
                    onBackClick = { 
                        showSearch = false
                        searchQuery = ""
                    },
                    onProductClick = { product ->
                        selectedProduct = product
                        showSearch = false
                        showProductDetail = true
                    }
                )
            }
            else {
                when (selectedTab) {
                    BottomNavItem.Home -> MarketplaceContent(
                        onProductClick = { product -> 
                            selectedProduct = product
                            showProductDetail = true 
                        },
                        onHistoryClick = { showHistory = true },
                        onCanBeSeller = { showCanBeSeller = true },
                        onCategoryClick = { showCategory = true },
                        onBrandsClick = { showBrands = true },
                        onSearchClick = { showSearch = true }
                    )

                    BottomNavItem.Favorites -> FavoriteScreen(
                        onProductClick = { product ->
                            selectedProduct = product
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
    ),
    onSearchClick: () -> Unit
) {
    // Получаем состояние продуктов из ViewModel
    val productsState by viewModel.productsState.collectAsState()

    // Продукты уже загружены на SplashScreen, поэтому здесь ничего не загружаем
    // Если состояние Loading, это означает что данные еще загружаются (не должно происходить в норме)

    // Обрабатываем различные состояния UI
    when (val state = productsState) {
        is UiState.Loading -> {
            // Это состояние не должно появляться, так как загрузка происходит на SplashScreen
            // Но на всякий случай показываем Loading
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
                TopHeaderSection(onSearchClick = onSearchClick)
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
                TopHeaderSection(onSearchClick = onSearchClick)
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
            // Начальное состояние - не должно происходить, так как данные загружаются на SplashScreen
            // Но на всякий случай показываем Loading
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(fh(20)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainScreen()
}