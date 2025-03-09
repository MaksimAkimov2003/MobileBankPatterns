package ru.akimov.mobilebankpatterns.feature.main

import ru.akimov.mobilebankpatterns.core.BaseViewModel

class MainViewModel : BaseViewModel<MainScreenState, MainScreenAction, MainScreenEffect>(
    initialState = MainScreenMocks.content
) {
    override fun onAction(action: MainScreenAction) {
        when (action) {
            is MainScreenAction.OnItemClicked -> {
                onItemClicked(action)
            }
        }
    }

    private fun onItemClicked(action: MainScreenAction.OnItemClicked) {
        when (action.item.type) {
            Item.Type.ACCOUNT -> sendEffect(MainScreenEffect.NavigateToAccountDetails(action.item.subtitle))
            Item.Type.CREDIT -> {
                sendEffect(
                    MainScreenEffect.NavigateToCreditDetails(
                        name = action.item.subtitle,
                        balance = action.item.title
                    )
                )
            }

            Item.Type.BLOCKED_ACCOUNT -> {}
        }
    }
}