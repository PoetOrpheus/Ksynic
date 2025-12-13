package com.fortgame.ksynic.mvvm.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.fortgame.ksynic.mvvm.model.CartItem
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.model.UserProfile
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
    private val userProfileKey = stringPreferencesKey("user_profile_json")
    private val cartItemsKey = stringPreferencesKey("cart_items_json")
    private val isLoggedInKey = booleanPreferencesKey("is_logged_in")
    
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

    /**
     * Сохранить профиль пользователя
     */
    suspend fun saveUserProfile(profile: UserProfile) {
        val profileJson = gson.toJson(profile)
        context.dataStore.edit { preferences ->
            preferences[userProfileKey] = profileJson
        }
    }

    /**
     * Получить профиль пользователя
     */
    suspend fun getUserProfile(): UserProfile? {
        return try {
            val preferences = context.dataStore.data.first()
            val profileJson = preferences[userProfileKey]
            
            if (profileJson != null && profileJson.isNotEmpty()) {
                gson.fromJson(profileJson, UserProfile::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Получить профиль пользователя или профиль по умолчанию
     */
    suspend fun getUserProfileOrDefault(): UserProfile {
        return getUserProfile() ?: UserProfile.default()
    }

    /**
     * Сохранить корзину
     */
    suspend fun saveCartItems(cartItems: List<CartItem>) {
        try {
            // Конвертируем CartItem в упрощенную структуру для сохранения
            // Сохраняем только ID продуктов и метаданные, так как Product может быть большой
            val cartItemsData = cartItems.map { item ->
                CartItemData(
                    id = item.id,
                    productId = item.product.id,
                    selectedVariantId = item.selectedVariantId,
                    selectedSizeId = item.selectedSizeId,
                    quantity = item.quantity,
                    isSelected = item.isSelected
                )
            }
            val cartItemsJson = gson.toJson(cartItemsData)
            
            context.dataStore.edit { preferences ->
                preferences[cartItemsKey] = cartItemsJson
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Получить сохраненные данные корзины
     */
    suspend fun getCartItemsData(): List<CartItemData> {
        return try {
            val preferences = context.dataStore.data.first()
            val cartItemsJson = preferences[cartItemsKey]
            
            if (cartItemsJson != null && cartItemsJson.isNotEmpty()) {
                val type = object : TypeToken<List<CartItemData>>() {}.type
                gson.fromJson<List<CartItemData>>(cartItemsJson, type)
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    /**
     * Очистить корзину
     */
    suspend fun clearCartItems() {
        context.dataStore.edit { preferences ->
            preferences.remove(cartItemsKey)
        }
    }

    /**
     * DTO для сохранения данных корзины
     */
    data class CartItemData(
        val id: String,
        val productId: String,
        val selectedVariantId: String? = null,
        val selectedSizeId: String? = null,
        val quantity: Int = 1,
        val isSelected: Boolean = true
    )

    /**
     * Проверить, авторизован ли пользователь
     */
    suspend fun isLoggedIn(): Boolean {
        return try {
            val preferences = context.dataStore.data.first()
            preferences[isLoggedInKey] ?: false
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Получить Flow для отслеживания состояния авторизации
     */
    fun isLoggedInFlow(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[isLoggedInKey] ?: false
        }
    }

    /**
     * Сохранить состояние авторизации
     */
    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[isLoggedInKey] = isLoggedIn
        }
    }

    /**
     * Выйти из аккаунта
     */
    suspend fun logout() {
        context.dataStore.edit { preferences ->
            preferences[isLoggedInKey] = false
            // Можно также очистить профиль, если нужно
            // preferences.remove(userProfileKey)
        }
    }
}


