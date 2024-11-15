package com.example.uipractice

import androidx.lifecycle.ViewModel

class BankData() : ViewModel() {
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