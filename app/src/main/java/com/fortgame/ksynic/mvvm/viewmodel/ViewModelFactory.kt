package com.fortgame.ksynic.mvvm.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fortgame.ksynic.mvvm.repository.ProductRepository
import com.fortgame.ksynic.mvvm.repository.ProductRepositoryImpl

/**
 * Фабрика для создания ViewModels
 */
class ViewModelFactory(
    private val context: Context? = null
) : ViewModelProvider.Factory {
    
    private val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl(context)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ProductViewModel::class.java) -> {
                ProductViewModel(productRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
    
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        
        fun getInstance(context: Context): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}


