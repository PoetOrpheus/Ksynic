package com.fortgame.ksynic.mvvm.repository

import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.model.TestProducts
import kotlinx.coroutines.delay

/**
 * Реализация репозитория продуктов
 * 
 * ====================================================================
 * ВНИМАНИЕ: Используются тестовые данные из TestData.kt
 * После интеграции с реальным API замените логику на работу с API/БД
 * ====================================================================
 */
class ProductRepositoryImpl : ProductRepository {
    
    // Имитация хранилища избранных продуктов (для тестирования)
    // TODO: Заменить на реальное хранилище (SharedPreferences, Room, DataStore и т.д.)
    private val favoriteProductIds = mutableSetOf<String>()

    override suspend fun getAllProducts(): List<Product> {
        // Имитация задержки сети
        delay(500)
        
        // ====================================================================
        // ТЕСТОВЫЕ ДАННЫЕ - УДАЛИТЬ ПОСЛЕ ИНТЕГРАЦИИ С API
        // ====================================================================
        return TestProducts.allProducts.map { product ->
            // Обновляем состояние избранного для каждого продукта
            product.copy(isFavorite = favoriteProductIds.contains(product.id))
        }
        // TODO: Заменить на реальный запрос к API
        // return apiService.getProducts()
    }

    override suspend fun getProductById(id: String): Product? {
        // Имитация задержки сети
        delay(300)
        
        // ====================================================================
        // ТЕСТОВЫЕ ДАННЫЕ - УДАЛИТЬ ПОСЛЕ ИНТЕГРАЦИИ С API
        // ====================================================================
        val product = TestProducts.allProducts.find { it.id == id }
        return product?.copy(isFavorite = favoriteProductIds.contains(id))
        // TODO: Заменить на реальный запрос к API
        // return apiService.getProductById(id)
    }

    override suspend fun getProductsByCategory(categoryId: String): List<Product> {
        // Имитация задержки сети
        delay(400)
        
        // ====================================================================
        // ТЕСТОВЫЕ ДАННЫЕ - УДАЛИТЬ ПОСЛЕ ИНТЕГРАЦИИ С API
        // ====================================================================
        // В реальном приложении здесь будет фильтрация по категории
        // Пока возвращаем все продукты
        return TestProducts.allProducts.map { product ->
            product.copy(isFavorite = favoriteProductIds.contains(product.id))
        }
        // TODO: Заменить на реальный запрос к API
        // return apiService.getProductsByCategory(categoryId)
    }

    override suspend fun searchProducts(query: String): List<Product> {
        // Имитация задержки сети
        delay(400)
        
        // ====================================================================
        // ТЕСТОВЫЕ ДАННЫЕ - УДАЛИТЬ ПОСЛЕ ИНТЕГРАЦИИ С API
        // ====================================================================
        val lowerQuery = query.lowercase()
        return TestProducts.allProducts
            .filter { product ->
                product.name.lowercase().contains(lowerQuery) ||
                product.brand?.name?.lowercase()?.contains(lowerQuery) == true ||
                product.description?.lowercase()?.contains(lowerQuery) == true
            }
            .map { product ->
                product.copy(isFavorite = favoriteProductIds.contains(product.id))
            }
        // TODO: Заменить на реальный запрос к API
        // return apiService.searchProducts(query)
    }

    override suspend fun getFavoriteProducts(): List<Product> {
        // Имитация задержки сети
        delay(300)
        
        // ====================================================================
        // ТЕСТОВЫЕ ДАННЫЕ - УДАЛИТЬ ПОСЛЕ ИНТЕГРАЦИИ С API
        // ====================================================================
        return TestProducts.allProducts
            .filter { favoriteProductIds.contains(it.id) }
            .map { it.copy(isFavorite = true) }
        // TODO: Заменить на реальный запрос к API/БД
        // return apiService.getFavoriteProducts()
    }

    override suspend fun addToFavorites(productId: String): Boolean {
        // Имитация задержки сети
        delay(200)
        
        // ====================================================================
        // ТЕСТОВЫЕ ДАННЫЕ - УДАЛИТЬ ПОСЛЕ ИНТЕГРАЦИИ С API
        // ====================================================================
        favoriteProductIds.add(productId)
        return true
        // TODO: Заменить на реальный запрос к API
        // return apiService.addToFavorites(productId)
    }

    override suspend fun removeFromFavorites(productId: String): Boolean {
        // Имитация задержки сети
        delay(200)
        
        // ====================================================================
        // ТЕСТОВЫЕ ДАННЫЕ - УДАЛИТЬ ПОСЛЕ ИНТЕГРАЦИИ С API
        // ====================================================================
        favoriteProductIds.remove(productId)
        return true
        // TODO: Заменить на реальный запрос к API
        // return apiService.removeFromFavorites(productId)
    }
}

