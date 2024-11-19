package com.example.uipractice


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

//여기에 왜 mutableListOf를 쓰지 못하는가... 알아봐야겠는데...
class BankViewModel() : ViewModel() {
    private val _listBankData = mutableStateOf(
        listOf(
            BankData().apply {
                BankData().bankName = "토스"
                BankData().accountBalance = 10000
            },
            BankData().apply {
                BankData().bankName = "국민"
                BankData().accountBalance =30000
            },
            BankData().apply {
                BankData().bankName = "하나"
                BankData().accountBalance =100000
            }
        )
    )

    //이게 외부에서 사용하는 값과 관련된 내용...
    //State는 투사의 대상으로 이해하면 편할까
    val bankList: State<List<BankData>> = _listBankData
    private var bankListNumber: Int = 3

    //지정은행추가를 위한 변수
    private var writeBankName = ""
    private var writeAccountBalance : Long= 0

    //지정은행 제거를 위한 변수
    private var removeNumber = 0//내부에서 사용하는 내용

    //20개의 은행,증권사의 이름이 있음.
    fun setRandomBank() {
        val bankName: String = BankName.entries.random().bankName
        val accountBalanceRange = (0..10000000)
        val accountBalance: Long = accountBalanceRange.random().toLong()

        //기존 값에 뒷 값을 더한다는 의미
        _listBankData.value += listOf(
            BankData().apply {
                BankData().bankName =bankName
                BankData().accountBalance =accountBalance
            }
        )
        bankListNumber++
    }

    fun setWriteBank() {
        //만약에 은행이름은 스트링이니 그렇다 치고, 잔고는 0과 자연수만 입력할 수 있게 try catch해야함.
        _listBankData.value += listOf(
            BankData().apply {
                BankData().bankName =writeBankName//이름이 어차피 구분되니 this를 안써도 된다.
                BankData().accountBalance =writeAccountBalance
            }
        )
        bankListNumber++
    }

    //만약 bankListNumber가 0이거나? 자연수 이외의 값을 입력하면 오류로 튕겨내기
    fun removeRandomBank() {
        val removeNumberRange = 0..bankListNumber - 1
        val removeNumber = removeNumberRange.random()

        _listBankData.value -= _listBankData.value[removeNumber]

        bankListNumber--
    }

    fun removeSelectBank() {
        _listBankData.value -= _listBankData.value[removeNumber]
        bankListNumber--
    }

    fun size():Int{
        return _listBankData.value.size
    }

    //지정은행 제거를 위한 데이터 입력함수
    fun setRemoveNumber(number : String) {
        this.removeNumber = number.toInt() - 1
    }


    //지정은행 추가를 위한 데이터입력함수
    fun setWriteBankName(name:String){
        this.writeBankName = name
    }

    fun setWriteAccountBalance(money:String){
        this.writeAccountBalance = money.toLong()
    }


}