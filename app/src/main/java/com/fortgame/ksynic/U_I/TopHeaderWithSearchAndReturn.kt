package com.fortgame.ksynic.U_I

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.res.stringResource
import com.fortgame.ksynic.R
import com.fortgame.ksynic.theme.BlueGradient
import com.fortgame.ksynic.theme.PinkGradient
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun TopHeaderWithSearchAndReturn(
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Log.d("TopHeaderSearch", "TopHeaderWithSearchAndReturn: searchQuery = '$searchQuery', length = ${searchQuery.length}")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(fh(100))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(BlueGradient, PinkGradient),
                    start = Offset(0f, 0f),  // Левый верхний
                    end = Offset(600f, 600f), // Правый нижний (или используйте Offset.Infinite)
                ),
                RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                )
            )
    ) {
        Column(
            modifier = Modifier.padding(
                start = fw(15),
                end = fw(15),
                top = fh(10)
            )
        ) {
            // Строка адреса
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "ПВЗ: ул. Королева, 5",      //TODO: убрать статическую, мы должны будем получать по координатам адрес
                    //font=Font.Inter,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                // Иконка сообщений
                Image(
                    painter = painterResource(R.drawable.message_without_notification),
                    contentDescription = "Месседжер",
                    modifier = Modifier
                        .width(fw(40))
                        .height(fh(30))
                    //.padding(top = fh(10), end = fw(15))
                )

            }
            // Отступ 10px вниз от иконки чата до поисковой строки
            Spacer(modifier = Modifier.height(fh(10)))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.return_icon),
                    null,
                    Modifier.clickable(onClick = onBackClick)
                )
                Spacer(Modifier.width(fw(15)))
                // Поисковая строка с TextField
                Box(
                    modifier = Modifier
                        .width(fw(385))
                        .height(fh(40))
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Поиск",
                            tint = Color.Black,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        BasicTextField(
                            value = searchQuery,
                            onValueChange = { newValue ->
                                Log.d("TopHeaderSearch", "BasicTextField onValueChange: oldValue = '$searchQuery', newValue = '$newValue', newLength = ${newValue.length}")
                                Log.d("TopHeaderSearch", "BasicTextField onValueChange: вызываю onSearchQueryChange")
                                onSearchQueryChange(newValue)
                                Log.d("TopHeaderSearch", "BasicTextField onValueChange: callback вызван")
                            },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                lineHeight = 20.sp
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default,
                            cursorBrush = androidx.compose.ui.graphics.SolidColor(Color.Black),
                            decorationBox = { innerTextField ->
                                Log.d("TopHeaderSearch", "decorationBox: searchQuery = '$searchQuery', isEmpty = ${searchQuery.isEmpty()}")
                                Box(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    if (searchQuery.isEmpty()) {
                                        Text(
                                            stringResource(R.string.search),
                                            color = Color(0xFF6E6E6E),
                                            fontSize = 16.sp,
                                            modifier = Modifier.align(Alignment.CenterStart)
                                        )
                                    } else {
                                        // Логируем, если текст не пустой
                                        Log.d("TopHeaderSearch", "decorationBox: отображаем текст '$searchQuery'")
                                    }
                                    // Выравниваем текст по центру вертикально
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.CenterStart)
                                    ) {
                                        innerTextField()
                                    }
                                    // Дополнительный текст для проверки отображения
                                    if (searchQuery.isNotEmpty()) {
                                        Log.d("TopHeaderSearch", "decorationBox: innerTextField должен показать '$searchQuery'")
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun TopHeaderWithSearchAndReturnPreview(){
    TopHeaderWithSearchAndReturn()

}