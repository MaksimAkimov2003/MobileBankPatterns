package ru.akimov.mobilebankpatterns.feature.accountDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.NavController
import ru.akimov.mobilebankpatterns.AppConstants
import ru.akimov.mobilebankpatterns.R
import ru.akimov.mobilebankpatterns.ui.components.AppBubble
import ru.akimov.mobilebankpatterns.ui.components.AppBubbleText
import ru.akimov.mobilebankpatterns.ui.components.AppBubblesRow
import ru.akimov.mobilebankpatterns.ui.components.AppIcon
import ru.akimov.mobilebankpatterns.ui.components.AppIsland
import ru.akimov.mobilebankpatterns.ui.components.AppLayout
import ru.akimov.mobilebankpatterns.ui.theme.MobileBankPatternsTheme
import ru.akimov.mobilebankpatterns.ui.theme.accent
import ru.akimov.mobilebankpatterns.ui.theme.greenColor
import ru.akimov.mobilebankpatterns.ui.theme.mint
import ru.akimov.mobilebankpatterns.ui.theme.purpleColor
import java.util.UUID

data class AccountDetailsScreenState(
    val operations: List<Operation>
) {
    data class Operation(
        val amount: Int,
        val type: OperationType,
        val key: UUID = UUID.randomUUID()
    )
}

enum class OperationType {
    ADD, TRANSFER, PAY_LOAN
}

@Composable
fun AccountDetailsScreen(
    navController: NavController,
    accountName: String,
) {
    AccountDetailsScreenWidget(
        state = AccountDetailsMocks.state,
        accountName = accountName,
        onBackClicked = { navController.popBackStack() }
    )
}

@Composable
private fun AccountDetailsScreenWidget(
    state: AccountDetailsScreenState,
    accountName: String,
    onBackClicked: () -> Unit = {}
) {
    val topBarHeight = remember {
        AppConstants.topBarHeight
    }
    AppLayout(
        title = accountName,
        onBackClick = onBackClicked,
        topBarHeight = topBarHeight
    ) {
        Column(
            modifier = Modifier
                .padding(top = topBarHeight, start = 18.dp, end = 18.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            AppIsland(
                title = stringResource(R.string.actions)
            ) {
                ScreenIslandContent()
            }

            Spacer(modifier = Modifier.height(24.dp))

            AppIsland(title = stringResource(R.string.operations_on_account)) {
                Column {
                    state.operations.fastForEachIndexed { index, item ->
                        key(item.key) {
                            OperationItem(
                                amount = item.amount,
                                operationType = item.type
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
 fun ScreenIslandContent() {
    AppBubblesRow {
        ScreenBubblesRowContent()
    }
}

@Composable
fun ScreenBubblesRowContent() {
    ActionBubble(
        text = stringResource(R.string.add_account),
        icon = {
            PaymentIcon(
                color = MaterialTheme.colorScheme.mint,
                painter = painterResource(id = R.drawable.ic_arrow_down)
            )
        },
    )

    ActionBubble(
        text = stringResource(id = R.string.add_money),
        icon = {
            PaymentIcon(
                color = MaterialTheme.colorScheme.accent,
                painter = painterResource(id = R.drawable.ic_arrow_up)
            )
        },
    )

    ActionBubble(
        text = stringResource(R.string.close_account),
        icon = {
            Box(modifier = Modifier.size(40.dp)) {
                Icon(
                    modifier = Modifier.padding(top = 10.dp),
                    painter = painterResource(id = R.drawable.ic_block_danger),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
        },
    )
}

@Composable
private fun ActionBubble(
    text: String,
    icon: @Composable () -> Unit,
) {
    AppBubble(heightInMin = 108.dp) {
        Column {
            icon()
            Spacer(modifier = Modifier.height(8.dp))
            AppBubbleText(text)
        }
    }
}

@Composable
private fun OperationItem(
    amount: Int,
    operationType: OperationType
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        when (operationType) {
            OperationType.ADD -> PaymentIcon(
                color = MaterialTheme.colorScheme.mint,
                painter = painterResource(id = R.drawable.ic_arrow_down)
            )

            OperationType.TRANSFER -> PaymentIcon(
                color = MaterialTheme.colorScheme.accent,
                painter = painterResource(id = R.drawable.ic_arrow_up)
            )

            OperationType.PAY_LOAN -> PaymentIcon(
                color = MaterialTheme.colorScheme.purpleColor,
                painter = painterResource(id = R.drawable.ic_credit)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = getNameText(operationType),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Text(
                text = getAmountText(operationType, amount),
                color = getAmountColor(operationType)
            )
        }
    }
}

@Composable
private fun getNameText(operationType: OperationType) =
    when (operationType) {
        OperationType.ADD -> stringResource(R.string.add)
        OperationType.TRANSFER -> stringResource(R.string.transfer)
        OperationType.PAY_LOAN -> stringResource(R.string.pay_loan)
    }

@Composable
private fun getAmountColor(operationType: OperationType) =
    when (operationType) {
        OperationType.ADD -> MaterialTheme.colorScheme.greenColor
        OperationType.TRANSFER,
        OperationType.PAY_LOAN -> MaterialTheme.colorScheme.onBackground
    }

@Composable
private fun getAmountText(
    operationType: OperationType,
    amount: Int
): String {
    return buildString {
        if (operationType == OperationType.ADD) {
            append("+")
        } else {
            append("-")
        }
        append(amount.toString())
        append(" р")
    }
}

@Composable
private fun PaymentIcon(
    color: Color,
    painter: Painter,
) {
    AppIcon(
        size = 40.dp,
        cornerRadius = 30.dp,
        color = color,
        painter = painter
    )
}

object AccountDetailsMocks {
    val state = AccountDetailsScreenState(
        operations = buildList {
            repeat(10) {
                when (it % 3) {
                    0 -> add(
                        AccountDetailsScreenState.Operation(
                            amount = 200,
                            type = OperationType.ADD
                        )
                    )

                    1 -> add(
                        AccountDetailsScreenState.Operation(
                            amount = 500,
                            type = OperationType.TRANSFER
                        )
                    )

                    else -> add(
                        AccountDetailsScreenState.Operation(
                            amount = 1000,
                            type = OperationType.PAY_LOAN
                        )
                    )
                }
            }
        }
    )
}

@Preview
@Composable
internal fun AccountDetailsScreenWidgetPreview() {
    MobileBankPatternsTheme {
        AccountDetailsScreenWidget(
            accountName = "Основной",
            state = AccountDetailsMocks.state
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun OperationItemPreview() {
    MobileBankPatternsTheme {
        OperationItem(
            amount = 500,
            operationType = OperationType.PAY_LOAN
        )
    }
}