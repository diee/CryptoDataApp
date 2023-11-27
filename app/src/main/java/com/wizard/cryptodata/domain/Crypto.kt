package com.wizard.cryptodata.domain

import com.wizard.cryptodata.data.local.CryptoEntity
import com.wizard.cryptodata.data.network.CryptoContentResponse
import com.wizard.cryptodata.data.network.CryptoDetailResponse

data class Crypto(
    val name: String,
    val price: Double,
    val lastUpdated: String?
)

data class CryptoDetail(
    val name: String? = "",
    val marketCapRank: Int? = null,
    val sentimentVotesUpPercentage: Float? = null,
    val sentimentVotesDownPercentage: Float? = null,
    val priceChange24h: Float? = null,
    val priceChangePercentage24h: Double? = null,
    val totalSupply: Double? = null,
    val circulatingSupply: Double? = null,
    val lastUpdated: String? = ""
)

fun Pair<String, CryptoContentResponse>.convertToDomain(): Crypto {
    return Crypto(this.first, this.second.price, this.second.lastUpdatedAt.toString())
}

fun CryptoDetailResponse.convertToDomain(): CryptoDetail {
    return CryptoDetail(
        this.name,
        this.marketCapRank,
        this.sentimentVotesUpPercentage,
        this.sentimentVotesDownPercentage,
        this.marketData?.priceChange24h,
        this.marketData?.priceChangePercentage24h,
        this.marketData?.totalSupply,
        this.marketData?.circulatingSupply,
        this.lastUpdated
    )
}


fun Pair<String, CryptoContentResponse>.convertToDBEntity(): CryptoEntity {
    return CryptoEntity(this.first, this.second.price, this.second.lastUpdatedAt.toString())
}

fun CryptoEntity.toDomain(): Crypto {
    return Crypto(this.id, this.price ?: 0.0, this.lastUpdated)
}