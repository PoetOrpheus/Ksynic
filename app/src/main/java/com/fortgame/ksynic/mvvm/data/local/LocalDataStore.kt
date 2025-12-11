package com.fortgame.ksynic.mvvm.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.data.local.ProductDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// Расширение для DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

/**
 * Класс для работы с локальным хранилищем данных
 */
class LocalDataStore(private val context: Context) {
    
    private val gson = Gson()
    
    // Ключи для хранения данных
    private val favoriteProductIdsKey = stringSetPreferencesKey("favorite_product_ids")
    private val cachedProductsKey = stringPreferencesKey("cached_products_json")
    private val productsCacheTimestampKey = stringPreferencesKey("products_cache_timestamp")
    
    /**
     * Получить список ID избранных продуктов
     */
    suspend fun getFavoriteProductIds(): Set<String> {
        return context.dataStore.data
            .map { preferences ->
                preferences[favoriteProductIdsKey] ?: emptySet()
            }
            .first()
    }
    
    /**
     * Сохранить список ID избранных продуктов
     */
    suspend fun saveFavoriteProductIds(productIds: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[favoriteProductIdsKey] = productIds
        }
    }
    
    /**
     * Добавить продукт в избранное
     */
    suspend fun addToFavorites(productId: String) {
        val currentFavorites = getFavoriteProductIds()
        saveFavoriteProductIds(currentFavorites + productId)
    }
    
    /**
     * Удалить продукт из избранного
     */
    suspend fun removeFromFavorites(productId: String) {
        val currentFavorites = getFavoriteProductIds()
        saveFavoriteProductIds(currentFavorites - productId)
    }
    
    /**
     * Проверить, находится ли продукт в избранном
     */
    suspend fun isFavorite(productId: String): Boolean {
        return getFavoriteProductIds().contains(productId)
    }
    
    /**
     * Получить Flow для отслеживания изменений избранных продуктов
     */
    fun getFavoriteProductIdsFlow(): Flow<Set<String>> {
        return context.dataStore.data.map { preferences ->
            preferences[favoriteProductIdsKey] ?: emptySet()
        }
    }
    
    /**
     * Сохранить кэш продуктов
     */
    suspend fun cacheProducts(products: List<Product>) {
        // Конвертируем Product в ProductDTO для сериализации
        val productsDTO = products.map { ProductDTO.fromProduct(it) }
        val productsJson = gson.toJson(productsDTO)
        val timestamp = System.currentTimeMillis()
        
        context.dataStore.edit { preferences ->
            // Сохраняем продукты как JSON строку
            preferences[cachedProductsKey] = productsJson
            preferences[productsCacheTimestampKey] = timestamp.toString()
        }
    }
    
    /**
     * Получить кэш продуктов
     */
    suspend fun getCachedProducts(): List<Product>? {
        return try {
            val preferences = context.dataStore.data.first()
            val productsJson = preferences[cachedProductsKey]
            
            if (productsJson != null && productsJson.isNotEmpty()) {
                val type = object : TypeToken<List<ProductDTO>>() {}.type
                val productsDTO = gson.fromJson<List<ProductDTO>>(productsJson, type)
                // Конвертируем ProductDTO обратно в Product
                productsDTO.map { it.toProduct() }
            } else {
                null
            }
        } catch (e: Exception) {
            // В случае ошибки десериализации возвращаем null
            e.printStackTrace()
            null
        }
    }
    
    /**
     * Получить время последнего обновления кэша
     */
    suspend fun getCacheTimestamp(): Long? {
        return try {
            val preferences = context.dataStore.data.first()
            val timestampString = preferences[productsCacheTimestampKey]
            timestampString?.toLongOrNull()
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Очистить кэш продуктов
     */
    suspend fun clearProductsCache() {
        context.dataStore.edit { preferences ->
            preferences.remove(cachedProductsKey)
            preferences.remove(productsCacheTimestampKey)
        }
    }
    
    /**
     * Проверить, нужно ли обновлять кэш (например, если прошло более 1 часа)
     */
    suspend fun shouldRefreshCache(maxCacheAgeMs: Long = 3600000L): Boolean {
        val timestamp = getCacheTimestamp()
        return timestamp == null || (System.currentTimeMillis() - timestamp) > maxCacheAgeMs
    }
}


