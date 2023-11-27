package com.wizard.cryptodata.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoDao {

    @Query("SELECT * from cryptos")
    fun getAll(): Flow<List<CryptoEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(cryptoEntity: CryptoEntity)
}