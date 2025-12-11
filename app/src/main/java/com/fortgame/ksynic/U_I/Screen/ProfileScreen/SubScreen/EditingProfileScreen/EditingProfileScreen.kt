package com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen.components.DataBlock
import com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen.components.PhotoAndNameBlock
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.TopHeaderWithReturn
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun EditingProfileScreen(
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(BgGray)
    ) {
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
            PhotoAndNameBlock()
            Spacer(Modifier.height(fh(10)))
            DataBlock()

        }
    }
}

@Composable
@Preview
private fun EditingProfileScreenPreview() {
    EditingProfileScreen()
}