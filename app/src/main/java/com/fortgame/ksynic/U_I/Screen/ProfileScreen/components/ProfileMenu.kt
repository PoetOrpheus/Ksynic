package com.fortgame.ksynic.U_I.Screen.ProfileScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R

@Composable
fun ProfileMenu(
    countSell: Int,
    countFavorite: Int,
    countReviews: Int,
    onReviewClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Покупки
        ProfileMenuItem(
            iconRes = R.drawable.profile_menu_sell,
            title = "Покупки",
            subtitle = "$countSell заказа",
            modifier = Modifier.weight(1f),
            onReviewClick = onReviewClick
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Избранное
        ProfileMenuItem(
            iconRes = R.drawable.lover,
            title = "Избранное",
            subtitle = "$countFavorite товаров",
            modifier = Modifier.weight(1f),
            onReviewClick = onReviewClick
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Ждут отзыва
        ProfileMenuItem(
            iconRes = R.drawable.star_profile_menu,
            title = "Ждут отзыва",
            subtitle = "$countReviews товаров",
            modifier = Modifier.weight(1f),
            onReviewClick
        )
    }
}

@Composable
fun ProfileMenuItem(
    iconRes: Int,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onReviewClick: () -> Unit={}
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp),
                clip = false,
                ambientColor = Color.Black.copy(alpha = 0.25f),
                spotColor = Color.Black.copy(alpha = 0.25f)
            )
            .width(120.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .clickable(onClick = onReviewClick),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            // Иконка
            Box(
                modifier = Modifier.size(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                )
            }

            // Заголовок
            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 12.sp
            )

            // Подзаголовок
            Text(
                text = subtitle,
                fontSize = 8.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 10.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF2F2F2)
private fun ProfileMenuPreview() {
    ProfileMenu(
        countSell = 23,
        countFavorite = 4,
        countReviews = 5,
        onReviewClick = {}
    )
}