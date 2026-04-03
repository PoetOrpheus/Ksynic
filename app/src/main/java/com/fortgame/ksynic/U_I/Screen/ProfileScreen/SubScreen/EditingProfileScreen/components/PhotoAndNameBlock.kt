package com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.fortgame.ksynic.mvvm.model.UserProfile
import com.fortgame.ksynic.theme.BlueGradient
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun PhotoAndNameBlock(
    profile: UserProfile,
    onProfileUpdate: (UserProfile) -> Unit
){
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
                painter = if (profile.avatarRes != null) {
                    painterResource(profile.avatarRes)
                } else {
                    painterResource(R.drawable.ava_denis)
                },
                contentDescription = null,
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
                    color = BlueGradient
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
        var showEditDialog by remember { mutableStateOf(false) }
        
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = fw(20))
                .shadow(
                    elevation = 5.dp, // Figma: Blur 5
                    shape = RoundedCornerShape(10.dp),
                    spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                )
                .background(Color.White)
                .clickable { showEditDialog = true },
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text = if (profile.displayName.isNotEmpty()) profile.displayName else "",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 17.sp,
                color = if (profile.displayName.isNotEmpty()) Color.Black else Color(0xFFA2A2A2),
                modifier = Modifier.padding(horizontal = fw(15))
            )
        }
        
        if (showEditDialog) {
            EditFieldDialog(
                title = "Видимое имя на площадке",
                currentValue = profile.displayName,
                onDismiss = { showEditDialog = false },
                onSave = { newValue ->
                    onProfileUpdate(profile.copy(displayName = newValue))
                }
            )
        }

    }
}

@Composable
@Preview
private fun PhotoAndNameBlockPreview(){
    PhotoAndNameBlock(
        profile = com.fortgame.ksynic.mvvm.model.UserProfile.default(),
        onProfileUpdate = {}
    )
}