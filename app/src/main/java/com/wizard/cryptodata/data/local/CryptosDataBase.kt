package com.wizard.cryptodata.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CryptoEntity::class], version = 1)
abstract class CryptosDataBase : RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao

}