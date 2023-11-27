package com.wizard.cryptodata.domain

import com.wizard.cryptodata.data.network.CryptoContentResponse
import com.wizard.cryptodata.data.network.CryptoDetailResponse
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCryptoDetails(id: String): CryptoDetailResponse
    suspend fun getAllCryptos(): Map<String, CryptoContentResponse>
    suspend fun getAllCryptosFlow(): Flow<List<Crypto>>
    suspend fun addNew()
}