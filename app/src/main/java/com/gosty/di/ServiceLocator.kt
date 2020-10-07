package com.gosty.di

import android.content.Context
import com.gosty.data.IntentLauncher
import com.gosty.data.IntentLauncherImpl
import com.gosty.presentation.support.SupportViewModel
import com.gosty.repository.SupportRepo
import com.gosty.repository.SupportRepoImpl
import com.gosty.usecase.support.EmailContactUseCase
import com.gosty.usecase.support.TelegramContactUseCase
import com.gosty.usecase.support.ViberContactUseCase
import com.gosty.usecase.support.WhatsUpContactUseCase

object ServiceLocator {

    private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context
    }

    val intentLauncher: IntentLauncher by lazy {
        IntentLauncherImpl(context)
    }

    val supportRepo: SupportRepo
        get() = SupportRepoImpl()

    val emailContactUseCase: EmailContactUseCase
        get() = EmailContactUseCase(intentLauncher)
    val telegramContactUseCase: TelegramContactUseCase
        get() = TelegramContactUseCase(intentLauncher)
    val viberContactUseCase: ViberContactUseCase
        get() = ViberContactUseCase(intentLauncher)
    val whatsUpContactUseCase: WhatsUpContactUseCase
        get() = WhatsUpContactUseCase(intentLauncher)

    val supportViewModel: SupportViewModel
        get() = SupportViewModel(
            supportRepo,
            emailContactUseCase,
            telegramContactUseCase,
            viberContactUseCase,
            whatsUpContactUseCase
        )

}