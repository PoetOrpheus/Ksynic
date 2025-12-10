package com.fortgame.ksynic.U_I.RegistrationAndLoginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.TopHeaderWithoutSearch
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun RegistrationScreen(
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgGray)

    ) {
        TopHeaderWithoutSearch()

        Column(
            Modifier
                .height(fh(385))
                .fillMaxWidth()
                .padding(horizontal = fw(25))
                .align(Alignment.Center)
                .background(Color.White, RoundedCornerShape(20.dp))
        ){
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Box(
                    Modifier.padding(horizontal = fw(20))
                ){
                    Text(
                        "Зарегистрироваться",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 22.sp,
                    )
                }

                Box(
                    Modifier
                        .height(fh(70))
                        .width(fw(70)),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painterResource(R.drawable.plus),
                        null
                    )
                }
            }

            Text(
                text="Имя",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Light,
                modifier=Modifier.padding(horizontal = fw(30))
            )
            Spacer(Modifier.height(fh(5)))

            // ИМЯ
            NameBox("Иван")

            Spacer(Modifier.height(fh(20)))
            Text(
                text="Фамилия",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Light,
                modifier=Modifier.padding(horizontal = fw(30))
            )
            Spacer(Modifier.height(fh(5)))
            // ФАМИЛИЯ
            NameBox("Иванов")
            Spacer(Modifier.height(fh(20)))

            Text(
                text="Номер телефона",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Light,
                modifier=Modifier.padding(horizontal = fw(30))
            )
            Spacer(Modifier.height(fh(5)))

            // Номер телефона
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(Modifier.width(fw(20)))
                Image(
                    painterResource(R.drawable.russian_flag),
                    null
                )
                NameBox("+7  (000)  000-00-00")
            }
            Spacer(Modifier.height(fh(20)))

            // Получить код
            Box(
                Modifier
                    .height(fh(40))
                    .width(fw(200))
                    .padding(horizontal = fw(20))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    )
                    .background(Color.White)
                    .align(Alignment.End),
                contentAlignment = Alignment.Center
            ){
                Text(
                    "Получить код по СМС",
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Normal,
                )
            }


        }
    }
}


@Composable
fun NameBox(
    text:String="Иван",
){
    Box(
        Modifier
            .height(fh(40))
            .fillMaxWidth()
            .padding(horizontal = fw(20))
            .shadow(
                elevation = 5.dp, // Figma: Blur 5
                shape = RoundedCornerShape(10.dp),
                spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
            )
            .background(Color.White),
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            text,
            fontSize = 20.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Bold,
            color=Color(0xFFA2A2A2),
            modifier=Modifier.padding(horizontal = fw(10))
        )
    }
}

@Composable
@Preview
private fun RegistrationScreenPreview() {
    RegistrationScreen()
}
