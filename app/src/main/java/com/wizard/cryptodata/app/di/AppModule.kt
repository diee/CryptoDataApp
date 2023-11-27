package com.wizard.cryptodata.app.di

import android.app.Application
import androidx.room.Room
import com.wizard.cryptodata.data.network.CryptoApi
import com.wizard.cryptodata.data.RepositoryImpl
import com.wizard.cryptodata.data.local.CryptoDao
import com.wizard.cryptodata.data.local.CryptosDataBase
import com.wizard.cryptodata.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCryptoApi(): CryptoApi {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(CryptoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CryptoApi, dao: CryptoDao): Repository {
        return RepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app, CryptosDataBase::class.java, "cryptodata-db"
    ).build()

    @Provides
    @Singleton
    fun cryptoDaoProvider(dataBase: CryptosDataBase): CryptoDao {
        return dataBase.cryptoDao()
    }
}