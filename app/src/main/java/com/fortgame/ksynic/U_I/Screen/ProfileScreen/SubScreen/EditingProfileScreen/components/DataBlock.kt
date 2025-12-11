package com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun DataBlock(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = fw(15))
            .background(Color.White, RoundedCornerShape(10.dp)),
    ){
        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(30))
                .padding(horizontal = fw(20)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text="Ваши данные",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp
            )
        }
        Spacer(Modifier.height(fh(10)))

        // Имя
        Text(
            "Имя",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        Text(
            "Денис",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )

        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(2))
                .padding(horizontal = fw(20))
                .background(Color(0xFFF2F2F2))
        )
        Spacer(Modifier.height(fh(8)))

        // Фамилия
        Text(
            "Фамилия",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        Text(
            "Девятгин",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )

        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(2))
                .padding(horizontal = fw(20))
                .background(Color(0xFFF2F2F2))

        )
        Spacer(Modifier.height(fh(8)))

        // Пол
        Text(
            "Пол",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        Text(
            "Мужской",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )

        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(2))
                .padding(horizontal = fw(20))
                .background(Color(0xFFF2F2F2))

        )
        Spacer(Modifier.height(fh(8)))

        // Дата Рождения
        Text(
            "Дата рождения",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        Text(
            "19.09.2001",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )

        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(2))
                .padding(horizontal = fw(20))
                .background(Color(0xFFF2F2F2))

        )
        Spacer(Modifier.height(fh(8)))

        // Номер телефона
        Text(
            "Номер телефона",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        Text(
            "+7 (999) 999-99-99",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )

        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(2))
                .padding(horizontal = fw(20))
                .background(Color(0xFFF2F2F2))

        )
        Spacer(Modifier.height(fh(8)))

        // Почта
        Text(
            "Почта",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        Text(
            "im_gay_19@mail.ru",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        Spacer(Modifier.height(fh(15)))

    }
}

@Composable
@Preview
private fun DataBlockPreview(){
    DataBlock()
}