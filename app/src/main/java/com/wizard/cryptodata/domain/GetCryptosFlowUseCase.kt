package com.wizard.cryptodata.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCryptosFlowUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Flow<List<Crypto>> {
        return repository.getAllCryptosFlow()
    }
}