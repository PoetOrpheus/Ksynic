package com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fortgame.ksynic.R
import com.fortgame.ksynic.U_I.ProductDetailScreen.BlueButton
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw
import android.util.Log
import androidx.compose.runtime.LaunchedEffect

/**
 * Диалог для редактирования поля профиля
 * Точная вёрстка по макету Figma - прибит к низу экрана
 */
@Composable
fun EditFieldDialog(
    title: String,
    currentValue: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var textValue by remember { mutableStateOf(currentValue) }

    LaunchedEffect(currentValue) {
        Log.d("EditFieldDialog", "EditFieldDialog открыт: title='$title', currentValue='$currentValue', length=${currentValue.length}")
        textValue = currentValue
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false, // Отключаем закрытие по клику вне диалога
            usePlatformDefaultWidth = false // <--- ДОБАВИТЬ ЭТУ СТРОКУ
        )
    ) {
        // Box для позиционирования диалога внизу экрана на всю ширину
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            // Диалог прибит к низу на всю ширину
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(fh(245))
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                    ),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = fw(40))
                ) {

                    Spacer(modifier = Modifier.height(fh(40)))

                    // Заголовок
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light,
                        lineHeight = 20.sp,
                        color = Color.Black,
                    )

                    Spacer(modifier = Modifier.height(fh(15)))

                    // Поле ввода
                    TextField(
                        value = textValue,
                        onValueChange = { textValue = it },
                        placeholder = {
                            if (placeholder.isNotEmpty()) {
                                Text(
                                    text = placeholder,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Gray
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 5.dp, // Figma: Blur 5
                                shape = RoundedCornerShape(10.dp),
                                spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                            )
                            .background(Color.White),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            cursorColor = Color.Black
                        ),
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 22.sp,
                            color = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(fh(40)))

                    // Кнопка "Сохранить"
                    Button(
                        onClick = {
                            onSave(textValue)
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BlueButton
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(fh(40)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Сохранить",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 18.sp,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(fh(40)))
                }

                // Кнопка закрытия (X) 70x70px в правом верхнем углу
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(fw(70), fh(70))
                        .clickable { onDismiss() },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(R.drawable.plus),
                        null
                    )
                }
            }
        }
    }
}


@Composable
@Preview
private fun EditFieldDialogPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(fh(400))
            .background(Color.Gray.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        EditFieldDialog(
            title = "Видимое имя на площадке",
            currentValue = "Денис Д.",
            onDismiss = {},
            onSave = {}
        )
    }
}
