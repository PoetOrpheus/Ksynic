package com.fortgame.ksynic.mvvm.model

import androidx.compose.ui.graphics.Color

/**
 * Модель продукта
 */
data class Product(
    val id: String,
    val name: String,
    val price: Int, // Цена в копейках (для точности) или в рублях
    val oldPrice: Int? = null, // Старая цена (опционально)
    val discount: Int? = null, // Скидка в процентах (опционально)
    val rating: Double = 0.0, // Рейтинг от 0.0 до 5.0
    val reviewsCount: Int = 0, // Количество отзывов
    val images: List<String> = emptyList(), // Список URL изображений продукта (для реальных данных)
    val imagesRes: List<Int> = emptyList(), // Список drawable ресурсов изображений продукта для карусели (для тестовых данных)
    val isTimeLimited: Boolean = false, // Ограниченное по времени предложение
    val accentColor: Color = Color(0xFF000000), // Акцентный цвет продукта
    val isFavorite: Boolean = false, // В избранном
    val seller: Seller? = null, // Информация о продавце
    val brand: Brand? = null, // Информация о бренде
    val description: String? = null, // Описание продукта
    val variants: List<ProductVariant> = emptyList(), // Варианты продукта (размер, цвет и т.д.)
    val specifications: List<ProductSpecification> = emptyList(), // Характеристики продукта
    val quantity: Int = 1 // Количество в корзине (для корзины)
) {
    /**
     * Вычисляет процент скидки на основе старой и новой цены
     */
    fun calculateDiscountPercent(): Int? {
        return oldPrice?.let { old ->
            if (old > price && old > 0) {
                ((old - price).toDouble() / old * 100).toInt()
            } else null
        } ?: discount
    }

    /**
     * Проверяет, есть ли скидка на товар
     */
    fun hasDiscount(): Boolean {
        return oldPrice != null && oldPrice > price
    }
}

/**
 * Модель продавца
 */
data class Seller(
    val id: String,
    val name: String,
    val avatarUrl: String? = null, // URL аватара продавца
    val rating: Double = 0.0, // Рейтинг продавца
    val ordersCount: Int = 0, // Количество заказов
    val reviewsCount: Int = 0 // Количество отзывов
)

/**
 * Модель бренда
 */
data class Brand(
    val id: String,
    val name: String,
    val logoUrl: String? = null // URL логотипа бренда
)

/**
 * Модель характеристики продукта (пара ключ-значение)
 */
data class ProductSpecification(
    val name: String, // Название характеристики (например, "Материал", "Размер")
    val value: String // Значение характеристики (например, "Кожа", "42x30x10 см")
)

/**
 * Модель варианта продукта (размер, цвет и т.д.)
 */
data class ProductVariant(
    val id: String,
    val name: String, // Название варианта (например, "Размер: L" или "Цвет: Красный")
    val value: String, // Значение варианта
    val isAvailable: Boolean = true, // Доступен ли вариант
    val imagesRes: List<Int> = emptyList(), // Список drawable ресурсов изображений варианта для карусели (для тестовых данных)
    val imagesUrl: List<String> = emptyList() // Список URL изображений варианта для карусели (для реальных данных)
) {
    /**
     * Получить первое изображение варианта (для миниатюры в списке вариантов)
     */
    fun getFirstImageRes(): Int? = imagesRes.firstOrNull()
    
    /**
     * Получить первый URL изображения варианта (для миниатюры в списке вариантов)
     */
    fun getFirstImageUrl(): String? = imagesUrl.firstOrNull()
    
    /**
     * Проверяет, есть ли изображения у варианта
     */
    fun hasImages(): Boolean = imagesRes.isNotEmpty() || imagesUrl.isNotEmpty()
}

