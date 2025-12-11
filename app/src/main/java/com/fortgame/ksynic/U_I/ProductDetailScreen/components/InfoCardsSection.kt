package com.fortgame.ksynic.U_I.ProductDetailScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fortgame.ksynic.R
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw


@Composable
fun InfoCardsSection(
    description: String? = null,
    specifications: List<com.fortgame.ksynic.mvvm.model.ProductSpecification> = emptyList()
){
    var showText by remember { mutableStateOf(false) } // ДОБАВЬТЕ это состояние
    var select by remember { mutableStateOf(true) }

    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(10.dp))
    ){
        Spacer(Modifier.height(fh(10)))
        Row(Modifier
            .height(fh(30))
            .padding(horizontal = fw(10)),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Box(
                Modifier
                    .width(fw(120))
                    .fillMaxHeight()
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    )
                    .background(
                        color = if (select) Color(0xFF5D76CB) else Color.White
                    )
                    .clickable(
                        onClick = if (!select) {
                            { select = !select }
                        } else {
                            {}
                        }
                    ),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text="Описание",
                    fontSize = 16.sp,
                    color= if (select) Color.White else Color.Black,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(
                Modifier.width(fw(10))
            )

            Box(
                Modifier
                    .width(fw(175))
                    .fillMaxHeight()
                    .shadow(
                        elevation = 5.dp, // Figma: Blur 5
                        shape = RoundedCornerShape(10.dp),
                        spotColor = Color.Black.copy(alpha = 0.3f) // Figma: #000000 30%
                    )
                    .background(
                        color = if (!select) Color(0xFF5D76CB) else Color.White
                    )
                    .clickable(
                        onClick = if (select) {
                            { select = !select }
                        } else {
                            {}
                        }
                    ),
                contentAlignment = Alignment.Center

            ){
                Text(
                    text="Характеристики",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 18.sp,
                    color= if (!select) Color.White else Color.Black
                )
            }
        }
        Spacer (Modifier.height(fh(10)))

        if (select)
            Info(showText, description, onShowTextClick = { showText = true })
        else
            CharacteristicsColumn(specifications = specifications)

    }
}


@Composable
private fun Info(
    showText: Boolean = false,
    description: String? = null,
    onShowTextClick: () -> Unit
){
    val maxHeight = fh(160)
    val paddingDp = fh(10) // Вычисляем padding до использования в with(density)
    val bottomPaddingDp = fh(10) // Отступ снизу
    val density = LocalDensity.current
    var shouldShowExpandButton by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (!showText && shouldShowExpandButton) {
                    // Если текст большой и не развернут - фиксированная высота с обрезкой
                    Modifier
                        .height(maxHeight)
                        .clipToBounds() // Обрезаем содержимое, которое выходит за границы
                } else {
                    // Если текст маленький или развернут - без ограничения высоты
                    Modifier
                }
            )
            .then(
                // Добавляем отступ снизу когда текст развернут или когда кнопки нет
                if (showText || (!showText && !shouldShowExpandButton)) {
                    Modifier.padding(bottom = bottomPaddingDp)
                } else {
                    Modifier
                }
            )
    ){
        // Скрытый Text для измерения полной высоты текста (без ограничения по высоте)
        if (!showText) {
            Text(
                text = description ?: "Описание товара отсутствует",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 14.sp,
                modifier = Modifier
                    .padding(horizontal = fw(10))
                    .fillMaxWidth()
                    .alpha(0f), // Скрытый для измерения
                onTextLayout = { textLayoutResult: TextLayoutResult ->
                    // Получаем полную высоту текста в пикселях
                    val fullTextHeightPx = textLayoutResult.size.height.toFloat()
                    
                    // Конвертируем maxHeight и padding из dp в пиксели
                    val maxHeightPx = with(density) { maxHeight.toPx() }
                    val paddingPx = with(density) { paddingDp.toPx() }
                    val availableHeightPx = maxHeightPx - paddingPx
                    
                    // Проверяем, больше ли текст чем доступная высота
                    shouldShowExpandButton = fullTextHeightPx > availableHeightPx
                }
            )
        }
        
        // Видимый Text для отображения
        Text(
            text = description ?: "Описание товара отсутствует",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 14.sp,
            modifier = Modifier
                .padding(horizontal = fw(10))
        )
        if (!showText && shouldShowExpandButton){
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(fh(50))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.White.copy(alpha = 0.8f), Color(0xFF5D76CB))
                        )
                    )
                    .align(Alignment.BottomCenter)
                    .clickable(onClick = onShowTextClick),
                contentAlignment = Alignment.CenterEnd

            ){
                Row(
                    Modifier
                        .height(fh(30))
                        .fillMaxWidth()
                        .padding(horizontal = fw(10))
                ){
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .width(fw(390)),
                        contentAlignment = Alignment.CenterEnd
                    ){
                        Text(
                            text="Развернуть",
                            fontSize = 18.sp,
                            color=Color.White,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 18.sp,
                        )
                    }
                    Spacer(Modifier.width(fw(10)))
                    Box(
                        Modifier.height(fh(20)).width(fw(20)).align(Alignment.CenterVertically),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.down),
                            null,
                            //Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CharacteristicsColumn(
    specifications: List<com.fortgame.ksynic.mvvm.model.ProductSpecification>
){
    Column (){
        if (specifications.isNotEmpty()) {
            specifications.forEachIndexed { index, specification ->
                Characteristic(
                    name = specification.name,
                    value = specification.value
                )
                // Добавляем разделитель между характеристиками, кроме последней
                if (index < specifications.size - 1) {
                    Box(Modifier
                        .height(fh(2))
                        .fillMaxWidth()
                        .background(Color(0xFFD9D9D9)))
                }
            }
        } else {
            // Если характеристик нет, показываем сообщение
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = fw(10), vertical = fh(20)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Характеристики не указаны",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
        Spacer(Modifier.height(fh(10)))
    }
}

@Preview
@Composable
private fun Characteristic(
    name: String = "name",
    value: String = "value"
){
    Row(
        Modifier
            .height(fh(30))
            .fillMaxWidth()
            .padding(horizontal = fw(10))
            .background(Color.White)
    ){

        Box(
            Modifier
                .fillMaxHeight()
                .width(fw(120)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                name,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(Modifier.width(fw(10)))

        Box(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                value,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}


@Composable
@Preview
private fun InfoCardsSectionPreview(){
    InfoCardsSection()
}