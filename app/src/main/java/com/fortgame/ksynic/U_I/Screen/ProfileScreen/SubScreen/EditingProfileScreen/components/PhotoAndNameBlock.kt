package com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun PhotoAndNameBlock(){
    // Фото и Имя на площадке
    Column(
        Modifier
            .height(fh(265))
            .fillMaxWidth()
            .padding(horizontal = fw(15))
            .background(Color.White, RoundedCornerShape(10.dp)),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.ava_denis),
                null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(fw(150))
                    .height(fh(150))
            )
            Spacer(Modifier.height(fh(5)))
            Box(
                Modifier
                    .height(fh(15))
                    .width(fw(150)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Изменить фото",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    lineHeight = 14.sp,
                    color = Color(0xFF5D76CB)
                )
            }
            Spacer(Modifier.height(fh(5)))
        }
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(30))
                .padding(horizontal = fw(35)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text="Видимое имя на площадке",
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(40))
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
                text="Денис Д.",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 22.sp,
                modifier = Modifier.padding(horizontal = fw(10))
            )
        }

    }
}

@Composable
@Preview
private fun PhotoAndNameBlockPreview(){
    PhotoAndNameBlock()
}