package com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.RegistrationAndLoginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.fortgame.ksynic.mvvm.data.local.LocalDataStore
import com.fortgame.ksynic.mvvm.model.UserProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.TopHeaderWithoutSearch
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw
import android.util.Log

@Composable
fun RegistrationScreen(
    onBackClick: () -> Unit = {},
    onRegistrationSuccess: () -> Unit = {}
) {
    val context = LocalContext.current
    val localDataStore = remember { LocalDataStore(context) }
    
    // Состояния для ввода данных
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneValue by remember { mutableStateOf(TextFieldValue("+7 (", TextRange(4))) }
    
    // Функция форматирования номера телефона с сохранением позиции курсора
    fun formatPhoneNumber(input: TextFieldValue): TextFieldValue {
        val base = "+7 ("
        
        // Извлекаем все цифры из строки
        val allDigits = input.text.filter { it.isDigit() }
        
        // Если нет цифр или только одна "7", возвращаем базовый формат
        if (allDigits.isEmpty() || (allDigits.length == 1 && allDigits == "7")) {
            return TextFieldValue(base, TextRange(base.length))
        }
        
        // Убираем первую 7, если она есть (код страны уже в base)
        val phoneDigits = if (allDigits.startsWith("7") && allDigits.length > 1) {
            allDigits.substring(1).take(10)
        } else {
            allDigits.take(10)
        }
        
        // Если после удаления первой 7 не осталось цифр, возвращаем базовый формат
        if (phoneDigits.isEmpty()) {
            return TextFieldValue(base, TextRange(base.length))
        }
        
        // Форматируем номер
        val formatted = when {
            phoneDigits.length <= 3 -> "$base$phoneDigits"
            phoneDigits.length <= 6 -> "$base${phoneDigits.substring(0, 3)}) ${phoneDigits.substring(3)}"
            phoneDigits.length <= 8 -> "$base${phoneDigits.substring(0, 3)}) ${phoneDigits.substring(3, 6)}-${phoneDigits.substring(6)}"
            else -> "$base${phoneDigits.substring(0, 3)}) ${phoneDigits.substring(3, 6)}-${phoneDigits.substring(6, 8)}-${phoneDigits.substring(8)}"
        }
        
        // Рассчитываем новую позицию курсора
        val oldCursor = input.selection.start
        val oldText = input.text
        
        // Считаем количество цифр до старой позиции курсора (исключая первую "7")
        val digitsBeforeCursor = oldText.substring(0, oldCursor.coerceAtMost(oldText.length)).filter { it.isDigit() }
        // Количество цифр номера телефона (без кода страны 7)
        val digitsCountBefore = when {
            digitsBeforeCursor.isEmpty() -> 0
            digitsBeforeCursor == "7" -> 0
            digitsBeforeCursor.startsWith("7") -> digitsBeforeCursor.length - 1
            else -> digitsBeforeCursor.length
        }
        
        // Находим позицию в отформатированной строке, соответствующую этой цифре
        // base = "+7 (" = 4 символа
        // Формат: "+7 (XXX) XXX-XX-XX"
        val newCursor = when {
            digitsCountBefore <= 0 -> base.length  // 4
            digitsCountBefore <= 3 -> base.length + digitsCountBefore  // 4 + количество цифр (1-3)
            digitsCountBefore <= 6 -> base.length + 3 + 3 + (digitsCountBefore - 3)  // 4 + 3 цифры + ") " (3 символа) + остальные цифры
            digitsCountBefore <= 8 -> base.length + 3 + 3 + 3 + 1 + (digitsCountBefore - 6)  // 4 + 3 + ") " (3) + 3 + "-" (1) + остальные
            else -> base.length + 3 + 3 + 3 + 1 + 2 + 1 + (digitsCountBefore - 8)  // 4 + 3 + ") " (3) + 3 + "-" (1) + 2 + "-" (1) + остальные
        }.coerceIn(0, formatted.length)
        
        return TextFieldValue(formatted, TextRange(newCursor))
    }
    
    // Состояния для кода
    var showCodeField by remember { mutableStateOf(false) }
    var smsCode by remember { mutableStateOf("") }
    var generatedCode by remember { mutableStateOf("") }
    var codeSent by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgGray)

    ) {
        TopHeaderWithoutSearch()

        Column(
            Modifier
                //.height(fh(385))
                .fillMaxWidth()
                .padding(horizontal = fw(25))
                .align(Alignment.Center)
                .background(Color.White, RoundedCornerShape(20.dp))
        ){
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Box(
                    Modifier.padding(horizontal = fw(20))
                ){
                    Text(
                        "Зарегистрироваться",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 22.sp,
                    )
                }

                Box(
                    Modifier
                        .height(fh(70))
                        .width(fw(70)),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painterResource(R.drawable.plus),
                        null
                    )
                }
            }

            Text(
                text="Имя",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Light,
                modifier=Modifier.padding(horizontal = fw(30))
            )
            Spacer(Modifier.height(fh(5)))

            // ИМЯ (редактируемое поле)
            EditableNameBox(
                value = firstName,
                onValueChange = { firstName = it },
                placeholder = "Иван"
            )

            Spacer(Modifier.height(fh(20)))
            Text(
                text="Фамилия",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Light,
                modifier=Modifier.padding(horizontal = fw(30))
            )
            Spacer(Modifier.height(fh(5)))
            // ФАМИЛИЯ (редактируемое поле)
            EditableNameBox(
                value = lastName,
                onValueChange = { lastName = it },
                placeholder = "Иванов"
            )
            Spacer(Modifier.height(fh(20)))

            Text(
                text="Номер телефона",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Light,
                modifier=Modifier.padding(horizontal = fw(30))
            )
            Spacer(Modifier.height(fh(5)))

            // Номер телефона (редактируемое поле)
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(Modifier.width(fw(20)))
                Image(
                    painterResource(R.drawable.russian_flag),
                    null
                )
                EditableNameBoxPhone(
                    value = phoneValue,
                    onValueChange = { newValue ->
                        phoneValue = formatPhoneNumber(newValue)
                    },
                    placeholder = "+7 (000) 000-00-00"
                )
            }
            Spacer(Modifier.height(fh(20)))

            // Анимированное поле для кода
            AnimatedVisibility(
                visible = showCodeField,
                enter = fadeIn(animationSpec = tween(300)) + expandVertically(
                    animationSpec = tween(300)
                )
            ) {
                Column {
                    Text(
                        text="Код из СМС",
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Light,
                        modifier=Modifier.padding(horizontal = fw(30))
                    )
                    Spacer(Modifier.height(fh(5)))
                    EditableNameBox(
                        value = smsCode,
                        onValueChange = { smsCode = it },
                        placeholder = "Введите код",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(Modifier.height(fh(10)))
                }
            }

            // Получить код
            if (!codeSent) {
                Box(
                    Modifier
                        .height(fh(40))
                        .width(fw(200))
                        .padding(horizontal = fw(20))
                        .shadow(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(10.dp),
                            spotColor = Color.Black.copy(alpha = 0.3f)
                        )
                        .background(Color.White)
                        .align(Alignment.End)
                        .clickable(
                            enabled = firstName.isNotBlank() && lastName.isNotBlank() && phoneValue.text.length > 4
                        ) {
                            // Генерируем тестовый код
                            generatedCode = (1000..9999).random().toString()
                            codeSent = true
                            showCodeField = true
                            // Автоматически вставляем код в поле
                            smsCode = generatedCode
                            
                            // Через 2 секунды автоматически переходим в профиль
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(2000)
                                // Сохраняем профиль
                                val profile = UserProfile(
                                    firstName = firstName.trim(),
                                    lastName = lastName.trim(),
                                    phone = phoneValue.text.trim(),
                                    email = "", // Пустое
                                    birthDate = "", // Пустое
                                    gender = "", // Пустое
                                    displayName = "" // Пустое
                                )
                                localDataStore.saveUserProfile(profile)
                                localDataStore.setLoggedIn(true)
                                onRegistrationSuccess()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Получить код по СМС",
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }

                Spacer(Modifier.height(fh(20)))
            }


        }
    }
}


@Composable
fun NameBox(
    text:String="Иван",
){
    Box(
        Modifier
            .height(fh(40))
            .fillMaxWidth()
            .padding(horizontal = fw(20))
            .shadow(
                elevation = 5.dp, // Figma: Blur 5
                shape = RoundedCornerShape(10.dp),
                spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
            )
            .background(Color.White),
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            text,
            fontSize = 20.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Bold,
            color=Color(0xFFA2A2A2),
            modifier=Modifier.padding(horizontal = fw(10))
        )
    }
}

@Composable
fun EditableNameBox(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Log.d("EditableNameBox", "value='$value', length=${value.length}")
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            //.height(fh(40))
            .fillMaxWidth()
            .padding(horizontal = fw(20)),
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFA2A2A2)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(10.dp),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 20.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Bold
        ),
        keyboardOptions = keyboardOptions,
        singleLine = true
    )
}

@Composable
fun EditableNameBoxPhone(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String = ""
) {
    Log.d("EditableNameBoxPhone", "value='${value.text}', cursor=${value.selection.start}")
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = fw(20)),
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFA2A2A2)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(10.dp),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 20.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Bold
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        singleLine = true
    )
}

@Composable
@Preview
private fun RegistrationScreenPreview() {
    RegistrationScreen()
}
