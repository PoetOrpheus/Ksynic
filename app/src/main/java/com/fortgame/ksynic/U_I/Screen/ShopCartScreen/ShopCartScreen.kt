package com.fortgame.ksynic.U_I.Screen.ShopCartScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.fortgame.ksynic.U_I.Screen.MainScreen.components.ProductCard
import com.fortgame.ksynic.U_I.Screen.ShopCartScreen.components.CardCart
import com.fortgame.ksynic.U_I.Screen.ShopCartScreen.components.TotalCountCart
import com.fortgame.ksynic.U_I.TopHeaderWithoutSearch
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.model.CartItem
import com.fortgame.ksynic.mvvm.ui.state.UiState
import com.fortgame.ksynic.mvvm.viewmodel.CartViewModel
import com.fortgame.ksynic.mvvm.viewmodel.ProductViewModel
import com.fortgame.ksynic.mvvm.viewmodel.ViewModelFactory
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun ShopCartScreen(
    cartViewModel: CartViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current)),
    productViewModel: com.fortgame.ksynic.mvvm.viewmodel.ProductViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current))
) {
    val context = LocalContext.current
    val cartState by cartViewModel.cartState.collectAsState()
    val cartTotal by cartViewModel.cartTotal.collectAsState()
    val productsState by productViewModel.productsState.collectAsState()
    
    // Состояние для бесконечного скролла истории просмотров
    var displayedHistoryCount by remember { mutableIntStateOf(8) } // Начинаем с 8 товаров
    val lazyListState = rememberLazyListState()

    // Загружаем корзину при первом запуске
    LaunchedEffect(Unit) {
        cartViewModel.loadCart()
    }
    
    // Загружаем больше товаров истории при достижении конца списка
    LaunchedEffect(lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index) {
        val lastVisibleIndex = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return@LaunchedEffect
        val allProducts = when (val state = productsState) {
            is UiState.Success -> state.data
            else -> emptyList()
        }
        
        // Если мы близко к концу загруженных товаров, загружаем еще
        if (lastVisibleIndex >= displayedHistoryCount - 3 && displayedHistoryCount < allProducts.size) {
            displayedHistoryCount = minOf(displayedHistoryCount + 8, allProducts.size)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
    ) {
        TopHeaderWithoutSearch()
        
        when (val state = cartState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                val cartItems = state.data
                val allProducts = when (val productsStateValue = productsState) {
                    is UiState.Success -> productsStateValue.data
                    else -> emptyList()
                }
                val displayedProducts = allProducts.take(displayedHistoryCount)
                
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Секция корзины
                    item {
                        Spacer(modifier = Modifier.height(fh(15)))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp)
                                .background(Color.White, RoundedCornerShape(10.dp))
                                .clip(RoundedCornerShape(10.dp)),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (cartItems.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(fh(400)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Корзина пуста",
                                        fontSize = 18.sp,
                                        color = Color.Gray
                                    )
                                }
                            } else {
                                // Товары корзины в обычном Column (не LazyColumn, чтобы избежать вложенных LazyColumn)
                                cartItems.forEachIndexed { index, cartItem ->
                                    if (index > 0) {
                                        Box(
                                            Modifier
                                                .height(fh(2))
                                                .fillMaxWidth()
                                                .background(Color(0xFFF2F2F2))
                                        )
                                    }
                                    CardCart(
                                        cartItem = cartItem,
                                        onQuantityChange = { newQuantity ->
                                            cartViewModel.updateCartItemQuantity(cartItem.id, newQuantity)
                                        },
                                        onRemove = {
                                            cartViewModel.removeFromCart(cartItem.id)
                                        },
                                        onToggleSelection = {
                                            cartViewModel.toggleCartItemSelection(cartItem.id)
                                        },
                                        onToggleFavorite = {
                                            Log.d("ShopCartScreen", "onToggleFavorite: клик по избранному для товара ${cartItem.product.id} (${cartItem.product.name})")
                                            CoroutineScope(Dispatchers.Main).launch {
                                                val success = productViewModel.toggleFavoriteSync(cartItem.product.id)
                                                Log.d("ShopCartScreen", "onToggleFavorite: операция завершена, success = $success")
                                                cartViewModel.refreshCartState()
                                            }
                                        }
                                    )
                                }
                                
                                // Кнопка оформить заказ и итоги
                                Spacer(Modifier.height(fh(20)))
                                Button(
                                    modifier = Modifier
                                        .height(fh(40))
                                        .width(fw(340)),
                                    colors = ButtonColors(
                                        containerColor = Color(0xFF50C878),
                                        contentColor = Color.White,
                                        disabledContentColor = Color.Blue,
                                        disabledContainerColor = Color.Yellow
                                    ),
                                    onClick = {
                                        Toast.makeText(
                                            context,
                                            "Заказ оформлен",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    },
                                ) {
                                    Text(
                                        text = "Оформить заказ",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        lineHeight = 17.sp
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .width(fw(340))
                                        .height(fh(40)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Далее можно выбрать место доставки и способ оплаты",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                        lineHeight = 12.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                val selectedItems = cartItems.filter { it.isSelected }
                                val totalOldPrice = selectedItems.sumOf { (it.product.oldPrice ?: it.product.price) * it.quantity }
                                val discount = totalOldPrice - selectedItems.sumOf { it.product.price * it.quantity }
                                
                                TotalCountCart(
                                    count = selectedItems.sumOf { it.quantity },
                                    countPrice = totalOldPrice,
                                    salePrice = discount,
                                    finalPrice = cartTotal
                                )
                                Spacer(Modifier.height(fh(20)))
                            }
                        }
                    }
                    
                    // Секция истории просмотров
                    if (allProducts.isNotEmpty()) {
                        item {
                            Spacer(modifier = Modifier.height(fh(20)))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = fw(25)),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "История просмотров",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    lineHeight = 26.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(fh(10)))
                        }
                        
                        // Товары истории просмотров с бесконечным скроллом
                        items(
                            items = displayedProducts.chunked(2),
                            key = { row -> "${row.first().id}_${row.lastOrNull()?.id ?: ""}" } // Улучшенный key для стабильности
                        ) { rowProducts ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = fw(10))
                                    .padding(bottom = fh(10)),
                                horizontalArrangement = Arrangement.spacedBy(fw(10))
                            ) {
                                rowProducts.forEach { product ->
                                    ProductCard(
                                        product = product,
                                        onClick = { },
                                        onFavoriteClick = { productViewModel.toggleFavorite(product.id) },
                                        modifier = Modifier.weight(1f),
                                        enableAppearanceAnimation = false // Отключаем анимацию появления для оптимизации
                                    )
                                }
                                // Если в ряду только один элемент, добавляем пустое место
                                if (rowProducts.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                        
                        // Индикатор загрузки, если еще есть товары для загрузки
                        if (displayedHistoryCount < allProducts.size) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(fh(20)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(modifier = Modifier.size(32.dp))
                                }
                            }
                        }
                    }
                    
                    // Отступ внизу
                    item {
                        Spacer(modifier = Modifier.height(fh(20)))
                    }
                }
            }
            is UiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Ошибка загрузки корзины",
                        fontSize = 18.sp,
                        color = Color.Red
                    )
                }
            }
            is UiState.Idle -> {
                // Начальное состояние
            }
        }
    }
}

@Composable
@Preview
private fun ShopCartScreenPreview(){
    ShopCartScreen()
}