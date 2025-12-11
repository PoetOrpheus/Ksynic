package com.fortgame.ksynic.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fortgame.ksynic.mvvm.repository.ProductRepository
import com.fortgame.ksynic.mvvm.repository.ProductRepositoryImpl

/**
 * Фабрика для создания ViewModels
 */
class ViewModelFactory(
    private val productRepository: ProductRepository = ProductRepositoryImpl()
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ProductViewModel::class.java) -> {
                ProductViewModel(productRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}


