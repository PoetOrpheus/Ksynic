package com.fortgame.ksynic.U_I.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun TopHeaderWithoutSearch() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(fh(50))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF5D76CB), Color(0xFFFCB4D5)),
                    start = Offset(0f, 0f),  // Левый верхний
                    end = Offset(600f, 600f), // Правый нижний (или используйте Offset.Infinite)
                )
            )
    ) {
        Column(
            modifier = Modifier.padding(
                start = fw(15),
                end = fw(15),
                top = fh(10)
            )
        ) {
            // Строка адреса
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "ПВЗ: ул. Королева, 5",
                    //font=Font.Inter,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                // Иконка сообщений
                Image(
                    painter = painterResource(R.drawable.message_without_notification),
                    contentDescription = "Месседжер",
                    modifier = Modifier
                        .width(fw(40))
                        .height(fh(30))
                    //.padding(top = fh(10), end = fw(15))
                )

            }


        }
    }
}

@Composable
@Preview
private fun TopHeaderWithoutSearchPreview(){
    Box(modifier = Modifier.fillMaxSize()){
        TopHeaderWithoutSearch()
    }
}