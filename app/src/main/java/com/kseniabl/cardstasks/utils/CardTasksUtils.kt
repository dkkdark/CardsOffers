package com.kseniabl.cardstasks.utils

import android.app.Activity
import android.graphics.LinearGradient
import android.graphics.Shader
import com.kseniabl.cardtasks.R
import java.util.*
import android.content.ComponentName

import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.ActivityManager.RunningTaskInfo
import android.content.Context

import android.os.Build




object CardTasksUtils {
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
}