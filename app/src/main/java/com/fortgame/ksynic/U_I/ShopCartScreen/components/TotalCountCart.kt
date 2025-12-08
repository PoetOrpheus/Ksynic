package com.fortgame.ksynic.U_I.ShopCartScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun TotalCountCart(
    count:Int=3,
    countPrice:Int=121000,
    salePrice:Int=36800,
    finalPrice:Int=84200
){
    Column(
        modifier=Modifier
            .padding(horizontal = fw(40), vertical = (fh(10)))
            .background(Color.White)

    ){
        Box(
            modifier=Modifier
                .width(fw(100))
                .height(fh(50)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text="Заказ",
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 27.sp
            )
        }
        Spacer(Modifier.height(fh(10)))

        ParametrTotal("Товары ($count)", price =countPrice,Color.Black )

        Spacer(Modifier.height(fh(10)))

        ParametrTotal("Скидка", price =salePrice,Color(0xFFCC3333))

        Spacer(Modifier.height(fh(10)))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                // Чтобы линия была внутри паддингов (как текст), добавьте padding здесь.
                // Если хотите от края до края карточки — уберите padding.
                // Обычно красиво, когда линия выровнена с текстом:
                .height(2.dp) // Ваше требование 1dp (можно использовать 0.5.dp для "hairline")
                .background(Color(0xFF939393)) // Очень светло-серый цвет, как на макетах
        )
        Spacer(Modifier.height(fh(10)))

        // Итого
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Box(
                modifier=Modifier
                    .height(fh(40)),
                contentAlignment = Alignment.CenterStart
            ){
                Text(
                    text="Итого",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 27.sp
                )
            }

            Box(
                modifier=Modifier
                    .height(fh(40))
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                Text(
                    text="$finalPrice ₽",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 27.sp,
                    color = Color(0xFF50C878)
                )
            }
        }


    }
}

@Composable
private fun ParametrTotal(
    text:String,
    price:Int,
    colorPrice:Color

){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Box(
            modifier = Modifier
                .height(fh(30)),
            contentAlignment = Alignment.CenterStart

        ){
            Text(
                text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 17.sp

            )
        }

        Box(
            Modifier
                .height(fh(30))
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ){
            Text(
                text="$price ₽",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 17.sp,
                color=colorPrice
            )
        }
    }
}

@Composable
@Preview
private fun TotalCountCartPreview(){
    TotalCountCart()
}