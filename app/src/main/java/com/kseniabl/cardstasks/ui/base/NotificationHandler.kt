package com.kseniabl.cardstasks.ui.base

class NotificationHandler {

    private var listener: OnNotificationHandlerListener? = null

    interface OnNotificationHandlerListener {
        fun onNotificationHandler()
    }

    fun setOnNotificationHandlerListener(parameter: OnNotificationHandlerListener) {
        listener = parameter
    }

    fun notificationHandle() {
        listener?.onNotificationHandler()
    }
}