package com.fortgame.ksynic.mvvm.model

/**
 * Модель профиля пользователя
 */
data class UserProfile(
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "", // "Мужской", "Женский", и т.д.
    val birthDate: String = "", // Формат: "DD.MM.YYYY"
    val phone: String = "",
    val email: String = "",
    val displayName: String = "", // Видимое имя на площадке
    val avatarRes: Int? = null // Ресурс аватара (drawable ID)
) {
    /**
     * Получить полное имя (имя + фамилия)
     */
    fun getFullName(): String {
        return if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
            "$firstName $lastName"
        } else if (firstName.isNotEmpty()) {
            firstName
        } else if (lastName.isNotEmpty()) {
            lastName
        } else {
            ""
        }
    }

    /**
     * Получить короткое имя для отображения (например, "Денис Д.")
     */
    fun getShortName(): String {
        return if (displayName.isNotEmpty()) {
            displayName
        } else {
            val firstInitial = firstName.take(1).uppercase()
            val lastInitial = lastName.take(1).uppercase()
            if (firstInitial.isNotEmpty() && lastInitial.isNotEmpty()) {
                "${firstName.take(1).uppercase()}${lastName.take(1).uppercase()}."
            } else if (firstName.isNotEmpty()) {
                "$firstName ${lastName.take(1).uppercase()}."
            } else {
                getFullName()
            }
        }
    }

    companion object {
        /**
         * Создать профиль по умолчанию для тестов
         */
        fun default(): UserProfile {
            return UserProfile(
                firstName = "Денис",
                lastName = "Девятгин",
                gender = "Мужской",
                birthDate = "19.09.2001",
                phone = "+7 777 777 77 77",
                email = "asdasdf@mail.ru",
                displayName = "Денис Д.",
                avatarRes = com.fortgame.ksynic.R.drawable.ava_denis
            )
        }
    }
}

