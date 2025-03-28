package com.example.data.util

object Secrets {
    init {
        System.loadLibrary("native-lib")
    }

    external fun getApiKey(): String
}