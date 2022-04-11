package com.kseniabl.cardstasks.utils

import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.kseniabl.cardtasks.R
import java.util.*


object CardTasksUtils {

    private var activityName = ""
    private var cardId = ""

    fun generateRandomKey(): String {
        val uuid = UUID.randomUUID()
        return uuid.toString()
    }

    fun getTextGradient(activity: Activity): Shader {
        val textShader: Shader = LinearGradient(
            0F, 0F, 0f, 70F,
            intArrayOf(activity.getColor(R.color.purple), activity.getColor(R.color.blue)),
            floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP
        )
        return textShader
    }

    fun isAppIsInBackground(context: Context): Boolean {
        var isInBackground = true
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningProcesses = am.runningAppProcesses
        for (processInfo in runningProcesses) {
            if (processInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (activeProcess in processInfo.pkgList) {
                    if (activeProcess == context.packageName) {
                        isInBackground = false
                    }
                }
            }
        }
        return isInBackground
    }

    fun specifyActivityRun(activityRun: String) {
        activityName = activityRun
    }

    fun setCardId(card_id: String) {
        cardId = card_id
    }

    fun getActivityRun(): Boolean {
        return activityName == "ChatScreenActivity"
    }

    fun getCardId(card_id: String): Boolean {
        return cardId == card_id
    }
}