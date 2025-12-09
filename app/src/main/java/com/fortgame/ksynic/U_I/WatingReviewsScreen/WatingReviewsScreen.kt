package com.fortgame.ksynic.U_I.WatingReviewsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.TopHeaderWithReturn
import com.fortgame.ksynic.U_I.WatingReviewsScreen.components.MyReviewBlock
import com.fortgame.ksynic.U_I.WatingReviewsScreen.components.ReviewBlock
import com.fortgame.ksynic.U_I.WatingReviewsScreen.components.SwitchBlock
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw

@Composable
fun WatingReviewScreen(
    onBackClick:()->Unit= {}
){
    var isSelect by remember { mutableStateOf(true) }

    Box(modifier = Modifier
        .background(BgGray)) {
        TopHeaderWithReturn(onBackClick)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = fw(15),
                    end = fw(5),
                    top = fh(65 )),
        ) {
            item{
                SwitchBlock(isSelect, { isSelect = !isSelect})
            }
            item{
                Spacer(Modifier.height(fh(10)))

               if (isSelect) ReviewBlock() else MyReviewBlock()
            }
        }
    }
}

@Composable
@Preview
private fun WatingReviewScreenPreview(){
    WatingReviewScreen()
}
