package com.fortgame.ksynic.U_I.ProfileScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
fun CardPickUp(
    productName:String = "Брюки прямые ТВОЕ",
    image: Painter = painterResource(R.drawable.image_for_profile),
    stateDelivery:String = "Доставлено",
    stateColor: Color= Color(0xFF50C878)
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(fh(160))
            .padding(horizontal = fh(30)),
    ){
        Row(
            modifier=Modifier
                .width(fw(220))
                .height(fh(100))
                .fillMaxHeight()
                .background(Color.White, RoundedCornerShape(20.dp))
        ){
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .height(fh(100))
                    .width(fw(100))
                    .border(4.dp,Color.White,RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier=Modifier
                    .fillMaxSize()
                    .padding(horizontal = fw(5), vertical = fh(5))
            ) {
                Box(
                    modifier=Modifier
                        .fillMaxWidth()
                        .height(fh(60))
                ){
                    Text(
                        text=productName,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 11.sp
                    )
                }
                Text(
                    text="Доставка в ПВЗ:",
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Normal,
                    color=Color(0xFF919191),
                    lineHeight = 11.sp
                )
                Text(
                    text=stateDelivery,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color=stateColor,
                    lineHeight = 13.sp
                )
            }
        }

    }
}

@Preview
@Composable
private fun CardPickUpPreview(){
    CardPickUp()
}