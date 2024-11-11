package com.example.uipractice

import kotlin.reflect.KMutableProperty0

class BankData {
    private var bankName = "공백"
    private var accountBalance = 0

    fun setbankName(bankName: String) {
        this.bankName = bankName
    }

    fun setAccountBalance(accountBalance: Long) {
        this.accountBalance = accountBalance.toInt()
    }

    fun getBankName(): String {
        return this.bankName
    }

    fun getAccountBalance(): String {
        return this.accountBalance.toString()
    }
}