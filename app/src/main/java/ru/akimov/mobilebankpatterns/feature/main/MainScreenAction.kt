package ru.akimov.mobilebankpatterns.feature.main

sealed interface MainScreenAction {
    data class OnItemClicked(val item: Item) : MainScreenAction
}