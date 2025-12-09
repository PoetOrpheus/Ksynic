package com.fortgame.ksynic.U_I.MainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.fortgame.ksynic.Navigation.BottomNavItem
import com.fortgame.ksynic.Navigation.BottomNavigationBar
import com.fortgame.ksynic.U_I.BrandsScreen.BrandsScreen
import com.fortgame.ksynic.U_I.CanBeSeller.CanBeSeller
import com.fortgame.ksynic.U_I.CatalogScreen.CatalogScreen
import com.fortgame.ksynic.U_I.ProfileScreen.ProfileScreen
import com.fortgame.ksynic.U_I.FavoriteScreen.FavoriteScreen
import com.fortgame.ksynic.U_I.HistoryScreen.HistoryScreen
import com.fortgame.ksynic.U_I.MainScreen.components.CategoriesRow
import com.fortgame.ksynic.U_I.MainScreen.components.ProductGrid
import com.fortgame.ksynic.U_I.ProductDetailScreen.ProductDetailScreen // ДОБАВЬТЕ этот импорт
import com.fortgame.ksynic.U_I.ShopCartScreen.ShopCartScreen
import com.fortgame.ksynic.U_I.TopHeaderSection
import com.fortgame.ksynic.utils.fh

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    var showProductDetail by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    var showHistory by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    var showCanBeSeller by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    var showCategory by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    var showBrands by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние



    Scaffold(
        bottomBar = {
            if (!showProductDetail and !showHistory and !showCanBeSeller and !showCategory and !showBrands) { // Скрываем навигацию на экране деталей
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

            else {
                when (selectedTab) {
                    BottomNavItem.Home -> MarketplaceContent(
                        onProductClick = { showProductDetail = true }, // Передаем функцию
                        onHistoryClick = { showHistory = true }, // Передаем функцию
                        onCanBeSeller = {showCanBeSeller=true},
                        onCategoryClick = {showCategory=true},
                        onBrandsClick = {showBrands=true}
                        )
                    BottomNavItem.Favorites -> FavoriteScreen()
                    BottomNavItem.Profile -> ProfileScreen()
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
    onProductClick: () -> Unit = {}, // ДОБАВЬТЕ этот параметр
    onHistoryClick: () -> Unit = {}, // ДОБАВЬТЕ этот параметр
    onCanBeSeller: () -> Unit = {}, // ДОБАВЬТЕ этот параметр
    onCategoryClick:() -> Unit = {},
    onBrandsClick:() -> Unit = {}
    ) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        TopHeaderSection()
        CategoriesRow(onHistoryClick = onHistoryClick, onCanBeSeller=onCanBeSeller, onCategoryClick =onCategoryClick, onBrandsClick = onBrandsClick )
        Spacer(modifier = Modifier.height(fh(10)))
        ProductGrid(onProductClick = onProductClick) // Передаем функцию дальше
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainScreen()
}