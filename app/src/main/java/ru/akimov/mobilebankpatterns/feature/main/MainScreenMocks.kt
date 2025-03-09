package ru.akimov.mobilebankpatterns.feature.main

object MainScreenMocks {
    val content = MainScreenState.Content(
        isRefreshing = false,
        items = listOf(
            Item(
                id = "1",
                title = "25 230₽",
                subtitle = "Основной",
                type = Item.Type.ACCOUNT
            ),
            Item(
                id = "2",
                title = "11₽",
                subtitle = "Дополнительный",
                type = Item.Type.ACCOUNT
            ),
            Item(
                id = "3",
                title = "-25 370₽",
                subtitle = "Кредит наличными",
                type = Item.Type.CREDIT
            ),
            Item(
                id = "4",
                title = "15₽",
                subtitle = "Закрытый счет",
                type = Item.Type.BLOCKED_ACCOUNT
            ),
            Item(
                id = "5",
                title = "25 230₽",
                subtitle = "Основной",
                type = Item.Type.ACCOUNT
            ),
            Item(
                id = "6",
                title = "11₽",
                subtitle = "Дополнительный",
                type = Item.Type.ACCOUNT
            ),
            Item(
                id = "7",
                title = "-25 370₽",
                subtitle = "Кредит наличными",
                type = Item.Type.CREDIT
            ),
            Item(
                id = "8",
                title = "15₽",
                subtitle = "Закрытый счет",
                type = Item.Type.BLOCKED_ACCOUNT
            ),
        )
    )
}