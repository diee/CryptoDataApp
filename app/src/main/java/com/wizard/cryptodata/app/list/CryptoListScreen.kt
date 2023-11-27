package com.wizard.cryptodata.app.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wizard.cryptodata.app.Screens

@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel: CryptoListViewModel = hiltViewModel()
) {

    val state = viewModel.uiState.value

    LazyColumn {
        items(state.cryptos) { crypto ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screens.CryptoDetailScreen.withArgs(crypto.name))
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = crypto.name)
                Text(text = crypto.price.toString())
            }
        }
    }
}