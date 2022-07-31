package com.cnexia.technicaltest.utils

import org.junit.Assert.*
import org.junit.Test

class ExtentionsKtTest {
    val price = "123"

    @Test
    fun testStringExtentionFunctionToKM() {
        val newPrice = price.addKToPrice()
        assertEquals(newPrice, price + "K")
    }
}