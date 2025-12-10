package com.fortgame.ksynic.U_I.ShopCartScreen

import android.R.attr.onClick
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.U_I.ShopCartScreen.components.CardCart
import com.fortgame.ksynic.U_I.ShopCartScreen.components.TotalCountCart
import com.fortgame.ksynic.U_I.TopHeaderWithoutSearch
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun ShopCartScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
    ) {
        TopHeaderWithoutSearch()
        Spacer(modifier = Modifier.height(fh(15)))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .background(Color.White, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                Modifier.height(fh(465))
            ) {
                items(4){
                    CardCart()
                    Box(
                        Modifier.height(fh(2)).fillMaxWidth().background(Color(0xFFF2F2F2))
                    )
                }

            }



            // Офорить заказ + итоговый результат
            Spacer(Modifier.height(fh(20)))
            // todo: Проверить цвета кнопки по нажатию и так далее
            Button(
                modifier = Modifier
                    .height(fh(40))
                    .width(fw(340)),
                colors = ButtonColors(containerColor = Color(0xFF50C878),
                    contentColor = Color.White,
                    disabledContentColor = Color.Blue,
                    disabledContainerColor = Color.Yellow),
                onClick = {
                    Toast.makeText(
                        context,
                        "Длинное сообщение Toast",
                        Toast.LENGTH_LONG
                    ).show()
                },
            ) {
                Text(
                    text="Оформить заказ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 17.sp

                )
            }

            Box(
                modifier=Modifier
                    .width(fw(340))
                    .height(fh(40)),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text="Далее можно выбрать место доставки и способ оплаты",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
            TotalCountCart()
        }
    }
}

@Composable
@Preview
private fun ShopCartScreenPreview(){
    ShopCartScreen()
}