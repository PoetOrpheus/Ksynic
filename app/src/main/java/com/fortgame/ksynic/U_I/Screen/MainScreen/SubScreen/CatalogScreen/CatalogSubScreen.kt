package com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.CatalogScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.TopHeaderWithReturn
import com.fortgame.ksynic.theme.lowWhite
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun CatalogSubScreen(
    onBackClick:()->Unit = {},
    onSubcategoryClick: (String) -> Unit = {},
){
    val context = LocalContext.current
    Box(modifier = Modifier
        .background(BgGray)) {
        TopHeaderWithReturn(onBackClick)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = fw(5),
                    end = fw(5),
                    top = fh(60)
                ),
        ) {
            item{Spacer(Modifier.height(fh(10)))}
            item{
                Category("Блузы и рубашки",true, onClick = { onSubcategoryClick("Блузы и рубашки") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Брюки, бриджи и капри", onClick = { onSubcategoryClick("Брюки, бриджи и капри") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Верхняя одежда", onClick = { onSubcategoryClick("Верхняя одежда") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Джемперы, свитеры и кардиганы", onClick = { onSubcategoryClick("Джемперы, свитеры и кардиганы") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Джинсы и джеггинсы", onClick = { onSubcategoryClick("Джинсы и джеггинсы") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Домашняя одежда", onClick = { onSubcategoryClick("Домашняя одежда") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Комбинезоны", onClick = { onSubcategoryClick("Комбинезоны") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Костюмы и комплекты", onClick = { onSubcategoryClick("Костюмы и комплекты") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Купальники и пляжная одежда", onClick = { onSubcategoryClick("Купальники и пляжная одежда") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Лонгсливы", onClick = { onSubcategoryClick("Лонгсливы") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Носки, колготки и чулки", onClick = { onSubcategoryClick("Носки, колготки и чулки") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Пиджаки, жакеты и жилеты", onClick = { onSubcategoryClick("Пиджаки, жакеты и жилеты") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Платья и сарафаны", onClick = { onSubcategoryClick("Платья и сарафаны") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Термобелье", onClick = { onSubcategoryClick("Термобелье") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Толстовки, свитшоты и худи", onClick = { onSubcategoryClick("Толстовки, свитшоты и худи") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Туники", onClick = { onSubcategoryClick("Туники") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Футболки и топы", onClick = { onSubcategoryClick("Футболки и топы") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Шорты", onClick = { onSubcategoryClick("Шорты") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Юбки", onClick = { onSubcategoryClick("Юбки") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Одежда больших размеров", onClick = { onSubcategoryClick("Одежда больших размеров") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Одежда для беременных", onClick = { onSubcategoryClick("Одежда для беременных") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }
            item{
                Category("Свадебные платья",false,true, onClick = { onSubcategoryClick("Свадебные платья") })
                Box(Modifier.fillMaxWidth().height(fh(2)).background(lowWhite))
            }

            item{
                Spacer(Modifier.height(fh(20)))
            }

        }
    }
}

@Preview
@Composable
private fun Category(
    text:String="Блузы и рубашки",
    firt:Boolean=false,
    end: Boolean=false,
    onClick: () -> Unit = {},
){
    Row(
        Modifier
            .height(fh(40))
            .fillMaxWidth()
            .padding(horizontal = fw(20))
            .background(
                Color.White,
                shape= if (firt){
                    RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)}
                else if (end){
                    RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                }
                else RoundedCornerShape(0.dp)
            )
            .clickable(onClick = {
                onClick()
                Log.d("TAG", "Category:$text ")
            }),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(Modifier.width(fw(20)))
        Box(
            Modifier
                .height(fh(40))
                .width(fw(320)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Medium,

            )
        }
        Box(
            Modifier
                .width(fw(20))
                .height(fh(20))
                //.padding(horizontal = fw(15))
            , contentAlignment = Alignment.Center
        ){
            Image(
                painterResource(R.drawable.circle_right),
                null,
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(Modifier.width(fw(15)))
    }
}

@Composable
@Preview
private fun CatalogSubScreenPreview(){
    CatalogSubScreen()
}

