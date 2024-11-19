package com.example.uipractice

import androidx.lifecycle.ViewModel

class BankData() : ViewModel() {
    var bankName = "공백"
    var accountBalance: Long = 0
}
//    fun setbankName(bankName: String) {
//        this.bankName = bankName
//    }
//
//    fun setAccountBalance(accountBalance: Long) {
//        this.accountBalance = accountBalance
//    }
//
//    fun getBankName(): String {
//        return this.bankName
//    }
//
//    fun getAccountBalance(): String {
//        return this.accountBalance.toString()
//    }
//}