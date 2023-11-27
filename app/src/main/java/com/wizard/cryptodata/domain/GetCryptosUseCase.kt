package com.wizard.cryptodata.domain

import javax.inject.Inject

class GetCryptosUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): List<Crypto> {
        return repository.getAllCryptos().entries.map { it.toPair().convertToDomain() }
    }
}