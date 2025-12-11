package com.fortgame.ksynic.mvvm.ui.state

/**
 * Базовый класс для состояний UI
 */
sealed class UiState<out T> {
    /**
     * Начальное состояние (загрузка еще не началась)
     */
    object Idle : UiState<Nothing>()

    /**
     * Состояние загрузки
     */
    object Loading : UiState<Nothing>()

    /**
     * Состояние успеха с данными
     */
    data class Success<T>(val data: T) : UiState<T>()

    /**
     * Состояние ошибки
     */
    data class Error(val message: String? = null, val throwable: Throwable? = null) : UiState<Nothing>()
}

