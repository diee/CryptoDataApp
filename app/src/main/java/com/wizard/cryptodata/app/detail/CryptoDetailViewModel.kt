package com.wizard.cryptodata.app.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizard.cryptodata.domain.CryptoDetail
import com.wizard.cryptodata.domain.GetCryptoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val getCryptoDetailUseCase: GetCryptoDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val cryptoName = savedStateHandle.get<String>("crypto") ?: ""

    private var _uiState = mutableStateOf(CryptoDetailState(isLoading = true))
    val uiState: State<CryptoDetailState> = _uiState

    init {
        viewModelScope.launch {
            getCryptoDetailUseCase(cryptoName).let {
                _uiState.value = CryptoDetailState(crypto = it)
            }
        }
    }

    data class CryptoDetailState(
        val isLoading: Boolean = false,
        val crypto: CryptoDetail = CryptoDetail(),
        val error: String = ""
    )
}