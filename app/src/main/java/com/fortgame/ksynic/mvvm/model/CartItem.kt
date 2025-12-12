package com.fortgame.ksynic.mvvm.model

/**
 * Модель товара в корзине
 * Содержит информацию о продукте, выбранном варианте, размере и количестве
 */
data class CartItem(
    val id: String, // Уникальный ID элемента корзины (может быть комбинацией productId + variantId + sizeId)
    val product: Product,
    val selectedVariantId: String? = null, // ID выбранного варианта (цвет и т.д.)
    val selectedSizeId: String? = null, // ID выбранного размера
    val quantity: Int = 1, // Количество товара
    val isSelected: Boolean = true // Выбран ли товар для покупки (по умолчанию выбран)
) {
    /**
     * Вычисляет итоговую цену за все количество товара
     */
    fun getTotalPrice(): Int {
        return product.price * quantity
    }

    /**
     * Получить название выбранного варианта
     */
    fun getSelectedVariantName(): String? {
        return selectedVariantId?.let { variantId ->
            product.variants.find { it.id == variantId }?.name
        }
    }

    /**
     * Получить название выбранного размера
     */
    fun getSelectedSizeName(): String? {
        return selectedSizeId?.let { sizeId ->
            product.sizes.find { it.id == sizeId }?.value
        }
    }
}

