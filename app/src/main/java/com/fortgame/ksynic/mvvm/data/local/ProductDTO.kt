package com.fortgame.ksynic.mvvm.data.local

import androidx.compose.ui.graphics.Color
import com.fortgame.ksynic.mvvm.model.Brand
import com.fortgame.ksynic.mvvm.model.Product
import com.fortgame.ksynic.mvvm.model.ProductVariant
import com.fortgame.ksynic.mvvm.model.Seller

/**
 * DTO классы для сериализации Product в JSON
 * Используются для кэширования, так как Color не может быть напрямую сериализован
 */

data class ProductDTO(
    val id: String,
    val name: String,
    val price: Int,
    val oldPrice: Int? = null,
    val discount: Int? = null,
    val rating: Double = 0.0,
    val reviewsCount: Int = 0,
    val images: List<String> = emptyList(),
    val imagesRes: List<Int> = emptyList(), // Drawable ресурсы изображений продукта
    val isTimeLimited: Boolean = false,
    val accentColorValue: ULong, // Храним Color как Long (ARGB значение)
    val isFavorite: Boolean = false,
    val seller: SellerDTO? = null,
    val brand: BrandDTO? = null,
    val description: String? = null,
    val variants: List<ProductVariantDTO> = emptyList(),
    val quantity: Int = 1
) {
    fun toProduct(): Product {
        return Product(
            id = id,
            name = name,
            price = price,
            oldPrice = oldPrice,
            discount = discount,
            rating = rating,
            reviewsCount = reviewsCount,
            images = images,
            imagesRes = imagesRes,
            isTimeLimited = isTimeLimited,
            accentColor = Color(accentColorValue),
            isFavorite = isFavorite,
            seller = seller?.toSeller(),
            brand = brand?.toBrand(),
            description = description,
            variants = variants.map { it.toProductVariant() },
            quantity = quantity
        )
    }
    
    companion object {
        fun fromProduct(product: Product): ProductDTO {
            return ProductDTO(
                id = product.id,
                name = product.name,
                price = product.price,
                oldPrice = product.oldPrice,
                discount = product.discount,
                rating = product.rating,
                reviewsCount = product.reviewsCount,
                images = product.images,
                imagesRes = product.imagesRes,
                isTimeLimited = product.isTimeLimited,
                accentColorValue = product.accentColor.value,
                isFavorite = product.isFavorite,
                seller = product.seller?.let { SellerDTO.fromSeller(it) },
                brand = product.brand?.let { BrandDTO.fromBrand(it) },
                description = product.description,
                variants = product.variants.map { ProductVariantDTO.fromProductVariant(it) },
                quantity = product.quantity
            )
        }
    }
}

data class SellerDTO(
    val id: String,
    val name: String,
    val avatarUrl: String? = null,
    val rating: Double = 0.0,
    val ordersCount: Int = 0,
    val reviewsCount: Int = 0
) {
    fun toSeller(): Seller {
        return Seller(
            id = id,
            name = name,
            avatarUrl = avatarUrl,
            rating = rating,
            ordersCount = ordersCount,
            reviewsCount = reviewsCount
        )
    }
    
    companion object {
        fun fromSeller(seller: Seller): SellerDTO {
            return SellerDTO(
                id = seller.id,
                name = seller.name,
                avatarUrl = seller.avatarUrl,
                rating = seller.rating,
                ordersCount = seller.ordersCount,
                reviewsCount = seller.reviewsCount
            )
        }
    }
}

data class BrandDTO(
    val id: String,
    val name: String,
    val logoUrl: String? = null
) {
    fun toBrand(): Brand {
        return Brand(
            id = id,
            name = name,
            logoUrl = logoUrl
        )
    }
    
    companion object {
        fun fromBrand(brand: Brand): BrandDTO {
            return BrandDTO(
                id = brand.id,
                name = brand.name,
                logoUrl = brand.logoUrl
            )
        }
    }
}

data class ProductVariantDTO(
    val id: String,
    val name: String,
    val value: String,
    val isAvailable: Boolean = true,
    val imagesRes: List<Int> = emptyList(), // Список drawable ресурсов изображений варианта
    val imagesUrl: List<String> = emptyList() // Список URL изображений варианта
) {
    fun toProductVariant(): ProductVariant {
        return ProductVariant(
            id = id,
            name = name,
            value = value,
            isAvailable = isAvailable,
            imagesRes = imagesRes,
            imagesUrl = imagesUrl
        )
    }
    
    companion object {
        fun fromProductVariant(variant: ProductVariant): ProductVariantDTO {
            return ProductVariantDTO(
                id = variant.id,
                name = variant.name,
                value = variant.value,
                isAvailable = variant.isAvailable,
                imagesRes = variant.imagesRes,
                imagesUrl = variant.imagesUrl
            )
        }
    }
}

