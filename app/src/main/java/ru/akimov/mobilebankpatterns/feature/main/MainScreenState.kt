package ru.akimov.mobilebankpatterns.feature.main

data class Item(
    val id: String,
    val title: String,
    val subtitle: String,
    val type: Type,
) {
    enum class Type {
        ACCOUNT, CREDIT, BLOCKED_ACCOUNT
    }
}

sealed interface MainScreenState {
    val showFooter: Boolean
        get() = false

    data object Loading : MainScreenState
    data object Error : MainScreenState
    data object Empty : MainScreenState
    data class Content(
        val isRefreshing: Boolean,
        val items: List<Item>,
    ) : MainScreenState {
        override val showFooter: Boolean = true
    }
}