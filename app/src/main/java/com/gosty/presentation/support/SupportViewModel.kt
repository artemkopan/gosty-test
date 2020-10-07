package com.gosty.presentation.support

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gosty.repository.SupportRepo
import com.gosty.usecase.support.EmailContactUseCase
import com.gosty.usecase.support.TelegramContactUseCase
import com.gosty.usecase.support.ViberContactUseCase
import com.gosty.usecase.support.WhatsUpContactUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SupportViewModel(
    private val supportRepo: SupportRepo,
    private val emailContactUseCase: EmailContactUseCase,
    private val telegramContactUseCase: TelegramContactUseCase,
    private val viberContactUseCase: ViberContactUseCase,
    private val whatsUpContactUseCase: WhatsUpContactUseCase,

    ) : ViewModel() {

    private val mutableState = MutableStateFlow(SupportState())
    val state: StateFlow<SupportState> get() = mutableState

    init {
        viewModelScope.launch(createErrorHandler() + Dispatchers.IO) {
            mutateState {
                copy(questions = supportRepo.getQuestions())
            }
        }
    }

    private suspend fun mutateState(apply: suspend SupportState.() -> SupportState) {
        mutableState.value = apply(mutableState.value)
    }

    fun onEmailClicked() {
        viewModelScope.launch(createErrorHandler() + Dispatchers.Default) {
            emailContactUseCase.execute(Unit)
        }
    }

    fun onWhatsUpClicked() {
        viewModelScope.launch(createErrorHandler() + Dispatchers.Default) {
            whatsUpContactUseCase.execute(Unit)
        }
    }

    fun onTelegramClicked() {
        viewModelScope.launch(createErrorHandler() + Dispatchers.Default) {
            telegramContactUseCase.execute(Unit)
        }
    }

    fun onViberClicked() {
        viewModelScope.launch(createErrorHandler() + Dispatchers.Default) {
            viberContactUseCase.execute(Unit)
        }
    }

    fun consumeError() {
        viewModelScope.launch {
            mutateState { copy(throwable = null) }
        }
    }

    private fun createErrorHandler(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                Log.e(TAG, throwable.message, throwable)
                mutateState { copy(throwable = throwable) }
            }
        }
    }

    companion object {
        private const val TAG = "SupportViewModel"
    }

}

