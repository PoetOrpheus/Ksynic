package com.fortgame.ksynic.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fortgame.ksynic.mvvm.data.local.LocalDataStore
import com.fortgame.ksynic.mvvm.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel для управления профилем пользователя
 */
class UserProfileViewModel(
    private val localDataStore: LocalDataStore
) : ViewModel() {

    private val _profileState = MutableStateFlow<UserProfile?>(null)
    val profileState: StateFlow<UserProfile?> = _profileState.asStateFlow()

    private var hasLoadedProfile = false

    init {
        loadProfile()
    }

    /**
     * Загрузить профиль из локального хранилища
     */
    fun loadProfile() {
        if (hasLoadedProfile) return
        
        viewModelScope.launch {
            val profile = localDataStore.getUserProfileOrDefault()
            _profileState.value = profile
            hasLoadedProfile = true
        }
    }

    /**
     * Обновить профиль
     */
    fun updateProfile(profile: UserProfile) {
        viewModelScope.launch {
            localDataStore.saveUserProfile(profile)
            _profileState.value = profile
        }
    }

    /**
     * Обновить отдельное поле профиля
     */
    fun updateProfileField(
        firstName: String? = null,
        lastName: String? = null,
        gender: String? = null,
        birthDate: String? = null,
        phone: String? = null,
        email: String? = null,
        displayName: String? = null,
        avatarRes: Int? = null
    ) {
        val currentProfile = _profileState.value ?: UserProfile.default()
        val updatedProfile = currentProfile.copy(
            firstName = firstName ?: currentProfile.firstName,
            lastName = lastName ?: currentProfile.lastName,
            gender = gender ?: currentProfile.gender,
            birthDate = birthDate ?: currentProfile.birthDate,
            phone = phone ?: currentProfile.phone,
            email = email ?: currentProfile.email,
            displayName = displayName ?: currentProfile.displayName,
            avatarRes = avatarRes ?: currentProfile.avatarRes
        )
        updateProfile(updatedProfile)
    }

    /**
     * Получить текущий профиль или профиль по умолчанию
     */
    fun getCurrentProfile(): UserProfile {
        return _profileState.value ?: UserProfile.default()
    }
}

