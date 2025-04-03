package com.example.domain.usecase

import com.example.domain.exceptions.ConvertCurrencyExceptions
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ConvertCurrencyUseCaseTest {

    private lateinit var convertCurrencyUseCase: ConvertCurrencyUseCase

    @Before
    fun setUp() {
        convertCurrencyUseCase = ConvertCurrencyUseCase()
    }

    @Test
    fun `convert currency with valid rates and amount returns correct result`() {
        // Given
        val fromRate = 10.0
        val toRate = 20.0
        val amount = 100.0

        // When
        val result = convertCurrencyUseCase(fromRate, toRate, amount)

        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(200.0) // (100 / 10) * 20 = 200
    }

    @Test
    fun `convert currency with zero fromRate returns failure InvalidExchangeRateException`() {
        // Given
        val fromRate = 0.0
        val toRate = 20.0
        val amount = 100.0

        // When
        val result = convertCurrencyUseCase(fromRate, toRate, amount)

        // Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(ConvertCurrencyExceptions.InvalidExchangeRateException)
    }

    @Test
    fun `convert currency with zero toRate returns failure InvalidExchangeRateException`() {
        // Given
        val fromRate = 10.0
        val toRate = 0.0
        val amount = 100.0

        // When
        val result = convertCurrencyUseCase(fromRate, toRate, amount)

        // Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(ConvertCurrencyExceptions.InvalidExchangeRateException)
    }

    @Test
    fun `convert currency with negative fromRate returns failure InvalidExchangeRateException`() {
        // Given
        val fromRate = -5.0
        val toRate = 20.0
        val amount = 100.0

        // When
        val result = convertCurrencyUseCase(fromRate, toRate, amount)

        // Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(ConvertCurrencyExceptions.InvalidExchangeRateException)
    }

    @Test
    fun `convert currency with negative toRate returns failure InvalidExchangeRateException`() {
        // Given
        val fromRate = 10.0
        val toRate = -2.0
        val amount = 100.0

        // When
        val result = convertCurrencyUseCase(fromRate, toRate, amount)

        // Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(ConvertCurrencyExceptions.InvalidExchangeRateException)
    }

    @Test
    fun `convert currency with zero amount returns failure InvalidAmountException`() {
        // Given
        val fromRate = 10.0
        val toRate = 20.0
        val amount = 0.0

        // When
        val result = convertCurrencyUseCase(fromRate, toRate, amount)

        // Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(ConvertCurrencyExceptions.InvalidAmountException)
    }

    @Test
    fun `convert currency with small values returns correct result`() {
        // Given
        val fromRate = 0.1
        val toRate = 0.2
        val amount = 1.0

        // When
        val result = convertCurrencyUseCase(fromRate, toRate, amount)

        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(2.0) // (1 / 0.1) * 0.2 = 2.0
    }

    @Test
    fun `convert currency with large values returns correct result`() {
        // Given
        val fromRate = 100000.0
        val toRate = 500000.0
        val amount = 1000000.0

        // When
        val result = convertCurrencyUseCase(fromRate, toRate, amount)

        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(5000000.0) // (1000000 / 100000) * 500000 = 5000000
    }
}
