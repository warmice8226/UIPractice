package com.example.uipractice

enum class BankName(val bankName: String) {
    Kookmin("KB국민은행"),
    Shinhan("신한은행"),
    Woori("우리은행"),
    Hana("하나은행"),
    NongHyup("NH농협은행"),
    Industrial("IBK기업은행"),
    K("케이뱅크"),
    Kakao("카카오뱅크"),
    SC("SC제일"),
    Citi("씨티은행"),
    Samsung("삼성증권"),
    MiraeAsset("미래에셋증권"),
    KoreaInvestment("한국투자증권"),
    NHInvestment("NH투자증권"),
    KB("KB증권"),
    HanaSecurities("하나증권"),
    ShinhanInvestment("신한투자증권"),
    Meritz("메리츠증권"),
    Daishin("대신증권"),
    Kiwoom("키움증권")
}

fun getRandomBankName(): String {
    return BankName.values().random().bankName
}
