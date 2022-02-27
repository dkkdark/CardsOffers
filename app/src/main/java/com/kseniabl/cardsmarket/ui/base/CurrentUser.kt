package com.kseniabl.cardsmarket.ui.base

import com.kseniabl.cardsmarket.ui.models.UserModel

object CurrentUser {

    private var user: UserModel? = null

    fun setUser(userModel: UserModel) {
        user = userModel
    }

    fun changeName(name: String) {
        user?.username = name
    }

    fun changeProfession(description: String, specialization: String, tags: List<String>) {
        user?.profession?.description = description
        user?.profession?.specialization = specialization
        user?.profession?.tags = tags
    }

    fun changeAdditional(description: String, country: String, city: String, type: String) {
        user?.additionalInfo?.description = description
        user?.additionalInfo?.country = country
        user?.additionalInfo?.city = city
        user?.additionalInfo?.typeOfWork = type
    }

    fun changeIsExecutorState(state: Boolean) {
        user?.isExecutor = state
    }

    fun getUser(): UserModel? {
        return user
    }
}