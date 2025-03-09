package ru.akimov.mobilebankpatterns.feature.main

sealed interface MainScreenEffect {
    data class NavigateToCreditDetails(
        val name: String,
        val balance: String
    ) : MainScreenEffect

    data class NavigateToAccountDetails(
        val name: String
    ) : MainScreenEffect
}