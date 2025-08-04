package com.furkanbarissonmezisik.secim2028sayac.widget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.glance.appwidget.updateAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserPresentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val pendingResult = goAsync()
        if (intent.action == Intent.ACTION_USER_PRESENT) {
            CoroutineScope(Dispatchers.Default).launch {
                Secim2028AppWidget().updateAll(context)
                pendingResult.finish()
            }
        } else {
            pendingResult.finish()
        }
    }
}