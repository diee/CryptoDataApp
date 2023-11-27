package com.wizard.cryptodata.app.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizard.cryptodata.domain.Crypto
import com.wizard.cryptodata.domain.GetCryptosFlowUseCase
import com.wizard.cryptodata.domain.GetCryptosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    getCryptosUseCase: GetCryptosUseCase,
    getCryptosFlowUseCase: GetCryptosFlowUseCase
) : ViewModel() {

    private var _uiState = mutableStateOf(CryptoListState())
    val uiState: State<CryptoListState> = _uiState

    init {
        viewModelScope.launch {
            /*getCryptosUseCase().let {
                _uiState.value = CryptoListState(cryptos = it)
            }*/

            getCryptosFlowUseCase().collect{
                _uiState.value = CryptoListState(cryptos = it)
            }
        }
    }

    data class CryptoListState(
        val isLoading: Boolean = false,
        val cryptos: List<Crypto> = emptyList(),
        val error: String = ""
    )
}