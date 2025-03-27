package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.ExchangeRateDao
import com.example.data.local.model.ExchangeRateEntity
import com.example.data.local.util.Constants.APP_DATA_BASE_VERSION_CODE

@Database(entities = [ExchangeRateEntity::class], version = APP_DATA_BASE_VERSION_CODE, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exchangeRateDao(): ExchangeRateDao
}
