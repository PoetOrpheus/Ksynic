package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.ProductDetailScreen.BlueButton
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

/**
 * Форматирует количество отзывов для отображения (например, 1337 -> "1 337 отзыв")
 */
private fun formatReviewsCount(count: Int): String {
    val formattedCount = String.format("%,d", count).replace(",", " ")
    return when {
        count % 10 == 1 && count % 100 != 11 -> "$formattedCount отзыв"
        count % 10 in 2..4 && count % 100 !in 12..14 -> "$formattedCount отзыва"
        else -> "$formattedCount отзывов"
    }
}

@Composable
fun StartCardRow(
    rating: Double = 4.9,
    reviewsCount: Int = 1337
) {

    // Статистика (Рейтинг, Вопросы, Фото)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(fh(80))
            .background(Color.White, RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Отзывы
        Column(Modifier
            .width(fw(161))
            .height(fh(60))

            .shadow(
                elevation = 5.dp, // Figma: Blur 5
                shape = RoundedCornerShape(10.dp),
                spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
            )
            .background(Color.White),

        ){
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ){
                Image(
                    painterResource(R.drawable.star_profile_menu),
                    null,
                    Modifier
                        .width(fw(22))
                        .height(fh(22))

                )
                Spacer(Modifier.width(fw(10)))
                Box(
                    Modifier
                        .width(fw(70))
                        .height(fh(35)),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = String.format("%.1f", rating),
                        fontSize =16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = formatReviewsCount(reviewsCount),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 18.sp,

                )
            }
        }

        Spacer(Modifier.width(fw(10)))

        // Вопросы
        Column(Modifier
            .width(fw(90))
            .height(fh(60))
            .shadow(
                elevation = 5.dp, // Figma: Blur 5
                shape = RoundedCornerShape(10.dp),
                spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
            )
            .background(Color.White),

            ){
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ){
                Image(
                    painterResource(R.drawable.question),
                    null,
                    Modifier
                        .width(fw(14))
                        .height(fh(22))

                )
                Spacer(Modifier.width(fw(10)))
                Box(
                    Modifier
                        .width(fw(40))
                        .height(fh(35)),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text="19",
                        fontSize =12.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text="вопросов",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,

                    )
            }
        }
        Spacer(Modifier.width(fw(10)))

        // Фотки
        PicturesBlock()
    }
}


@Composable
private fun PicturesBlock(
    count: Int = 6
) {
    if (count == 1) {
        Box(){
        Image(
            painterResource(R.drawable.watch_1),
            null,
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .width(fw(60))
                .height(fh(60))
                .shadow(
                    elevation = 5.dp, // Figma: Blur 5
                    shape = RoundedCornerShape(10.dp),
                    spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                ),

        )
        }
    } else if (count==2){
        Row(
            Modifier
                .width(fw(125))
                .height(fh(60))
        ){
            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
            Spacer(Modifier.width(fw(5)))

            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
        }
    }
    else if (count == 3){
        Box(
            Modifier
                .width(fw(138))
                .height(fh(60))
        ){
            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .padding(start = fw(39))
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .padding(start = fw(78))
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
        }
    }

    else if (count==4){
        Box(
            Modifier
                .width(fw(138))
                .height(fh(60))
        ){
            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .padding(start = fw(26))
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .padding(start = fw(52))
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .padding(start = fw(78))
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
        }
    }
    else if (count>4){
        Box(
            Modifier
                .width(fw(138))
                .height(fh(60))
        ){
            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .padding(start = fw(26))
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
            Image(
                painterResource(R.drawable.watch_1),
                null,

                modifier = Modifier
                    .padding(start = fw(52))
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .padding(start = fw(78))
                    .width(fw(60))
                    .height(fh(60))
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painterResource(R.drawable.watch_1),
                    null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(Color(0xBFF2F2F2).copy(alpha = 0.7f),
                        BlendMode.SrcOver),
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "+ ${count - 1}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
@Preview
private fun StartCardRowPreview(){
    Box(
        Modifier.background(Color.White)
    ){
    StartCardRow()
}}