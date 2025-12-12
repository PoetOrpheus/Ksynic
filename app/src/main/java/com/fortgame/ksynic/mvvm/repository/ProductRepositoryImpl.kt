package com.fortgame.ksynic.mvvm.repository

import android.content.Context
import android.util.Log
import com.fortgame.ksynic.mvvm.data.local.LocalDataStore
import com.fortgame.ksynic.mvvm.model.CartItem
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.model.TestProducts
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

/**
 * Реализация репозитория продуктов
 * 
 * ====================================================================
 * ВНИМАНИЕ: Используются тестовые данные из TestData.kt
 * После интеграции с реальным API замените логику на работу с API/БД
 * ====================================================================
 */
class ProductRepositoryImpl(
    private val context: Context? = null
) : ProductRepository {
    
    private val localDataStore = context?.let { LocalDataStore(it) }
    private val favoriteProductIds = mutableSetOf<String>()
    private var isFavoritesInitialized = false
    
    // Хранение корзины в памяти (можно позже перенести в DataStore)
    // Используем companion object для статического хранения, чтобы корзина сохранялась между вызовами
    companion object {
        private val cartItems = mutableMapOf<String, CartItem>()
    }

    override suspend fun getAllProducts(): List<Product> {
        // Сначала инициализируем избранные из локального хранилища (важно сделать это до работы с кэшем)
        initializeFavoritesFromLocalStorage()
        
        // Затем пытаемся загрузить из кэша
        val cachedProducts = localDataStore?.getCachedProducts()
        if (cachedProducts != null && !localDataStore.shouldRefreshCache()) {
            // Восстанавливаем imageRes из оригинальных тестовых данных, если они отсутствуют
            val restoredProducts = restoreVariantsImageRes(cachedProducts)
            // Используем кэшированные данные, обновляя их с учетом избранных
            // (избранные уже инициализированы выше)
            // НЕ добавляем задержку при загрузке из кэша для мгновенного отображения
            return updateProductsWithFavorites(restoredProducts)
        }
        
        // Имитация задержки сети (загрузка с сервера) - только если кэш отсутствует или устарел
        delay(500)
        
        // ====================================================================
        // ТЕСТОВЫЕ ДАННЫЕ - УДАЛИТЬ ПОСЛЕ ИНТЕГРАЦИИ С API
        // ====================================================================
        val products = TestProducts.allProducts
        
        val productsWithFavorites = updateProductsWithFavorites(products)
        
        // Сохраняем в кэш (избранные уже инициализированы выше)
        localDataStore?.cacheProducts(productsWithFavorites)
        
        return productsWithFavorites
        // TODO: Заменить на реальный запрос к API
        // return apiService.getProducts()
    }
    
    /**
     * Инициализировать избранные из локального хранилища
     */
    private suspend fun initializeFavoritesFromLocalStorage() {
        if (!isFavoritesInitialized && localDataStore != null) {
            val savedFavorites = localDataStore.getFavoriteProductIds()
            favoriteProductIds.clear()
            favoriteProductIds.addAll(savedFavorites)
            
            // Если сохраненных избранных нет, инициализируем из тестовых данных
            if (favoriteProductIds.isEmpty()) {
                TestProducts.allProducts.forEach { product ->
                    if (product.isFavorite) {
                        favoriteProductIds.add(product.id)
                    }
                }
                // Сохраняем начальные избранные
                localDataStore.saveFavoriteProductIds(favoriteProductIds)
            }
            
            isFavoritesInitialized = true
        } else if (!isFavoritesInitialized) {
            // Если нет LocalDataStore, используем только тестовые данные
            TestProducts.allProducts.forEach { product ->
                if (product.isFavorite) {
                    favoriteProductIds.add(product.id)
                }
            }
            isFavoritesInitialized = true
        }
    }
    
    /**
     * Обновить продукты с учетом избранных
     */
    private fun updateProductsWithFavorites(products: List<Product>): List<Product> {
        return products.map { product ->
            product.copy(isFavorite = favoriteProductIds.contains(product.id))
        }
    }
    
    /**
     * Восстанавливает imageRes для вариантов продукта из оригинальных тестовых данных
     * Это необходимо, так как при кэшировании drawable ресурсы не сохраняются
     */
    private fun restoreVariantsImageRes(products: List<Product>): List<Product> {
        val originalProducts = TestProducts.allProducts.associateBy { it.id }
        
        return products.map { product ->
            val originalProduct = originalProducts[product.id]
            if (originalProduct != null) {
                // Создаем Map оригинальных вариантов по id для быстрого поиска
                val originalVariantsMap = originalProduct.variants.associateBy { it.id }
                
                // Восстанавливаем imagesRes и imagesUrl для каждого варианта
                val restoredVariants = product.variants.map { variant ->
                    val originalVariant = originalVariantsMap[variant.id]
                    if (originalVariant != null) {
                        // Всегда восстанавливаем imagesRes и imagesUrl из оригинала (они могут потеряться при кэшировании)
                        variant.copy(
                            imagesRes = originalVariant.imagesRes.ifEmpty { variant.imagesRes },
                            imagesUrl = originalVariant.imagesUrl.ifEmpty { variant.imagesUrl }
                        )
                    } else {
                        variant
                    }
                }
                
                // Также восстанавливаем imagesRes продукта
                val restoredImagesRes = if (product.imagesRes.isEmpty()) {
                    originalProduct.imagesRes.ifEmpty { product.imagesRes }
                } else {
                    product.imagesRes
                }
                
                product.copy(
                    variants = restoredVariants,
                    imagesRes = restoredImagesRes
                )
            } else {
                product
            }
        }
    }
    

    override suspend fun getProductById(id: String): Product? {
        // Инициализируем избранные, если еще не инициализированы
        initializeFavoritesFromLocalStorage()
        
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
        // Инициализируем избранные, если еще не инициализированы
        initializeFavoritesFromLocalStorage()
        
        // Имитация задержки сети
        delay(400)
        
        // ====================================================================
        // ТЕСТОВЫЕ ДАННЫЕ - УДАЛИТЬ ПОСЛЕ ИНТЕГРАЦИИ С API
        // ====================================================================
        // В реальном приложении здесь будет фильтрация по категории
        // Пока возвращаем все продукты
        val products = TestProducts.allProducts
        return updateProductsWithFavorites(products)
        // TODO: Заменить на реальный запрос к API
        // return apiService.getProductsByCategory(categoryId)
    }

    override suspend fun searchProducts(query: String): List<Product> {
        // Инициализируем избранные, если еще не инициализированы
        initializeFavoritesFromLocalStorage()
        
        // Имитация задержки сети
        delay(400)
        
        // ====================================================================
        // ТЕСТОВЫЕ ДАННЫЕ - УДАЛИТЬ ПОСЛЕ ИНТЕГРАЦИИ С API
        // ====================================================================
        val lowerQuery = query.lowercase()
        val filteredProducts = TestProducts.allProducts.filter { product ->
            product.name.lowercase().contains(lowerQuery) ||
            product.brand?.name?.lowercase()?.contains(lowerQuery) == true ||
            product.description?.lowercase()?.contains(lowerQuery) == true
        }
        return updateProductsWithFavorites(filteredProducts)
        // TODO: Заменить на реальный запрос к API
        // return apiService.searchProducts(query)
    }

    override suspend fun getFavoriteProducts(): List<Product> {
        // Инициализируем избранные, если еще не инициализированы
        initializeFavoritesFromLocalStorage()
        
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
        
        // Сохраняем в локальное хранилище
        localDataStore?.saveFavoriteProductIds(favoriteProductIds)
        
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
        
        // Сохраняем в локальное хранилище
        localDataStore?.saveFavoriteProductIds(favoriteProductIds)
        
        return true
        // TODO: Заменить на реальный запрос к API
        // return apiService.removeFromFavorites(productId)
    }

    override suspend fun getCartItems(): List<CartItem> {
        delay(100) // Имитация задержки
        val items = cartItems.values.toList()
        Log.d("CartRepository", "getCartItems: получено ${items.size} товаров")
        items.forEach { item ->
            Log.d("CartRepository", "  - ${item.product.name} (ID: ${item.id}), количество: ${item.quantity}")
        }
        return items
    }

    override suspend fun addToCart(
        product: Product,
        selectedVariantId: String?,
        selectedSizeId: String?,
        quantity: Int
    ): Boolean {
        delay(200) // Имитация задержки
        
        // Создаем уникальный ID для элемента корзины
        val cartItemId = generateCartItemId(product.id, selectedVariantId, selectedSizeId)
        Log.d("CartRepository", "addToCart: добавляем товар ${product.name} (productId: ${product.id}, variantId: $selectedVariantId, sizeId: $selectedSizeId)")
        Log.d("CartRepository", "addToCart: сгенерированный cartItemId: $cartItemId")
        Log.d("CartRepository", "addToCart: текущий размер корзины до добавления: ${cartItems.size}")
        
        // Проверяем, есть ли уже такой товар в корзине
        val existingItem = cartItems[cartItemId]
        if (existingItem != null) {
            // Если товар уже есть, увеличиваем количество
            cartItems[cartItemId] = existingItem.copy(quantity = existingItem.quantity + quantity)
            Log.d("CartRepository", "addToCart: товар уже был в корзине, обновлено количество: ${existingItem.quantity} -> ${existingItem.quantity + quantity}")
        } else {
            // Добавляем новый товар в корзину (по умолчанию выбран)
            cartItems[cartItemId] = CartItem(
                id = cartItemId,
                product = product,
                selectedVariantId = selectedVariantId,
                selectedSizeId = selectedSizeId,
                quantity = quantity,
                isSelected = true
            )
            Log.d("CartRepository", "addToCart: добавлен новый товар в корзину, новое количество товаров: ${cartItems.size}")
        }
        
        // TODO: Сохранить в DataStore для персистентности
        // localDataStore?.saveCartItems(cartItems.values.toList())
        
        Log.d("CartRepository", "addToCart: итоговый размер корзины: ${cartItems.size}")
        return true
    }

    override suspend fun updateCartItemQuantity(cartItemId: String, quantity: Int): Boolean {
        delay(100) // Имитация задержки
        
        val item = cartItems[cartItemId]
        if (item != null) {
            if (quantity <= 0) {
                // Удаляем товар, если количество <= 0
                cartItems.remove(cartItemId)
            } else {
                cartItems[cartItemId] = item.copy(quantity = quantity)
            }
            
            // TODO: Сохранить в DataStore
            // localDataStore?.saveCartItems(cartItems.values.toList())
            return true
        }
        return false
    }

    override suspend fun toggleCartItemSelection(cartItemId: String): Boolean {
        delay(50) // Имитация задержки
        
        val item = cartItems[cartItemId]
        if (item != null) {
            cartItems[cartItemId] = item.copy(isSelected = !item.isSelected)
            Log.d("CartRepository", "toggleCartItemSelection: товар ${item.product.name} теперь ${if (!item.isSelected) "выбран" else "не выбран"}")
            
            // TODO: Сохранить в DataStore
            // localDataStore?.saveCartItems(cartItems.values.toList())
            return true
        }
        return false
    }

    override suspend fun removeFromCart(cartItemId: String): Boolean {
        delay(100) // Имитация задержки
        
        val removed = cartItems.remove(cartItemId) != null
        
        // TODO: Сохранить в DataStore
        // if (removed) {
        //     localDataStore?.saveCartItems(cartItems.values.toList())
        // }
        
        return removed
    }

    override suspend fun clearCart(): Boolean {
        delay(100) // Имитация задержки
        
        cartItems.clear()
        
        // TODO: Сохранить в DataStore
        // localDataStore?.saveCartItems(emptyList())
        
        return true
    }

    override suspend fun getCartTotal(): Int {
        delay(50) // Имитация задержки
        
        // Считаем только выбранные товары
        return cartItems.values
            .filter { it.isSelected }
            .sumOf { it.getTotalPrice() }
    }

    /**
     * Генерирует уникальный ID для элемента корзины
     */
    private fun generateCartItemId(productId: String, variantId: String?, sizeId: String?): String {
        return "${productId}_${variantId ?: "no_variant"}_${sizeId ?: "no_size"}"
    }
}

