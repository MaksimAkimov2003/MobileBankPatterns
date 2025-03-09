package ru.akimov.mobilebankpatterns.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BaseState<DATA>(
    val data: DATA,
    val isLoading: Boolean = false,
    val isP2R: Boolean = false,
    val error: Throwable? = null
) {
    sealed interface Mode {
        data object Content : Mode
        data object Error : Mode
        data object Loading : Mode
        data object Empty : Mode
    }
}

abstract class BaseViewModel<STATE, ACTION, EFFECT>(
    initialState: STATE
) : ViewModel() {
    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    private val _effects: MutableSharedFlow<EFFECT> = MutableSharedFlow()
    val effects = _effects.shareIn(viewModelScope, started = SharingStarted.WhileSubscribed(500L))

    abstract fun onAction(action: ACTION)

    protected fun updateState(updater: STATE.() -> STATE) {
        _state.update { it.updater() }
    }

    protected fun sendEffect(effect: EFFECT) {
        viewModelScope.launch { _effects.emit(effect) }
    }
}