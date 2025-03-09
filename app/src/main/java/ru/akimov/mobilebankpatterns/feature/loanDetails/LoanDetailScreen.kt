package ru.akimov.mobilebankpatterns.feature.loanDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.akimov.mobilebankpatterns.AppConstants
import ru.akimov.mobilebankpatterns.R
import ru.akimov.mobilebankpatterns.feature.main.AccountRow
import ru.akimov.mobilebankpatterns.feature.main.Item
import ru.akimov.mobilebankpatterns.ui.components.AppBubble
import ru.akimov.mobilebankpatterns.ui.components.AppBubbleText
import ru.akimov.mobilebankpatterns.ui.components.AppBubblesRow
import ru.akimov.mobilebankpatterns.ui.components.AppIcon
import ru.akimov.mobilebankpatterns.ui.components.AppIsland
import ru.akimov.mobilebankpatterns.ui.components.AppLayout
import ru.akimov.mobilebankpatterns.ui.components.AppSpacerHeight
import ru.akimov.mobilebankpatterns.ui.theme.MobileBankPatternsTheme
import ru.akimov.mobilebankpatterns.ui.theme.mint

data class LoanDetailScreenState(
    val tarrifName: String,
    val tarrifRate: Int,
    val accountName: String,
    val accountBalance: String,
)

@Composable
fun LoanDetailScreen(
    name: String,
    balance: String,
    onBackClicked: () -> Unit,
) {
    LoanScreenWidget(
        name = name,
        balance = balance,
        state = mockState,
        onBackClicked = onBackClicked
    )
}

@Composable
private fun LoanScreenWidget(
    name: String,
    balance: String,
    state: LoanDetailScreenState,
    onBackClicked: () -> Unit,
) {
    val topBarHeight = remember {
        120.dp
    }
    AppLayout(
        topBarHeight = topBarHeight,
        title = name,
        onBackClick = onBackClicked,
        subtitle = balance
    ) {
        Column(
            modifier = Modifier
                .padding(top = topBarHeight, start = 16.dp, end = 16.dp)

        ) {
            Spacer(modifier = Modifier.height(24.dp))
            AppIsland(title = stringResource(id = R.string.actions)) {
                AppBubblesRow {
                    ScreenBubble(
                        text = stringResource(R.string.pay_full),
                        painter = painterResource(id = R.drawable.ic_check)
                    )
                    ScreenBubble(
                        text = stringResource(R.string.pay_partitial),
                        painter = painterResource(id = R.drawable.ic_part)
                    )
                }
            }

            AppSpacerHeight()
            AppIsland(
                title = stringResource(R.string.credit_detail_account_title),
                needSpacing = false,
                bottomPadding = 0.dp
            ) {
                AccountRow(
                    title = state.accountBalance,
                    subtitle = state.accountName,
                    type = Item.Type.ACCOUNT
                )
            }

            AppSpacerHeight()

            AppIsland(title = state.tarrifName) {
                Text(text = state.tarrifRate.toString() + "%" + " " + stringResource(R.string.years))
            }
        }
    }
}

@Composable
private fun RowScope.ScreenBubble(
    text: String,
    painter: Painter,
) {
    AppBubble(
        heightInMin = Dp.Unspecified
    ) {
        Row {
            AppIcon(
                color = MaterialTheme.colorScheme.mint,
                painter = painter
            )
            Spacer(Modifier.width(12.dp))
            AppBubbleText(text = text)
        }
    }
}

private val mockState = LoanDetailScreenState(
    tarrifName = "Тариф Выгодный",
    tarrifRate = 25,
    accountBalance = "25230 ${AppConstants.rubbleSymbol}",
    accountName = "Основной",
)

@Preview
@Composable
internal fun LoanScreenWidgetPreview() {
    MobileBankPatternsTheme {
        LoanScreenWidget(
            name = "Кредит наличными",
            balance = "-12300 ${AppConstants.rubbleSymbol}",
            onBackClicked = {},
            state = mockState
        )
    }
}