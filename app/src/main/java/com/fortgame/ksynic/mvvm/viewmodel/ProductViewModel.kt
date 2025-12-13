package com.fortgame.ksynic.mvvm.viewmodel

import android.util.Log
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

    // Состояние результатов поиска
    private val _searchResultsState = MutableStateFlow<UiState<List<Product>>>(UiState.Idle)
    val searchResultsState: StateFlow<UiState<List<Product>>> = _searchResultsState.asStateFlow()

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
            
            // При принудительной перезагрузке всегда показываем Loading
            // При первой загрузке тоже показываем (но кэш загрузится быстро)
            if (forceReload || !hasLoadedProducts) {
                _productsState.value = UiState.Loading
            }
            
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
     * Удалить продукт из избранного
     */
    fun removeFromFavorites(productId: String) {
        viewModelScope.launch {
            try {
                val success = productRepository.removeFromFavorites(productId)
                if (success) {
                    // Оптимизированное обновление - обновляем только состояние избранных без полной перезагрузки
                    refreshFavoriteProductsState(productId, false)
                    // Обновляем список продуктов, чтобы отразить изменение избранного
                    updateProductFavoriteState(productId, isFavorite = false)
                }
            } catch (e: Exception) {
                // Обработка ошибки
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
                    // Оптимизированное обновление
                    refreshFavoriteProductsState(productId, true)
                    // Обновляем список продуктов, чтобы отразить изменение избранного
                    updateProductFavoriteState(productId, isFavorite = true)
                }
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }

    /**
     * Оптимизированное обновление состояния избранных продуктов без полной перезагрузки
     */
    private suspend fun refreshFavoriteProductsState(productId: String, shouldAdd: Boolean) {
        val currentState = _favoriteProductsState.value
        if (currentState is UiState.Success) {
            val updatedProducts = if (shouldAdd) {
                // Добавляем товар в избранное (нужно получить продукт из репозитория)
                val product = productRepository.getProductById(productId)
                if (product != null && !currentState.data.any { it.id == productId }) {
                    currentState.data + product.copy(isFavorite = true)
                } else {
                    // Обновляем существующий товар
                    currentState.data.map { 
                        if (it.id == productId) it.copy(isFavorite = true) else it 
                    }
                }
            } else {
                // Удаляем товар из избранного
                currentState.data.filter { it.id != productId }
            }
            _favoriteProductsState.value = UiState.Success(updatedProducts)
        }
    }

    /**
     * Переключить состояние избранного для продукта (suspend функция для ожидания завершения)
     */
    suspend fun toggleFavoriteSync(productId: String): Boolean {
        Log.d("ProductViewModel", "toggleFavoriteSync: переключаем избранное для товара $productId")
        return try {
            // Получаем текущее состояние избранного из репозитория
            val product = productRepository.getProductById(productId)
            val isCurrentlyFavorite = product?.isFavorite ?: false
            Log.d("ProductViewModel", "toggleFavoriteSync: текущее состояние избранного для $productId = $isCurrentlyFavorite")
            
            val success = if (isCurrentlyFavorite) {
                Log.d("ProductViewModel", "toggleFavoriteSync: удаляем из избранного")
                productRepository.removeFromFavorites(productId)
            } else {
                Log.d("ProductViewModel", "toggleFavoriteSync: добавляем в избранное")
                productRepository.addToFavorites(productId)
            }
            
            Log.d("ProductViewModel", "toggleFavoriteSync: операция завершена, success = $success")
            
            // Обновляем состояние в основном списке, если он загружен
            updateProductFavoriteState(productId, !isCurrentlyFavorite)
            
            // Обновляем состояние избранных продуктов
            refreshFavoriteProductsState(productId, !isCurrentlyFavorite)
            
            Log.d("ProductViewModel", "toggleFavoriteSync: обновлено состояние для $productId, новое значение = ${!isCurrentlyFavorite}")
            success
        } catch (e: Exception) {
            Log.e("ProductViewModel", "toggleFavoriteSync: ошибка при переключении избранного", e)
            false
        }
    }

    /**
     * Переключить состояние избранного для продукта (async версия для обратной совместимости)
     */
    fun toggleFavorite(productId: String) {
        viewModelScope.launch {
            toggleFavoriteSync(productId)
        }
    }

    /**
     * Поиск продуктов по запросу
     */
    fun searchProducts(query: String) {
        if (query.isBlank()) {
            _searchResultsState.value = UiState.Success(emptyList())
            return
        }

        viewModelScope.launch {
            _searchResultsState.value = UiState.Loading
            try {
                val results = productRepository.searchProducts(query)
                _searchResultsState.value = UiState.Success(results)
            } catch (e: Exception) {
                _searchResultsState.value = UiState.Error(message = e.message, throwable = e)
            }
        }
    }

    /**
     * Очистить результаты поиска
     */
    fun clearSearchResults() {
        _searchResultsState.value = UiState.Idle
    }

    /**
     * Обновить состояние избранного для продукта в списке
     */
    private fun updateProductFavoriteState(productId: String, isFavorite: Boolean) {
        val currentState = _productsState.value
        if (currentState is UiState.Success) {
            val updatedProducts = currentState.data.map { product ->
                if (product.id == productId) {
                    product.copy(isFavorite = isFavorite)
                } else {
                    product
                }
            }
            _productsState.value = UiState.Success(updatedProducts)
            // Кэш обновится при следующей загрузке, или можно обновить его здесь через репозиторий
        }
    }
}

