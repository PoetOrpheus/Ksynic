package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw


@Composable
fun InfoCardsSection(
    description: String? = null
){
    var showText by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    var select by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(10.dp))
    ){
        Spacer(Modifier.height(fh(10)))
        Row(Modifier
            .height(fh(30))
            .padding(horizontal = fw(10)),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Box(
                Modifier
                    .width(fw(120))
                    .fillMaxHeight()
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    )
                    .background(
                       color= if (select) Color(0xFF5D76CB) else Color.White)
                    .clickable(
                        onClick = if (!select) {{select=!select}} else {{}}),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text="Описание",
                    fontSize = 16.sp,
                    color= if (select) Color.White else Color.Black,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(
                Modifier.width(fw(10))
            )

            Box(
                Modifier
                    .width(fw(175))
                    .fillMaxHeight()
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    )
                    .background(
                     color= if(!select) Color(0xFF5D76CB) else Color.White
                    )
                    .clickable(
                        onClick= if (select) {{select=!select}} else {{}}
                    ),
                contentAlignment = Alignment.Center

            ){
                Text(
                    text="Характеристики",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 18.sp,
                    color= if (!select) Color.White else Color.Black
                )
            }
        }
        Spacer (Modifier.height(fh(10)))

        if (select)
            Info(showText, description, onShowTextClick = { showText = true })
        else
            CharacteristicsColumn()

    }
}


@Composable
private fun Info(
    showText: Boolean = false,
    description: String? = null,
    onShowTextClick: () -> Unit
){

    Box(
        modifier= if (showText){
            Modifier
                .fillMaxWidth()
        } else {
            Modifier.fillMaxWidth().height(fh(160))
        },
    ){
        Text(
            text = description ?: "Описание товара отсутствует",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 14.sp,
            modifier = Modifier
                .padding(horizontal = fw(10))
        )
        if (!showText){
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(fh(50))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.White.copy(alpha = 0.8f), Color(0xFF5D76CB))
                        )
                    )
                    .align(Alignment.BottomCenter)
                    .clickable(onClick = onShowTextClick),
                contentAlignment = Alignment.CenterEnd

            ){
                Row(
                    Modifier
                        .height(fh(30))
                        .fillMaxWidth()
                        .padding(horizontal = fw(10))
                ){
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .width(fw(400)),
                        contentAlignment = Alignment.CenterEnd
                    ){
                        Text(
                            text="Развернуть",
                            fontSize = 18.sp,
                            color=Color.White,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 20.sp,
                        )
                    }
                    Spacer(Modifier.width(fw(4)))
                    Image(
                        painter = painterResource(R.drawable.down_icon),
                        null,
                        Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Composable
private fun CharacteristicsColumn(){
    LazyColumn (){
        item{
            Characteristic()
            Box(Modifier.height(fh(2)).fillMaxWidth().background(Color(0xFFD9D9D9)))
        }
        item{
            Characteristic()
            Box(Modifier.height(fh(2)).fillMaxWidth().background(Color(0xFFD9D9D9)))
        }
        item{
            Characteristic()
            Box(Modifier.height(fh(2)).fillMaxWidth().background(Color(0xFFD9D9D9)))
        }
        item{
            Characteristic()
            Box(Modifier.height(fh(2)).fillMaxWidth().background(Color(0xFFD9D9D9)))
        }
    }

}

@Preview
@Composable
private fun Characteristic(
    name: String = "name",
    value: String = "value"
){
    Row(
        Modifier.height(fh(30)).fillMaxWidth().padding(horizontal = fw(10)).background(Color.White)
    ){

        Box(
            Modifier.fillMaxHeight().width(fw(120)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                name,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(Modifier.width(fw(10)))

        Box(
            Modifier.fillMaxHeight().fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                value,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}


@Composable
@Preview
private fun InfoCardsSectionPreview(){
    InfoCardsSection()
}