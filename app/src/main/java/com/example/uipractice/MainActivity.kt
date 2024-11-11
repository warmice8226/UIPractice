package com.example.uipractice

import android.app.ActionBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.uipractice.ui.theme.UIPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UIPracticeTheme {
                Scaffold { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Column {
                            var listBankData: List<BankData>

                            listBankData = listOf(
                                BankData().apply {
                                    setbankName("토스")
                                    setAccountBalance(10000)
                                },
                                BankData().apply {
                                    setbankName("국민")
                                    setAccountBalance(30000)
                                },
                                BankData().apply {
                                    setbankName("하나")
                                    setAccountBalance(100000)
                                }

                            )


                            listBankData.forEach { listBankData ->
                                bankDisplay(
                                    listBankData.getBankName(),
                                    listBankData.getAccountBalance()
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun bankDisplay(bankName: String, money: String) {
    Row {
        bankIcon(icon = bankName)

        Column {
            accountName(bankName = bankName + "통장")
            accountBalance(accountBalance = money.toLong())
        }

        sendMoney()
    }
}


@Composable
fun bankIcon(icon: String) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Black, CircleShape)
            .padding(3.dp)
    ) {
        Text(
            text = icon,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}

@Composable
fun accountName(bankName: String) {
    Box(
        modifier = Modifier
            .size(150.dp, 40.dp)
            .padding(10.dp, 0.dp, 0.dp, 0.dp)
    ) {
        Text(
            text = bankName,
            textAlign = TextAlign.Left
        )
    }
}

@Composable
fun accountBalance(accountBalance: Long) {
    Box(
        modifier = Modifier
            .size(150.dp, 40.dp)
            .padding(10.dp, 0.dp, 0.dp, 0.dp)
    ) {
        Text(
            text = accountBalance.toString() + "원",
            textAlign = TextAlign.Left
        )
    }
}

@Composable
fun sendMoney() {
    Box(
        modifier = Modifier
            .size(80.dp)
            .padding(15.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
    ) {
        Text(
            text = "송금",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}