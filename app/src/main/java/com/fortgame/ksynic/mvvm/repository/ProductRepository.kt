package com.fortgame.ksynic.mvvm.repository

import com.fortgame.ksynic.mvvm.model.CartItem
import com.fortgame.ksynic.mvvm.model.Product

/**
 * Репозиторий для работы с продуктами
 * Здесь будет логика получения данных (из API, локальной БД и т.д.)
 */
interface ProductRepository {
    /**
     * Получить все продукты
     */
    suspend fun getAllProducts(): List<Product>

    /**
     * Получить продукт по ID
     */
    suspend fun getProductById(id: String): Product?

    /**
     * Получить продукты по категории
     */
    suspend fun getProductsByCategory(categoryId: String): List<Product>

    /**
     * Поиск продуктов
     */
    suspend fun searchProducts(query: String): List<Product>

    /**
     * Получить избранные продукты
     */
    suspend fun getFavoriteProducts(): List<Product>

    /**
     * Добавить продукт в избранное
     */
    suspend fun addToFavorites(productId: String): Boolean

    /**
     * Удалить продукт из избранного
     */
    suspend fun removeFromFavorites(productId: String): Boolean

    /**
     * Получить все товары в корзине
     */
    suspend fun getCartItems(): List<CartItem>

    /**
     * Добавить товар в корзину
     */
    suspend fun addToCart(
        product: Product,
        selectedVariantId: String? = null,
        selectedSizeId: String? = null,
        quantity: Int = 1
    ): Boolean

    /**
     * Обновить количество товара в корзине
     */
    suspend fun updateCartItemQuantity(cartItemId: String, quantity: Int): Boolean

    /**
     * Переключить выбранное состояние товара в корзине
     */
    suspend fun toggleCartItemSelection(cartItemId: String): Boolean

    /**
     * Удалить товар из корзины
     */
    suspend fun removeFromCart(cartItemId: String): Boolean

    /**
     * Очистить корзину
     */
    suspend fun clearCart(): Boolean

    /**
     * Получить общую стоимость корзины
     */
    suspend fun getCartTotal(): Int
}

