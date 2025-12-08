package com.fortgame.ksynic.U_I

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
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
fun TopHeaderWithReturn() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF5D76CB), Color(0xFFFCB4D5)),
                    start = Offset(0f, 0f),  // Левый верхний
                    end = Offset(600f, 600f), // Правый нижний (или используйте Offset.Infinite)
                ),
                RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                )
            )
            .padding(horizontal = fw(10)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier=Modifier
                .width(fw(30))
                .height(fh(30)),
            contentAlignment = Alignment.Center

        ){
            Image(
                painterResource(R.drawable.return_icon),
                null
            )
        }
        Row(
            modifier=Modifier
                .padding(horizontal = fw(15), vertical = fh(10))
        ){
            IconHeader(R.drawable.question_header_section)
            Spacer(Modifier.width(fw(15)))
            IconHeader(R.drawable.share)
            Spacer(Modifier.width(fw(15)))
            IconHeader(R.drawable.lover_for_header_section)
        }
    }
}

@Composable
private fun IconHeader(
    image: Int=R.drawable.share
) {

    Box(
        modifier = Modifier
            .width(fw(35))
            .height(fh(35))
            .background(Color.White, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(image),
            null
        )
    }
}



@Composable
@Preview
private fun TopHeaderWithReturnPreview() {
    TopHeaderWithReturn()
   // IconHeader()
}
