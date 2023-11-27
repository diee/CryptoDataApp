package com.wizard.cryptodata.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ExtraScreen(viewModel: ExtraScreenViewModel = hiltViewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            viewModel.addNew()
        }) {
            Text(text = "Magic add")
        }
    }
}