package com.fortgame.ksynic.mvvm.viewmodel

import androidx.lifecycle.viewModelScope
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.repository.ProductRepository
import com.fortgame.ksynic.mvvm.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel для работы с продуктами
 */
class ProductViewModel(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    // Состояние списка продуктов
    private val _productsState = MutableStateFlow<UiState<List<Product>>>(UiState.Idle)
    val productsState: StateFlow<UiState<List<Product>>> = _productsState.asStateFlow()

    // Состояние текущего продукта
    private val _productState = MutableStateFlow<UiState<Product>>(UiState.Idle)
    val productState: StateFlow<UiState<Product>> = _productState.asStateFlow()

    // Состояние избранных продуктов
    private val _favoriteProductsState = MutableStateFlow<UiState<List<Product>>>(UiState.Idle)
    val favoriteProductsState: StateFlow<UiState<List<Product>>> = _favoriteProductsState.asStateFlow()

    // Флаг для отслеживания, были ли данные загружены
    private var hasLoadedProducts = false
    private var isLoadingProducts = false

    /**
     * Загрузить все продукты (только один раз)
     */
    fun loadProducts(forceReload: Boolean = false) {
        // Если данные уже загружены или идет загрузка, и не требуется принудительная перезагрузка - выходим
        if ((hasLoadedProducts || isLoadingProducts) && !forceReload) {
            return
        }

        viewModelScope.launch {
            isLoadingProducts = true
            _productsState.value = UiState.Loading
            try {
                val products = productRepository.getAllProducts()
                _productsState.value = UiState.Success(products)
                hasLoadedProducts = true
            } catch (e: Exception) {
                _productsState.value = UiState.Error(message = e.message, throwable = e)
            } finally {
                isLoadingProducts = false
            }
        }
    }

    /**
     * Загрузить продукт по ID
     */
    fun loadProductById(id: String) {
        viewModelScope.launch {
            _productState.value = UiState.Loading
            try {
                val product = productRepository.getProductById(id)
                if (product != null) {
                    _productState.value = UiState.Success(product)
                } else {
                    _productState.value = UiState.Error(message = "Продукт не найден")
                }
            } catch (e: Exception) {
                _productState.value = UiState.Error(message = e.message, throwable = e)
            }
        }
    }

    /**
     * Поиск продуктов
     */
    fun searchProducts(query: String) {
        viewModelScope.launch {
            _productsState.value = UiState.Loading
            try {
                val products = productRepository.searchProducts(query)
                _productsState.value = UiState.Success(products)
            } catch (e: Exception) {
                _productsState.value = UiState.Error(message = e.message, throwable = e)
            }
        }
    }

    /**
     * Загрузить избранные продукты
     */
    fun loadFavoriteProducts() {
        viewModelScope.launch {
            _favoriteProductsState.value = UiState.Loading
            try {
                val products = productRepository.getFavoriteProducts()
                _favoriteProductsState.value = UiState.Success(products)
            } catch (e: Exception) {
                _favoriteProductsState.value = UiState.Error(message = e.message, throwable = e)
            }
        }
    }

    /**
     * Добавить продукт в избранное
     */
    fun addToFavorites(productId: String) {
        viewModelScope.launch {
            try {
                val success = productRepository.addToFavorites(productId)
                if (success) {
                    // Обновляем состояние избранных продуктов
                    loadFavoriteProducts()
                }
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }

    /**
     * Удалить продукт из избранного
     */
    fun removeFromFavorites(productId: String) {
        viewModelScope.launch {
            try {
                val success = productRepository.removeFromFavorites(productId)
                if (success) {
                    // Обновляем состояние избранных продуктов
                    loadFavoriteProducts()
                }
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }
}

