package com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.RegistrationAndLoginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.TopHeaderWithReturn
import com.fortgame.ksynic.U_I.TopHeaderWithoutSearch
import com.fortgame.ksynic.theme.BlueGradient
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun ChoiceLogOrRegisterScreen(
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(BgGray)
    ) {
        TopHeaderWithoutSearch()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = fw(5),
                    end = fw(5),
                    top = fh(60)
                ),
            contentAlignment = Alignment.CenterEnd
        ) {
            Column(
                Modifier
                    .height(fh(155))
                    .width(fw(250))
                    .background(
                        Color.White,
                        RoundedCornerShape(topStart = 25.dp, bottomStart = 25.dp)
                    )
                    .padding(horizontal = fw(25)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(fh(25)))
                Box(
                    Modifier
                        .height(fh(40))
                        .width(fw(120))
                        .align(Alignment.End)
                        .shadow(
                            elevation = 5.dp, // Figma: Blur 5
                            shape = RoundedCornerShape(10.dp),
                            spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                        )
                        .background(Color.White)
                        .clickable(onClick = onLoginClick),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Вход",
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(Modifier.height(fw(25)))

                Box(
                    Modifier
                        .height(fh(40))
                        .fillMaxWidth()
                        .shadow(
                            elevation = 5.dp, // Figma: Blur 5
                            shape = RoundedCornerShape(10.dp),
                            spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                        )
                        .background(Color.White)
                        .clickable(onClick = onRegisterClick),
                    contentAlignment = Alignment.Center

                ) {
                    Text(
                        "Регистрация",
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        color = BlueGradient,
                        fontWeight = FontWeight.Bold,
                        )
                }
            }
        }
    }
}
@Composable
@Preview
private fun ChoiceLogOrRegisterScreenPreview(){
    ChoiceLogOrRegisterScreen()
}