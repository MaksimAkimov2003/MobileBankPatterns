package ru.akimov.mobilebankpatterns.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import ru.akimov.mobilebankpatterns.AppConstants
import ru.akimov.mobilebankpatterns.R
import ru.akimov.mobilebankpatterns.feature.main.MainScreenMocks.content
import ru.akimov.mobilebankpatterns.ui.components.AppBorder
import ru.akimov.mobilebankpatterns.ui.components.AppButton
import ru.akimov.mobilebankpatterns.ui.components.AppIcon
import ru.akimov.mobilebankpatterns.ui.components.AppLayout
import ru.akimov.mobilebankpatterns.ui.components.AppLoader
import ru.akimov.mobilebankpatterns.ui.theme.MobileBankPatternsTheme

@Composable
fun MainScreen(
    onNavigateToAccountDetails: (name: String) -> Unit,
    onNavigateToCreditDetails: (name: String, balance: String) -> Unit
) {
    // Fixme Maksim тут через коин сделать
    val viewModel = remember {
        MainViewModel()
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    MainScreenWidget(
        state = state,
        onAction = remember {
            { viewModel.onAction(it) }
        }
    )


    LaunchedEffect(key1 = true) {
        viewModel.effects.collectLatest {
            when (it) {
                is MainScreenEffect.NavigateToAccountDetails -> onNavigateToAccountDetails(it.name)
                is MainScreenEffect.NavigateToCreditDetails -> onNavigateToCreditDetails(
                    it.name,
                    it.balance
                )
            }
        }
    }
}

@Composable
private fun MainScreenWidget(
    state: MainScreenState,
    onAction: (MainScreenAction) -> Unit,
) {
    val topBarHeight = remember {
        AppConstants.topBarHeight
    }
    val footerHeight = remember {
        AppConstants.footerHeight
    }
    AppLayout(
        title = stringResource(R.string.main),
        topBarHeight = topBarHeight,
        footerHeight = footerHeight,
        showTopBar = true,
        showFooter = state.showFooter,
        footerContent = { FooterContent() }
    ) {
        Content(
            state = state,
            topBarHeight = topBarHeight,
            footerHeight = footerHeight,
            onAction = onAction
        )
    }
}

@Composable
private fun FooterContent() {
    Column(
        modifier = Modifier.navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        AppButton(
            onClick = { /*TODO*/ },
            text = stringResource(R.string.open_account),
            icon = painterResource(id = R.drawable.ic_card),
        )

        AppButton(
            onClick = { /*TODO*/ },
            text = stringResource(R.string.take_loan),
            icon = painterResource(id = R.drawable.ic_credit),
            isAccent = false
        )
    }
}

@Composable
private fun Content(
    state: MainScreenState,
    topBarHeight: Dp,
    footerHeight: Dp,
    onAction: (MainScreenAction) -> Unit,
) {
    when (state) {
        is MainScreenState.Content -> {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = topBarHeight,
                        bottom = footerHeight
                    )
                    .fillMaxSize()
            ) {
                itemsIndexed(
                    items = state.items,
                    key = { index, item -> item.id },
                    itemContent = { index, item ->
                        if (index == 0) {
                            Spacer(modifier = Modifier.height(24.dp))
                        }

                        Item(
                            item = item,
                            modifier = Modifier.fillParentMaxWidth(),
                            onAction = onAction
                        )

                        if (index == state.items.lastIndex) {
                            Spacer(modifier = Modifier.height(24.dp))
                        } else {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                )
            }
        }

        MainScreenState.Loading -> {
            AppLoader(isTransparent = false)
        }

        MainScreenState.Error -> {}
        MainScreenState.Empty -> {}
    }
}

@Composable
private fun Item(
    item: Item,
    onAction: (MainScreenAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .run {
                if (item.type != Item.Type.BLOCKED_ACCOUNT) {
                    clickable { onAction(MainScreenAction.OnItemClicked(item)) }
                } else this
            },
        color = getBackgroundColor(item.type),
        shadowElevation = if (item.type == Item.Type.BLOCKED_ACCOUNT) 0.dp else AppConstants.cardElevation,
        shape = RoundedCornerShape(16.dp),
        border = if (item.type == Item.Type.BLOCKED_ACCOUNT) null else
            AppBorder()
    ) {
        AccountRow(
            title = item.title,
            subtitle = item.subtitle,
            type = item.type
        )
    }
}

@Composable
fun AccountRow(
    title: String,
    subtitle: String,
    type: Item.Type
) {
    Row(
        Modifier.padding(16.dp)
    ) {
        AppIcon(
            color = getIconColor(type),
            painter = getIcon(type)
        )

        Column(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            val textColor = getTextColor(type)
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = textColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyLarge.copy(),
                color = textColor
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenWidgetLoadingPreview() {
    MobileBankPatternsTheme {
        MainScreenWidget(
            state = MainScreenState.Loading
        ) {}
    }
}

@Preview
@Composable
private fun MainScreenWidgetContentPreview() {
    MobileBankPatternsTheme {
        MainScreenWidget(
            state = content
        ) {}
    }
}