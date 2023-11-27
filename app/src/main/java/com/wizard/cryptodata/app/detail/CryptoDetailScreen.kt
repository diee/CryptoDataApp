package com.wizard.cryptodata.app.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wizard.cryptodata.app.Screens

@Composable
fun CryptoDetailScreen(
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {

    val state = viewModel.uiState.value

    Scaffold(topBar = {
        TopAppBar {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
            Text(text = state.crypto.name ?: "")
        }
    }) {
        Column {
            Text(text = state.crypto.name ?: "")
            Button(onClick = { navController.navigate(Screens.ExtraScreen.route) }) {
                Text(text = "Navigate")
            }
        }
    }
}