package com.fortgame.ksynic.U_I.Screen.MainScreen.SubScreen.CatalogScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.TopHeaderWithReturn
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

// Названия категорий (соответствуют индексам category_1, category_2...)
private val categoryNames = listOf(
    "Мужская одежда", "Женская одежда", "Детская одежда", "Обувь",
    "Аксессуары", "Сумки", "Часы", "Ювелирные изделия",
    "Косметика", "Парфюмерия", "Спорт", "Электроника",
    "Техника", "Мебель", "Дом и сад", "Автотовары",
    "Книги", "Игрушки", "Продукты", "Другое"
)

@Composable
fun CatalogScreen(
    onBackClick:()->Unit = {},
    onCategoryClick: (String) -> Unit = {},
){
    val context = LocalContext.current
    Box(modifier = Modifier
        .background(BgGray)) {
        TopHeaderWithReturn(onBackClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = fw(5),
                    end = fw(5),
                    top = fh(60)
                ),
        ) {
            Spacer(Modifier.height(fh(10)))

            // Продукты
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = fw(20), vertical = fh(20)),
                horizontalArrangement = Arrangement.spacedBy(fw(25)),
                verticalArrangement = Arrangement.spacedBy(fh(20)),
                modifier = Modifier.fillMaxSize()
            ) {
                items(20){ index ->
                    val imageIndex = index + 1
                    val drawableId = remember(imageIndex) {
                        context.resources.getIdentifier(
                            "category_$imageIndex", // Имя файла: category_1, category_2...
                            "drawable",             // Тип ресурса
                            context.packageName     // Пакет приложения
                        )
                    }

                    if (drawableId != 0) {
                        val categoryName = if (index < categoryNames.size) categoryNames[index] else "Категория ${index + 1}"
                        CatalogBox(
                            painter = drawableId,
                            onClick = { onCategoryClick(categoryName) }
                        )
                    }
                }


            }
        }
    }
}

@Composable
private fun CatalogBox(
    painter: Int,
    onClick: () -> Unit = {}
) {
    Image(
        painterResource(painter),
        null,
        modifier=Modifier
            .width(fw(120))
            .height(fh(120))
            .clickable(onClick = onClick)
    )
}

@Composable
@Preview
private fun CatalogScreenPreview(){
    CatalogScreen()
}