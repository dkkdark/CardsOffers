package com.kseniabl.cardtasks.utils

import java.util.*

object CardTasksUtils {
    fun generateRandomKey(): String {
        val uuid = UUID.randomUUID()
        return uuid.toString()
    }
}