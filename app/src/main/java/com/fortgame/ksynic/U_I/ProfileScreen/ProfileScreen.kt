package com.fortgame.ksynic.U_I.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.ProfileScreen.components.BottomProfileMenuNavigation
import com.fortgame.ksynic.U_I.ProfileScreen.components.CardPickUp
import com.fortgame.ksynic.U_I.ProfileScreen.components.HistorySectionProduct
import com.fortgame.ksynic.U_I.ProfileScreen.components.Profile
import com.fortgame.ksynic.U_I.ProfileScreen.components.ProfileMenu
import com.fortgame.ksynic.U_I.TopHeaderWithoutSearch
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun ProfileScreen(onReviewClick: () -> Unit, onEditingClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
    ) {
        TopHeaderWithoutSearch()
        Spacer(modifier = Modifier.height(fh(10)))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            // Убираем verticalArrangement, лучше ставить Spacer где нужно,
            // или оставляем spacedBy, но следим за отступами заголовков
            verticalArrangement = Arrangement.spacedBy(fh(10))
        ) {
            // Профиль пользователя
            item { Profile(onEditingClick) }

            // Меню профиля
            item { ProfileMenu(23, 4, 5 , onReviewClick) }

            // --- СЕКЦИЯ "МОЖНО ЗАБИРАТЬ" ---
            item {
                Text(
                    text = "Можно забирать",
                    fontSize = 22.sp, // Чуть уменьшил, 30sp кажется огромным для заголовка секции
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = fw(30)) // Заголовок по сетке
                )
                Spacer(modifier = Modifier.height(fh(10)))
            }

            item {
                // Для одиночной карточки просто добавляем отступ слева через модификатор
                Box(modifier = Modifier.padding(horizontal = fw(30))) {
                    CardPickUp()
                }
            }

            // --- СЕКЦИЯ "В ПУТИ" ---
            item {
                Spacer(modifier = Modifier.height(fh(10))) // Отступ от предыдущей секции
                Text(
                    text = "В пути",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = fw(30))
                )
                Spacer(modifier = Modifier.height(fh(10)))
            }

            item {
                LazyRow(
                    // Самое важное: отступ 30px слева для первого элемента и справа для последнего
                    contentPadding = PaddingValues(horizontal = fw(30)),
                    // Расстояние МЕЖДУ карточками (например, 10 или 15 пикселей)
                    horizontalArrangement = Arrangement.spacedBy(fw(10))
                ) {
                    item {
                        CardPickUp(
                            productName = "Xiaomi Смартфон 15T + часы Xiaomi Watch",
                            image = painterResource(R.drawable.image_for_product_2),
                            stateDelivery = "9 декабря",
                            stateColor = Color.Black
                        )
                    }
                    item {
                        CardPickUp(
                            productName = "Часы наручные Кварцевые",
                            image = painterResource(R.drawable.image_for_product_3),
                            stateDelivery = "10 декабря",
                            stateColor = Color.Black
                        )
                    }
                    // Пример третьей карточки, чтобы проверить скролл
                    item {
                        CardPickUp(
                            productName = "Тестовый товар",
                            image = painterResource(R.drawable.image_for_product_1),
                            stateDelivery = "8 декабря",
                            stateColor = Color.Gray
                        )
                    }
                }
            }

            // --- СЕКЦИЯ "История просмотров" ---
            item {
                Spacer(modifier = Modifier.height(fh(10))) // Отступ от предыдущей секции
                HistorySectionProduct()
            }

            item{
                Spacer(modifier = Modifier.height(fh(10)))
                BottomProfileMenuNavigation()
                Spacer(modifier=Modifier.height(fw(30)))
            }

        }
    }
}

@Composable
@Preview
private fun ProfileScreenPreview(){
    ProfileScreen({},{})
}