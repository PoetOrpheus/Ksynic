package com.fortgame.ksynic.U_I.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.U_I.ProfileScreen.components.CardPickUp
import com.fortgame.ksynic.U_I.ProfileScreen.components.Profile
import com.fortgame.ksynic.U_I.ProfileScreen.components.ProfileMenu
import com.fortgame.ksynic.U_I.TopHeaderWithoutSearch
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
    ) {
        TopHeaderWithoutSearch()
        Spacer(modifier = Modifier.height(fh(10)))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(fh(10))
        ) {
            // Профиль пользователя
            item {
                Profile()
            }

            // Меню профиля
            item {
                ProfileMenu(23, 4, 5)
            }

            item{
                Text(
                    text="Можно забирать",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 32.sp,
                    modifier=Modifier.padding(horizontal = fw(30))
                )
                Spacer(
                    modifier=Modifier.height(fh(10))
                )
            }

            // Можно забирать
            item{
                CardPickUp()
            }

            item{
                Text(
                    text="В пути",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 32.sp,
                    modifier=Modifier.padding(horizontal = fw(30))
                )
                Spacer(
                    modifier=Modifier.height(fh(10))
                )
            }


            // В пути
            item{
                LazyRow() {
                    item{
                        CardPickUp(
                            productName ="Xiaomi Смартфон 15T + часы Xiaomi Watch",
                        )
                    }
                    item{
                        CardPickUp()
                    }
                }
            }
        }
    }
}


@Composable
@Preview
private fun ProfileScreenPreview(){
    ProfileScreen()
}
