package com.wizard.cryptodata.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cryptos")
data class CryptoEntity(

    @PrimaryKey
    var id: String,
    var price: Double? = 0.0,
    val lastUpdated: String?
)