package com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.mvvm.model.UserProfile
import com.fortgame.ksynic.theme.lowWhite
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun DataBlock(
    profile: UserProfile,
    onProfileUpdate: (UserProfile) -> Unit
){
    // Объявляем переменные состояния в начале функции
    var showEditFirstNameDialog by remember { mutableStateOf(false) }
    var showEditLastNameDialog by remember { mutableStateOf(false) }
    var showEditGenderDialog by remember { mutableStateOf(false) }
    var showEditBirthDateDialog by remember { mutableStateOf(false) }
    var showEditPhoneDialog by remember { mutableStateOf(false) }
    var showEditEmailDialog by remember { mutableStateOf(false) }
    
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(10.dp)),
    ){
        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(30))
                .padding(horizontal = fw(20)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text="Ваши данные",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp
            )
        }
        Spacer(Modifier.height(fh(10)))

        // Имя
        Text(
            "Имя",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        EditableFieldBox(
            value = profile.firstName.ifEmpty { "Денис" },
            onClick = { showEditFirstNameDialog = true }
        )

        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(2))
                .padding(horizontal = fw(20))
                .background(lowWhite)
        )
        Spacer(Modifier.height(fh(8)))

        // Фамилия
        Text(
            "Фамилия",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        EditableFieldBox(
            value = if (profile.lastName.isNotEmpty()) profile.lastName else "",
            onClick = { showEditLastNameDialog = true },
            isEmpty = profile.lastName.isEmpty()
        )

        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(2))
                .padding(horizontal = fw(20))
                .background(lowWhite)

        )
        Spacer(Modifier.height(fh(8)))

        // Пол
        Text(
            "Пол",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        EditableFieldBox(
            value = if (profile.gender.isNotEmpty()) profile.gender else "",
            onClick = { showEditGenderDialog = true },
            isEmpty = profile.gender.isEmpty()
        )

        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(2))
                .padding(horizontal = fw(20))
                .background(lowWhite)

        )
        Spacer(Modifier.height(fh(8)))

        // Дата Рождения
        Text(
            "Дата рождения",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        EditableFieldBox(
            value = if (profile.birthDate.isNotEmpty()) profile.birthDate else "",
            onClick = { showEditBirthDateDialog = true },
            isEmpty = profile.birthDate.isEmpty()
        )

        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(2))
                .padding(horizontal = fw(20))
                .background(lowWhite)

        )
        Spacer(Modifier.height(fh(8)))

        // Номер телефона
        Text(
            "Номер телефона",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        EditableFieldBox(
            value = if (profile.phone.isNotEmpty()) profile.phone else "",
            onClick = { showEditPhoneDialog = true },
            isEmpty = profile.phone.isEmpty()
        )

        Spacer(Modifier.height(fh(10)))
        Box(
            Modifier
                .fillMaxWidth()
                .height(fh(2))
                .padding(horizontal = fw(20))
                .background(lowWhite)

        )
        Spacer(Modifier.height(fh(8)))

        // Почта
        Text(
            "Почта",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 14.sp,
            modifier = Modifier.padding(horizontal = fw(20))
        )
        EditableFieldBox(
            value = if (profile.email.isNotEmpty()) profile.email else "",
            onClick = { showEditEmailDialog = true },
            isEmpty = profile.email.isEmpty()
        )
        Spacer(Modifier.height(fh(15)))

    }
    
    // Диалоги редактирования
    if (showEditFirstNameDialog) {
        EditFieldDialog(
            title = "Имя",
            currentValue = profile.firstName,
            onDismiss = { showEditFirstNameDialog = false },
            onSave = { newValue ->
                onProfileUpdate(profile.copy(firstName = newValue))
            }
        )
    }
    
    if (showEditLastNameDialog) {
        EditFieldDialog(
            title = "Фамилия",
            currentValue = profile.lastName,
            onDismiss = { showEditLastNameDialog = false },
            onSave = { newValue ->
                onProfileUpdate(profile.copy(lastName = newValue))
            }
        )
    }
    
    if (showEditGenderDialog) {
        EditFieldDialog(
            title = "Пол",
            currentValue = profile.gender,
            onDismiss = { showEditGenderDialog = false },
            onSave = { newValue ->
                onProfileUpdate(profile.copy(gender = newValue))
            }
        )
    }
    
    if (showEditBirthDateDialog) {
        EditFieldDialog(
            title = "Дата рождения",
            currentValue = profile.birthDate,
            placeholder = "DD.MM.YYYY",
            onDismiss = { showEditBirthDateDialog = false },
            onSave = { newValue ->
                onProfileUpdate(profile.copy(birthDate = newValue))
            }
        )
    }
    
    if (showEditPhoneDialog) {
        EditFieldDialog(
            title = "Номер телефона",
            currentValue = profile.phone,
            keyboardType = KeyboardType.Phone,
            onDismiss = { showEditPhoneDialog = false },
            onSave = { newValue ->
                onProfileUpdate(profile.copy(phone = newValue))
            }
        )
    }
    
    if (showEditEmailDialog) {
        EditFieldDialog(
            title = "Почта",
            currentValue = profile.email,
            keyboardType = KeyboardType.Email,
            onDismiss = { showEditEmailDialog = false },
            onSave = { newValue ->
                onProfileUpdate(profile.copy(email = newValue))
            }
        )
    }
}

@Composable
private fun EditableFieldBox(
    value: String,
    onClick: () -> Unit,
    isEmpty: Boolean = false
) {
    Text(
        text = if (isEmpty) "" else value,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        color = if (isEmpty) Color(0xFFA2A2A2) else Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = fw(20))
    )
}

@Composable
@Preview
private fun DataBlockPreview(){
    DataBlock(
        profile = com.fortgame.ksynic.mvvm.model.UserProfile.default(),
        onProfileUpdate = {}
    )
}