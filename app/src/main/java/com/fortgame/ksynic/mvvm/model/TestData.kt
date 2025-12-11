package com.fortgame.ksynic.mvvm.model

import androidx.compose.ui.graphics.Color
import com.fortgame.ksynic.R

// ====================================================================
// ТЕСТОВЫЕ ДАННЫЕ - УДАЛИТЬ ПОСЛЕ ИНТЕГРАЦИИ С РЕАЛЬНЫМ API
// ====================================================================
// Этот файл содержит примеры данных для демонстрации и тестирования.
// После подключения к реальному API этот файл должен быть удален.

/**
 * Тестовые данные - Бренды
 */
object TestBrands {
    val adidas = Brand(
        id = "brand_1",
        name = "Adidas",
        logoUrl = null
    )

    val calvinKlein = Brand(
        id = "brand_2",
        name = "Calvin Klein",
        logoUrl = null
    )

    val nike = Brand(
        id = "brand_3",
        name = "Nike",
        logoUrl = null
    )

    val apple = Brand(
        id = "brand_4",
        name = "Apple",
        logoUrl = null
    )

    val samsung = Brand(
        id = "brand_5",
        name = "Samsung",
        logoUrl = null
    )

    val allBrands = listOf(adidas, calvinKlein, nike, apple, samsung)
}

/**
 * Тестовые данные - Продавцы
 */
object TestSellers {
    val operatorZamesov = Seller(
        id = "seller_1",
        name = "Оператор замесов",
        avatarUrl = null,
        rating = 4.9,
        ordersCount = 3400,
        reviewsCount = 543
    )

    val fashionStore = Seller(
        id = "seller_2",
        name = "Fashion Store",
        avatarUrl = null,
        rating = 4.8,
        ordersCount = 1200,
        reviewsCount = 287
    )

    val techShop = Seller(
        id = "seller_3",
        name = "TechShop",
        avatarUrl = null,
        rating = 5.0,
        ordersCount = 5600,
        reviewsCount = 892
    )

    val sportStyle = Seller(
        id = "seller_4",
        name = "Sport Style",
        avatarUrl = null,
        rating = 4.7,
        ordersCount = 2100,
        reviewsCount = 156
    )

    val watchMaster = Seller(
        id = "seller_5",
        name = "Watch Master",
        avatarUrl = null,
        rating = 4.9,
        ordersCount = 890,
        reviewsCount = 234
    )

    val allSellers = listOf(operatorZamesov, fashionStore, techShop, sportStyle, watchMaster)
}

/**
 * Тестовые данные - Продукты
 */
object TestProducts {
    // Продукт 1: Кеды Adidas (без скидки)
    val adidasSneakers = Product(
        id = "product_1",
        name = "Кеды adidas Sportswear Hoops 3.0",
        price = 3743,
        oldPrice = null,
        discount = null,
        rating = 4.9,
        reviewsCount = 457,
        images = emptyList(), // В реальном приложении здесь будут URL изображений
        imagesRes = listOf(R.drawable.image_for_product_1, R.drawable.adidas_1_1, R.drawable.adidas_1), // Карусель изображений продукта
        isTimeLimited = false,
        accentColor = Color(0xFF000000), // Черный цвет
        isFavorite = false,
        seller = TestSellers.sportStyle,
        brand = TestBrands.adidas,
        description = "Спортивные кеды Adidas Sportswear Hoops 3.0. Удобная подошва и современный дизайн.",
        specifications = listOf(
            ProductSpecification("Материал верха", "Текстиль, синтетика"),
            ProductSpecification("Материал подошвы", "Резина"),
            ProductSpecification("Страна производства", "Китай"),
            ProductSpecification("Вес", "350 г")
        ),
        variants = listOf(
            ProductVariant(
                id = "variant_1_1",
                name = "Размер", 
                value = "40", 
                isAvailable = true, 
                imagesRes = listOf(R.drawable.adidas_2, R.drawable.adidas_2_1)
            ),
            ProductVariant(
                id = "variant_1_2", 
                name = "Размер", 
                value = "41", 
                isAvailable = true, 
                imagesRes = listOf(R.drawable.image_for_product_1, R.drawable.watch_2)
            ),
            ProductVariant(
                id = "variant_1_3", 
                name = "Размер", 
                value = "42", 
                isAvailable = true, 
                imagesRes = listOf(R.drawable.watch_2, R.drawable.watch_1)
            ),
            ProductVariant(id = "variant_1_4", name = "Размер", value = "43", isAvailable = false)
        ),
        quantity = 1
    )

    // Продукт 2: Часы наручные (со скидкой)
    val quartzWatch = Product(
        id = "product_2",
        name = "Часы наручные Кварцевые",
        price = 4200,
        oldPrice = 21000,
        discount = 80,
        rating = 5.0,
        reviewsCount = 23,
        images = emptyList(),
        imagesRes = listOf(R.drawable.watch_1, R.drawable.watch_2), // Карусель изображений продукта
        isTimeLimited = true,
        accentColor = Color(0xFFCC3333), // Красный цвет
        isFavorite = false,
        seller = TestSellers.watchMaster,
        brand = TestBrands.calvinKlein,
        description = "Элегантные кварцевые наручные часы. Классический дизайн и надежный механизм.",
        specifications = listOf(
            ProductSpecification("Тип механизма", "Кварцевый"),
            ProductSpecification("Материал корпуса", "Нержавеющая сталь"),
            ProductSpecification("Водостойкость", "30 м"),
            ProductSpecification("Диаметр корпуса", "40 мм")
        ),
        variants = listOf(
            ProductVariant(id = "variant_2_1", name = "Цвет", value = "Черный", isAvailable = true),
            ProductVariant(id = "variant_2_2", name = "Цвет", value = "Серебристый", isAvailable = true),
            ProductVariant(id = "variant_2_3", name = "Цвет", value = "Золотистый", isAvailable = false)
        ),
        quantity = 1
    )

    // Продукт 3: Часы Calvin Klein (детальный экран)
    val calvinKleinWatch = Product(
        id = "product_3",
        name = "Часы наручные Calvin Klein черные мужские",
        price = 4200,
        oldPrice = 21000,
        discount = 80,
        rating = 4.9,
        reviewsCount = 457,
        images = emptyList(),
        imagesRes = listOf(R.drawable.watch_1, R.drawable.watch_2, R.drawable.watch_3), // Карусель изображений продукта
        isTimeLimited = true,
        accentColor = Color(0xFFCC3333), // Красный цвет
        isFavorite = true,
        seller = TestSellers.operatorZamesov,
        brand = TestBrands.calvinKlein,
        description = "Премиальные мужские наручные часы Calvin Klein. Черный корпус, кожаный ремешок. Водостойкость 50м.",
        specifications = listOf(
            ProductSpecification("Бренд", "Calvin Klein"),
            ProductSpecification("Тип механизма", "Кварцевый"),
            ProductSpecification("Материал корпуса", "Нержавеющая сталь"),
            ProductSpecification("Материал ремешка", "Кожа"),
            ProductSpecification("Водостойкость", "50 м"),
            ProductSpecification("Диаметр корпуса", "42 мм"),
            ProductSpecification("Толщина корпуса", "8 мм"),
            ProductSpecification("Циферблат", "Черный с люминесцентными стрелками")
        ),
        variants = listOf(
            ProductVariant(
                id = "variant_3_1", 
                name = "Цвет", 
                value = "Серый", 
                isAvailable = true,
                imagesRes = listOf(R.drawable.watch_1, R.drawable.watch_2) // Карусель изображений варианта (с разных сторон)
            ),
            ProductVariant(
                id = "variant_3_2", 
                name = "Цвет", 
                value = "Чёрный", 
                isAvailable = true,
                imagesRes = listOf(R.drawable.watch_2, R.drawable.watch_3, R.drawable.watch_1)
            ),
            ProductVariant(
                id = "variant_3_3", 
                name = "Цвет", 
                value = "Серебристый", 
                isAvailable = true,
                imagesRes = listOf(R.drawable.watch_3, R.drawable.watch_1, R.drawable.watch_2)
            )
        ),
        quantity = 2
    )

    // Продукт 4: iPhone (техника)
    val iPhone = Product(
        id = "product_4",
        name = "iPhone 15 Pro 256GB",
        price = 89990,
        oldPrice = 99990,
        discount = 10,
        rating = 4.8,
        reviewsCount = 1234,
        images = emptyList(),
        imagesRes = listOf(R.drawable.watch_1, R.drawable.watch_2, R.drawable.watch_3), // Карусель изображений продукта
        isTimeLimited = false,
        accentColor = Color(0xFF007AFF), // Синий цвет Apple
        isFavorite = false,
        seller = TestSellers.techShop,
        brand = TestBrands.apple,
        description = "Новый iPhone 15 Pro с чипом A17 Pro, камерой Pro и дисплеем ProMotion.",
        specifications = listOf(
            ProductSpecification("Диагональ экрана", "6.1 дюйма"),
            ProductSpecification("Процессор", "Apple A17 Pro"),
            ProductSpecification("Объем памяти", "256 ГБ"),
            ProductSpecification("Камера", "48 Мп + 12 Мп + 12 Мп"),
            ProductSpecification("Батарея", "До 23 часов видео"),
            ProductSpecification("ОС", "iOS 17"),
            ProductSpecification("Вес", "187 г")
        ),
        variants = listOf(
            ProductVariant(id = "variant_4_1", name = "Цвет", value = "Титановый синий", isAvailable = true),
            ProductVariant(id = "variant_4_2", name = "Цвет", value = "Титановый белый", isAvailable = true),
            ProductVariant(id = "variant_4_3", name = "Цвет", value = "Титановый черный", isAvailable = true),
            ProductVariant(id = "variant_4_4", name = "Память", value = "128GB", isAvailable = true),
            ProductVariant(id = "variant_4_5", name = "Память", value = "256GB", isAvailable = true),
            ProductVariant(id = "variant_4_6", name = "Память", value = "512GB", isAvailable = false)
        ),
        quantity = 1
    )

    // Продукт 5: Кроссовки Nike (спорт)
    val nikeShoes = Product(
        id = "product_5",
        name = "Кроссовки Nike Air Max 270",
        price = 8999,
        oldPrice = null,
        discount = null,
        rating = 4.6,
        reviewsCount = 89,
        images = emptyList(),
        imagesRes = listOf(R.drawable.watch_1, R.drawable.watch_2), // Карусель изображений продукта
        isTimeLimited = false,
        accentColor = Color(0xFF000000), // Черный цвет
        isFavorite = true,
        seller = TestSellers.fashionStore,
        brand = TestBrands.nike,
        description = "Кроссовки Nike Air Max 270 с технологией Air для максимального комфорта при беге и ходьбе.",
        specifications = listOf(
            ProductSpecification("Материал верха", "Синтетическая кожа, текстиль"),
            ProductSpecification("Технология подошвы", "Air Max"),
            ProductSpecification("Страна производства", "Вьетнам"),
            ProductSpecification("Вес", "320 г"),
            ProductSpecification("Тип застежки", "Шнуровка")
        ),
        variants = listOf(
            ProductVariant(id = "variant_5_1", name = "Размер", value = "39", isAvailable = true),
            ProductVariant(id = "variant_5_2", name = "Размер", value = "40", isAvailable = true),
            ProductVariant(id = "variant_5_3", name = "Размер", value = "41", isAvailable = true),
            ProductVariant(id = "variant_5_4", name = "Размер", value = "42", isAvailable = true),
            ProductVariant(id = "variant_5_5", name = "Размер", value = "43", isAvailable = false),
            ProductVariant(id = "variant_5_6", name = "Цвет", value = "Черный/Белый", isAvailable = true),
            ProductVariant(id = "variant_5_7", name = "Цвет", value = "Красный/Белый", isAvailable = true)
        ),
        quantity = 1
    )

    val allProducts = listOf(
        adidasSneakers,
        quartzWatch,
        calvinKleinWatch,
        iPhone,
        nikeShoes
    )
}

// ====================================================================
// КОНЕЦ ТЕСТОВЫХ ДАННЫХ
// ====================================================================

