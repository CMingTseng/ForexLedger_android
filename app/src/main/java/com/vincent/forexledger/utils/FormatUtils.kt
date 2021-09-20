package com.vincent.forexledger.utils

import java.text.NumberFormat
import java.util.Locale

object FormatUtils {
    private val moneyFormat = NumberFormat.getNumberInstance(Locale.US)

    fun formatMoney(amount: Int): String = moneyFormat.format(amount)

    fun formatMoney(amount: Double): String = moneyFormat.format(amount)
}