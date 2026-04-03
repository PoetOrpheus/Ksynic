package com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen.components.DataBlock
import com.fortgame.ksynic.U_I.Screen.ProfileScreen.SubScreen.EditingProfileScreen.components.PhotoAndNameBlock
import com.fortgame.ksynic.U_I.ProductDetailScreen.BgGray
import com.fortgame.ksynic.U_I.TopHeaderWithReturn
import com.fortgame.ksynic.mvvm.viewmodel.UserProfileViewModel
import com.fortgame.ksynic.mvvm.viewmodel.ViewModelFactory
import com.fortgame.ksynic.utils.fh
import com.fortgame.ksynic.utils.fw
import android.util.Log

@Composable
fun EditingProfileScreen(
    onBackClick: () -> Unit = {},
    viewModel: UserProfileViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    val profileState by viewModel.profileState.collectAsState()
    val profile = profileState ?: com.fortgame.ksynic.mvvm.model.UserProfile.default()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    LaunchedEffect(profile) {
        Log.d("EditingProfileScreen", "Профиль загружен: firstName='${profile.firstName}', lastName='${profile.lastName}', phone='${profile.phone}', email='${profile.email}', gender='${profile.gender}', displayName='${profile.displayName}'")
    }
    Box(
        modifier = Modifier
            .background(BgGray)
    ) {
        TopHeaderWithReturn(onBackClick)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                 .padding(start = fw(5), end = fw(5), top = fh(60)),
        ) {
            item {
                PhotoAndNameBlock(
                    profile = profile,
                    onProfileUpdate = { updatedProfile ->
                        viewModel.updateProfile(updatedProfile)
                    }
                )
            }
            item { Spacer(Modifier.height(fh(10))) }

            item {
                DataBlock(
                    profile = profile,
                    onProfileUpdate = { updatedProfile ->
                        viewModel.updateProfile(updatedProfile)
                    }
                )
            }

        }
    }
}

@Composable
@Preview
private fun EditingProfileScreenPreview() {
    EditingProfileScreen()
}