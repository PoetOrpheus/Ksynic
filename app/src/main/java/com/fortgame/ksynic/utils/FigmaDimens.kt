package com.fortgame.ksynic.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Базовый размер листа в Figma: 450 x 900
private const val FIGMA_WIDTH = 450f
private const val FIGMA_HEIGHT = 900f

/**
 * Преобразует ширину из макета Figma (450px) в dp текущего экрана.
 *
 * Пример:
 *  - в Figma блок шириной 450px → fw(450f) даст ширину = ширине экрана
 *  - если блок 220px → fw(220f) вернёт пропорциональную ширину под устройство
 */
@Composable
fun fw(px: Float): Dp {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val k = screenWidthDp / FIGMA_WIDTH
    return (px * k).dp
}

/**
 * Преобразует высоту из макета Figma (900px) в dp текущего экрана.
 *
 * Используется так же, как [fw], только для вертикальных размеров.
 */
@Composable
fun fh(px: Float): Dp {
    val screenHeightDp = LocalConfiguration.current.screenHeightDp
    val k = screenHeightDp / FIGMA_HEIGHT
    return (px * k).dp
}

// Перегрузки под Int, чтобы можно было писать fw(220) без суффикса f
@Composable
fun fw(px: Int): Dp = fw(px.toFloat())

@Composable
fun fh(px: Int): Dp = fh(px.toFloat())


