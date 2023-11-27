package com.wizard.cryptodata.data

import com.wizard.cryptodata.data.local.CryptoDao
import com.wizard.cryptodata.data.local.CryptoEntity
import com.wizard.cryptodata.data.network.CryptoApi
import com.wizard.cryptodata.data.network.CryptoContentResponse
import com.wizard.cryptodata.data.network.CryptoDetailResponse
import com.wizard.cryptodata.domain.Crypto
import com.wizard.cryptodata.domain.Repository
import com.wizard.cryptodata.domain.convertToDBEntity
import com.wizard.cryptodata.domain.convertToDomain
import com.wizard.cryptodata.domain.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cryptoApi: CryptoApi,
    private val cryptoDao: CryptoDao
) : Repository {

    override suspend fun getCryptoDetails(id: String): CryptoDetailResponse {
        return cryptoApi.getCryptoDetails(id)
    }

    override suspend fun getAllCryptos(): Map<String, CryptoContentResponse> {
        return cryptoApi.getAllCryptos("bitcoin,ethereum,pancakeswap-token,litecoin,polkadot")
    }

    override suspend fun getAllCryptosFlow(): Flow<List<Crypto>> {
        val response =
            cryptoApi.getAllCryptos("bitcoin,ethereum,pancakeswap-token,litecoin,polkadot")

        response.entries.map { it.toPair().convertToDBEntity() }.forEach {
            cryptoDao.insert(it)
        }
        return cryptoDao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun addNew() {
        cryptoDao.insert(CryptoEntity("Sarandonga", 0.0, ""))
    }


}