package com.fortgame.ksynic.U_I.Screen.ProfileScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fortgame.ksynic.R
import com.fortgame.ksynic.mvvm.viewmodel.UserProfileViewModel
import com.fortgame.ksynic.mvvm.viewmodel.ViewModelFactory
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun Profile(
    onEditingClick: () -> Unit = {},
    viewModel: UserProfileViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    val profileState by viewModel.profileState.collectAsState()
    val profile = profileState ?: com.fortgame.ksynic.mvvm.model.UserProfile.default()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(fh(100))
            .padding(horizontal = fh(15))
    ){
        // Блок с информацией
        Row(
            modifier = Modifier
                .width(fw(340))
                .fillMaxHeight()
                .background(Color.White, RoundedCornerShape(10.dp))
        ){
            Image(
                painter = if (profile.avatarRes != null) {
                    painterResource(profile.avatarRes)
                } else {
                    painterResource(R.drawable.ava_denis)
                },
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(fw(110))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = fw(20))
            ) {
                // Имя  и Фамилия
                Box(
                    modifier = Modifier.height(fh(35)),
                    contentAlignment = Alignment.Center
                ){
                    ProfileInfo(
                        text = profile.getShortName(),
                        size=20.sp,
                        lineHeight = 22.sp,
                        weight=FontWeight.SemiBold
                        )
                }

                // Номер телефона
                ProfileInfo(text = profile.phone.ifEmpty { "+7 777 777 77 77" })

                // Почта
                ProfileInfo(text = profile.email.ifEmpty { "asdasdf@mail.ru" })

                // Редактировать
                ProfileInfo(text="Редактировать", color=Color(0xFF5D76CB), onClick=onEditingClick)
            }
        }
        Spacer(Modifier.width(fw(10)))

        // Блок с выходом
        Column(
            modifier = Modifier
                //.fillMaxHeight()
                //.width(fw(50))
                .fillMaxSize()
                .background(Color(0xFF5D76CB),RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(R.drawable.exit_from_profile),
                contentDescription = null,
            )
            Box(
                modifier=Modifier
                    .height(fh(50))
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text="Выйти из аккаунта",
                    fontSize=8.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color=Color.White,
                    lineHeight = 10.sp

                )
            }
        }
    }
}

@Composable
fun ProfileInfo(
    text:String,
    size: TextUnit = 10.sp,
    lineHeight: TextUnit=12.sp,
    weight: FontWeight= FontWeight.Light,
    color:Color = Color.Black,
    onClick: () -> Unit = {}
){
    Text(
        text=text,
        fontSize = size,
        fontWeight = weight,
        color=color,
        lineHeight=lineHeight,
        modifier=Modifier.clickable(onClick=onClick)
    )
}

@Composable
@Preview
private fun ProfilePreview(){
    Profile()
}