package com.fortgame.ksynic.U_I.ProfileScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.FavoriteScreen.FavoriteItem
import com.fortgame.ksynic.U_I.FavoriteScreen.components.CardFavorite
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun HistorySectionProduct(){
    Column(
        modifier=Modifier
            .fillMaxWidth()
            .height(fh(400))
            .padding(horizontal = fh(15))
            .background(Color.White, RoundedCornerShape(10.dp))

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(fh(70))
                .padding(horizontal = fw(15)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "История просмотров",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 25.sp
            )

            Image(
                painter = painterResource(R.drawable.circle_right),
                contentDescription = null,
            )
        }
        LazyRow(
            // Самое важное: отступ 30px слева для первого элемента и справа для последнего
            contentPadding = PaddingValues(horizontal = fw(15)),
            // Расстояние МЕЖДУ карточками (например, 10 или 15 пикселей)
            horizontalArrangement = Arrangement.spacedBy(fw(15))
        ) {
            items(listOf(
                ProfileItem("Брюки прямые TBOE", 800, oldPrice = 3000, discount = 70, rating = 4.8, reviews = 944, isTimeLimited = true),
                ProfileItem("Часы наручные Кварцевые", 4200, oldPrice = 21000, discount = 80, rating = 5.0, reviews = 23, isTimeLimited = true, colorBottom = Color(0xFFCC3333), colorText = Color(0xFFCC3333)),
                ProfileItem("Кеды adidas Sportswear Hoops 3.0", 3743, rating = 4.9, reviews = 457, isTimeLimited = true),
                ProfileItem("Xiaomi 15T + часы Xiaomi Watch", 40000, oldPrice = 60000, discount = 20, rating = 4.9, reviews = 437, isTimeLimited = true),
            )
            ) { item ->
                CardProfile(
                    title = item.title,
                    price = item.price,
                    oldPrice = item.oldPrice,
                    discount = item.discount,
                    rating = item.rating,
                    reviews = item.reviews,
                    imageUrl = null,
                    isTimeLimited = item.isTimeLimited,
                    colorBottom = item.colorBottom,
                    colorText = item.colorText
                )
            }
        }
    }
}

@Composable
@Preview()
private fun HistorySectionProductPreview(){
    HistorySectionProduct()
}

data class ProfileItem(
    val title: String,
    val price: Int,
    val oldPrice: Int = 0,
    val discount: Int = 0,
    val rating: Double,
    val reviews: Int,
    val isTimeLimited: Boolean,
    val colorBottom: Color = Color(0xFF5D76CB),
    val colorText: Color = Color(0xFF5D76CB)
)