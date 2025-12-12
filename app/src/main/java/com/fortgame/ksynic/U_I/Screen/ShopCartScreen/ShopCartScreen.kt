package com.fortgame.ksynic.U_I.Screen.ShopCartScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.fortgame.ksynic.U_I.Screen.ShopCartScreen.components.CardCart
import com.fortgame.ksynic.U_I.Screen.ShopCartScreen.components.TotalCountCart
import com.fortgame.ksynic.U_I.TopHeaderWithoutSearch
import com.fortgame.ksynic.mvvm.model.CartItem
import com.fortgame.ksynic.mvvm.ui.state.UiState
import com.fortgame.ksynic.mvvm.viewmodel.CartViewModel
import com.fortgame.ksynic.mvvm.viewmodel.ViewModelFactory
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun ShopCartScreen(
    cartViewModel: CartViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current))
) {
    val context = LocalContext.current
    val cartState by cartViewModel.cartState.collectAsState()
    val cartTotal by cartViewModel.cartTotal.collectAsState()

    // Загружаем корзину при первом запуске
    LaunchedEffect(Unit) {
        cartViewModel.loadCart()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
    ) {
        TopHeaderWithoutSearch()
        Spacer(modifier = Modifier.height(fh(15)))
        
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
                        LazyColumn(
                            modifier=
                                if (cartItems.size > 2)
                                    Modifier.height(fh(465))
                                else
                                    Modifier
                        ) {
                            items(cartItems.size) { index ->
                                val cartItem = cartItems[index]
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
                                    }
                                )
                                if (index < cartItems.size - 1) {
                                    Box(
                                        Modifier.height(fh(2)).fillMaxWidth().background(Color(0xFFF2F2F2))
                                    )
                                }
                            }
                        }
                    }



                    // Офорить заказ + итоговый результат
                    if (cartItems.isNotEmpty()) {
                        Spacer(Modifier.height(fh(20)))
                        Button(
                            modifier = Modifier
                                .height(fh(40))
                                .width(fw(340)),
                            colors = ButtonColors(containerColor = Color(0xFF50C878),
                                contentColor = Color.White,
                                disabledContentColor = Color.Blue,
                                disabledContainerColor = Color.Yellow),
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "Заказ оформлен",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                        ) {
                            Text(
                                text="Оформить заказ",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 17.sp
                            )
                        }

                        Box(
                            modifier=Modifier
                                .width(fw(340))
                                .height(fh(40)),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text="Далее можно выбрать место доставки и способ оплаты",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                        
                        // Вычисляем итоги только для выбранных товаров
                        val selectedItems = cartItems.filter { it.isSelected }
                        val totalPrice = selectedItems.sumOf { it.product.price * it.quantity }
                        val totalOldPrice = selectedItems.sumOf { (it.product.oldPrice ?: it.product.price) * it.quantity }
                        val discount = totalOldPrice - totalPrice
                        
                        TotalCountCart(
                            count = selectedItems.sumOf { it.quantity },
                            countPrice = totalOldPrice,
                            salePrice = discount,
                            finalPrice = cartTotal
                        )
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