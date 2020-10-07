package com.gosty.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity


interface IntentLauncher {

    fun launchEmail(mailTo: String, body: String)

    fun launchWhatsUp(phone: String)

    fun launchTelegram(userId: String)

    fun launchViber(phone: String)
}

class IntentLauncherImpl(private val context: Context) : IntentLauncher {

    override fun launchEmail(mailTo: String, body: String) {
        val emailIntent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts("mailto", mailTo, null)
        )
        emailIntent.putExtra(Intent.EXTRA_TEXT, body)
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(emailIntent)
    }

    override fun launchWhatsUp(phone: String) {
        try {
            context.startActivity(
                Intent(Intent.ACTION_VIEW)
                    .apply {
                        setPackage("com.whatsapp")
                        data = Uri.parse("https://api.whatsapp.com/send?phone=$phone")
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
            )
        } catch (throwable: Throwable) {
            openStore("com.whatsapp")
        }
    }

    override fun launchTelegram(userId: String) {
        val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/$userId"))
        telegram.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(telegram)
        } catch (throwable: Throwable) {
            openStore("org.telegram.messenger")
        }
    }

    override fun launchViber(phone: String) {
        val uri = Uri.parse("smsto:$phone")
        val viberIntent = Intent(Intent.ACTION_SENDTO, uri)
        viberIntent.setPackage("com.viber.voip")
        viberIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(viberIntent)
        } catch (throwable: Throwable) {
            openStore("com.viber.voip")
        }
    }

    private fun openStore(packageName: String) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            ).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
    }

}