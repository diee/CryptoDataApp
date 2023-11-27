package com.wizard.cryptodata.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizard.cryptodata.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExtraScreenViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    fun addNew() {
        viewModelScope.launch {
            repository.addNew()
        }
    }
}