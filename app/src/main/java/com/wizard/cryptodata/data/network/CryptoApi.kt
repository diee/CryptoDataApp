package com.wizard.cryptodata.data.network

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {

    @GET("/api/v3/coins/{id}")
    suspend fun getCryptoDetails(
        @Path("id") id: String
    ): CryptoDetailResponse

    @GET("/api/v3/simple/price?")
    suspend fun getAllCryptos(
        @Query("ids") id: String,
        @Query("vs_currencies") vsCurrencies: String? = "usd",
        @Query("include_last_updated_at") lastUpdate: Boolean? = true
    ): Map<String, CryptoContentResponse>
}

data class CryptoDetailResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int? = null,
    @SerializedName("sentiment_votes_up_percentage")
    val sentimentVotesUpPercentage: Float? = null,
    @SerializedName("sentiment_votes_down_percentage")
    val sentimentVotesDownPercentage: Float? = null,
    @SerializedName("market_data")
    val marketData: MarketData? = null,
    @SerializedName("last_updated")
    val lastUpdated: String? = ""
)

data class MarketData(
    @SerializedName("price_change_24h")
    val priceChange24h: Float? = null,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double? = null,
    @SerializedName("total_supply")
    val totalSupply: Double? = null,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double? = null
)


data class CryptoContentResponse(
    @SerializedName("usd")
    val price: Double,
    @SerializedName("last_updated_at")
    val lastUpdatedAt: Long
)