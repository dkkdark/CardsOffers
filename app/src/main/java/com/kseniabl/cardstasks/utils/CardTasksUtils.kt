package com.kseniabl.cardtasks.utils

import android.app.Activity
import android.graphics.LinearGradient
import android.graphics.Shader
import com.kseniabl.cardtasks.R
import java.util.*

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
}