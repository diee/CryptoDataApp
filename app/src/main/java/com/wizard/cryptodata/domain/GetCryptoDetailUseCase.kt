package com.wizard.cryptodata.domain

import javax.inject.Inject

class GetCryptoDetailUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(name: String): CryptoDetail {
        return repository.getCryptoDetails(name).convertToDomain()
    }
}