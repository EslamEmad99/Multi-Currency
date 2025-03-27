package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.model.ExchangeRateEntity
import com.example.data.local.util.Constants.HISTORICAL_EXCHANGES_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeRateDao {

    @Query("SELECT * FROM $HISTORICAL_EXCHANGES_TABLE_NAME WHERE baseCurrency = :from AND targetCurrency = :to ORDER BY date DESC")
    fun getExchangeRateHistory(from: String, to: String): Flow<List<ExchangeRateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeRate(exchangeRate: ExchangeRateEntity)

    @Query("DELETE FROM $HISTORICAL_EXCHANGES_TABLE_NAME WHERE date < :minDate")
    suspend fun cleanupOldData(minDate: String)
}
