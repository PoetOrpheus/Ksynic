package com.fortgame.ksynic.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fortgame.ksynic.mvvm.model.CartItem
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.repository.ProductRepository
import com.fortgame.ksynic.mvvm.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel для работы с корзиной
 */
class CartViewModel(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    // Состояние корзины
    private val _cartState = MutableStateFlow<UiState<List<CartItem>>>(UiState.Idle)
    val cartState: StateFlow<UiState<List<CartItem>>> = _cartState.asStateFlow()

    // Общая стоимость корзины
    private val _cartTotal = MutableStateFlow<Int>(0)
    val cartTotal: StateFlow<Int> = _cartTotal.asStateFlow()

    // Флаг для отслеживания, были ли данные загружены
    private var hasLoadedCart = false

    /**
     * Загрузить корзину
     */
    fun loadCart() {
        Log.d("CartViewModel", "loadCart: вызван, hasLoadedCart=$hasLoadedCart, currentState=${_cartState.value::class.simpleName}")
        
        // Убираем проверку, чтобы корзина всегда обновлялась
        viewModelScope.launch {
            Log.d("CartViewModel", "loadCart: начинаем загрузку корзины")
            _cartState.value = UiState.Loading
            try {
                val cartItems = productRepository.getCartItems()
                Log.d("CartViewModel", "loadCart: получено ${cartItems.size} товаров из репозитория")
                _cartState.value = UiState.Success(cartItems)
                updateCartTotal(cartItems)
                hasLoadedCart = true
            } catch (e: Exception) {
                Log.e("CartViewModel", "loadCart: ошибка загрузки корзины", e)
                _cartState.value = UiState.Error(message = e.message, throwable = e)
            }
        }
    }

    /**
     * Добавить товар в корзину
     */
    fun addToCart(
        product: Product,
        selectedVariantId: String? = null,
        selectedSizeId: String? = null,
        quantity: Int = 1
    ) {
        Log.d("CartViewModel", "addToCart: вызываем добавление товара ${product.name}")
        viewModelScope.launch {
            try {
                val success = productRepository.addToCart(
                    product = product,
                    selectedVariantId = selectedVariantId,
                    selectedSizeId = selectedSizeId,
                    quantity = quantity
                )
                Log.d("CartViewModel", "addToCart: результат добавления: $success")
                if (success) {
                    // Обновляем корзину
                    Log.d("CartViewModel", "addToCart: обновляем корзину после добавления")
                    loadCart()
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "addToCart: ошибка при добавлении товара", e)
            }
        }
    }

    /**
     * Обновить количество товара в корзине
     */
    fun updateCartItemQuantity(cartItemId: String, quantity: Int) {
        viewModelScope.launch {
            try {
                val success = productRepository.updateCartItemQuantity(cartItemId, quantity)
                if (success) {
                    // Обновляем корзину
                    loadCart()
                }
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }

    /**
     * Переключить выбранное состояние товара в корзине
     */
    fun toggleCartItemSelection(cartItemId: String) {
        Log.d("CartViewModel", "toggleCartItemSelection: переключаем выбор для товара $cartItemId")
        viewModelScope.launch {
            try {
                val success = productRepository.toggleCartItemSelection(cartItemId)
                if (success) {
                    // Обновляем корзину
                    loadCart()
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "toggleCartItemSelection: ошибка при переключении выбора", e)
            }
        }
    }

    /**
     * Удалить товар из корзины
     */
    fun removeFromCart(cartItemId: String) {
        viewModelScope.launch {
            try {
                val success = productRepository.removeFromCart(cartItemId)
                if (success) {
                    // Обновляем корзину
                    loadCart()
                }
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }

    /**
     * Очистить корзину
     */
    fun clearCart() {
        viewModelScope.launch {
            try {
                val success = productRepository.clearCart()
                if (success) {
                    _cartState.value = UiState.Success(emptyList())
                    _cartTotal.value = 0
                    hasLoadedCart = false
                }
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }

    /**
     * Обновить общую стоимость корзины
     */
    private fun updateCartTotal(cartItems: List<CartItem>) {
        viewModelScope.launch {
            try {
                val total = productRepository.getCartTotal()
                _cartTotal.value = total
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }

    /**
     * Получить количество товаров в корзине
     */
    fun getCartItemsCount(): Int {
        return when (val state = _cartState.value) {
            is UiState.Success -> state.data.sumOf { it.quantity }
            else -> 0
        }
    }
}

