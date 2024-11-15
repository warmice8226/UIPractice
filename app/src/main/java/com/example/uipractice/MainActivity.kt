package com.example.uipractice

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.uipractice.ui.theme.UIPracticeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UIPracticeTheme {

                //ViewModelProvider 라는 기능을 추가로 적용했어야 한다... 좀 더 정확한 공부가 필요
                val bankViewModel = ViewModelProvider(this).get(BankViewModel::class.java)

                //Snackbar를 위한 변수추가
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = Color.LightGray,
                                titleContentColor = Color.Black,
                            ),
                            title = {
                                Text(
                                    text = "은행 잔액 모음",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize()
                                )
                            },
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = Color.LightGray
                        ) {
                            Row {
                                creatRandomBankButton(bankViewModel)
                                creatWriteBankButton(bankViewModel)
                                removeRandomBankButton(
                                    bankViewModel,
                                    snackbarHostState,
                                    scope
                                )
                                removeSelectBankButton(
                                    bankViewModel,
                                    snackbarHostState,
                                    scope
                                )
                            }
                        }
                    },
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(top = 30.dp)
                            .wrapContentWidth()
                    ) {
                        Column {
                            val bankDataList = bankViewModel.bankList.value

                            bankDataList.forEach { bankDataList ->
                                bankDisplay(
                                    bankDataList.getBankName(), bankDataList.getAccountBalance()
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}

@Composable
fun bankDisplay(bankName: String, money: String) {
    Box(
        modifier = Modifier.padding(15.dp)
    ) {
        Row {
            bankIcon(icon = bankName)

            Column {
                accountName(bankName = bankName + "통장")
                accountBalance(accountBalance = money.toLong())
            }

            sendMoney {}
        }
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
            text = bankName, textAlign = TextAlign.Left
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
            text = accountBalance.toString() + "원", textAlign = TextAlign.Left
        )
    }
}

@Composable
fun sendMoney(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .size(60.dp)
            .fillMaxSize()
            .wrapContentSize()
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp)),
        colors = ButtonColors(
            containerColor = Color(red = 0x9C, green = 0xF1, blue = 0xFF, alpha = 0xFF),
            contentColor = Color(red = 0x0, green = 0x0, blue = 0x0, alpha = 0xFF),
            disabledContainerColor = Color(red = 0x64, green = 0x64, blue = 0x64, alpha = 0xFF),
            disabledContentColor = Color(red = 0xE6, green = 0xE6, blue = 0xE6, alpha = 0xFF),
        ),
        shape = RoundedCornerShape(20.dp)
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


@Composable
fun creatRandomBankButton(viewModel: BankViewModel) {
    Button(
        onClick = { viewModel.setRandomBank() }, modifier = Modifier
            .fillMaxWidth(0.25f)
            .fillMaxHeight()
    ) {
        Text(
            text = "무작위\n은행추가", textAlign = TextAlign.Center
        )
    }
}


@Composable
fun creatWriteBankButton(viewModel: BankViewModel) {
    var bankName by remember { mutableStateOf("은행/증권사 이름") }
    var accountBalance by remember { mutableStateOf("0") }
    var showFirstDialog by remember { mutableStateOf(false) }
    var showSecondDialog by remember { mutableStateOf(false) }
//    var  showFirstError by remember { mutableStateOf(false) }
    var showSecondError by remember { mutableStateOf(false) }

    FilledTonalButton(
        onClick = { showFirstDialog = true },
        modifier = Modifier
            .fillMaxWidth(0.33f)
            .fillMaxHeight()
    ) {
        Text(
            text = "지정\n은행추가", textAlign = TextAlign.Center
        )
    }

    //은행/증권사 이름을 입력하는 과정
    if (showFirstDialog) {
        AlertDialog(
            onDismissRequest = { showFirstDialog = false },
            title = { Text(text = "추가할 은행을 입력해주세요") },
            text = {
                Column {
                    TextField(
                        value = bankName,
                        onValueChange = { newValue ->
                            bankName = newValue
                            viewModel.setWriteBankName(newValue)
                        },
                        singleLine = true  // 한 줄 입력으로 제한
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showFirstDialog = false
                        showSecondDialog = true
                    }
                ) {
                    Text(text = "다음")
                }
            },

            dismissButton = {
                Button(onClick = { showFirstDialog = false }) {
                    Text(text = "취소")
                }
            }
        )

    }

    //은행 잔고를 입력하는 데이터
    if (showSecondDialog) {
        AlertDialog(
            onDismissRequest = { showSecondDialog = false },
            title = { Text(text = "잔고를 입력해주세요") },
            text = {
                Column {
                    OutlinedTextField(
                        value = accountBalance,
                        onValueChange = { newValue ->
                            if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                                accountBalance = newValue
                                showSecondError = false
                                if (newValue.isNotEmpty()) {
                                    viewModel.setWriteAccountBalance(newValue)
                                } else {
                                    showSecondError = true
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = showSecondError,
                        singleLine = true  // 한 줄 입력으로 제한
                    )
                    if (showSecondError) {
                        Text(
                            text = "유효한 숫자를 입력해주세요",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (accountBalance.isEmpty()) {
                            showSecondError = true
                        } else {
                            showSecondDialog = false
                            viewModel.setWriteBank()
                        }
                    }
                ) {
                    Text(text = "완료")
                }
            },

            dismissButton = {
                Button(onClick = { showSecondDialog = false }) {
                    Text(text = "취소")
                }
            }
        )

    }


}

@Composable
fun removeRandomBankButton(
    viewModel: BankViewModel,
    snackbarHostState: SnackbarHostState,//스낵바 호스트 스테이트 전달받기
    scope: CoroutineScope, //코루틴 스코프 받기
) {
    val size: Int = viewModel.size()

    ElevatedButton(
        onClick = {
            if (size <= 0) {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "데이터가 없습니다.",
                        duration = SnackbarDuration.Short
                    )
                }
            } else {
                viewModel.removeRandomBank()
            }

        },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight()
    ) {
        Text(
            text = "무작위\n은행제거", textAlign = TextAlign.Center
        )
    }
}

@Composable
fun removeSelectBankButton(
    viewModel: BankViewModel,
    snackbarHostState: SnackbarHostState,//스낵바 호스트 스테이트 전달받기
    scope: CoroutineScope, //코루틴 스코프 받기
) {
    var showDialog by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("1") }
    var showError by remember { mutableStateOf(false) }  // 에러 상태 추가
    val size = viewModel.size()


    ElevatedButton(
        onClick = {
            if (size <= 0) {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "데이터가 없습니다.",
                        duration = SnackbarDuration.Short
                    )
                }
            } else {
                showDialog = true
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "지정\n은행제거", textAlign = TextAlign.Center
        )
    }

    if (showDialog) {
        if (showDialog) {
            AlertDialog(onDismissRequest = { showDialog = false },
                title = { Text(text = "삭제할 번호를 입력해주세요.") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = inputText,
                            onValueChange = { newValue ->
                                if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                                    inputText = newValue
                                    showError = false
                                    if (newValue.isNotEmpty() && newValue.toInt() <= viewModel.size() && newValue.toInt() > 0) {
                                        viewModel.setRemoveNumber(newValue)
                                    } else {
                                        showError = true
                                    }
                                }
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            isError = showError,
                            singleLine = true  // 한 줄 입력으로 제한
                        )
                        if (showError) {
                            Text(
                                text = "유효한 숫자를 입력해주세요",
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if (inputText.isEmpty()) {
                            showError = true
                        } else {
                            showDialog = false
                            viewModel.removeSelectBank()
                        }
                    }) {
                        Text("완료")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        showDialog = false
                        inputText = "0"
                        showError = false
                    }) {
                        Text("취소")
                    }
                })
        }
    }
}