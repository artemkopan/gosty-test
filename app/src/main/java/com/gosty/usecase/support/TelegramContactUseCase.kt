package com.gosty.usecase.support

import com.gosty.data.IntentLauncher
import com.gosty.usecase.UseCase

class TelegramContactUseCase(private val intentLauncher: IntentLauncher) : UseCase<Any, Any> {

    override fun execute(params: Any): Any = intentLauncher.launchTelegram("HedgehogB")

}