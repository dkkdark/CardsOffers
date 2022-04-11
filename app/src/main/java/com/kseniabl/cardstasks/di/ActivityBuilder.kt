package com.kseniabl.cardstasks.di

import com.kseniabl.cardstasks.ui.chat.ChatScreenActivity
import com.kseniabl.cardstasks.ui.firebase_cloud_messaging.FirebaseInstanceIDService
import com.kseniabl.cardstasks.ui.freelancer_details.FreelancerDetailsActivity
import com.kseniabl.cardstasks.ui.main.MainActivity
import com.kseniabl.cardtasks.di.*
import com.kseniabl.cardstasks.ui.login.LoginActivity
import com.kseniabl.cardstasks.ui.show_item.ShowItemActivity
import com.kseniabl.cardstasks.ui.splash.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (AddProdFragmentModule::class), (AllOffersFragmentModule::class), (SettingsFragmentModule::class), (AllProdsFragmentModule::class), (FreelancerFragmentModule::class), (AddTasksFragmentModule::class), (DraftFragmentModule::class), (ChatListFragmentModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(SplashScreenModule::class)])
    abstract fun bindSplashActivity(): SplashScreenActivity

    @ContributesAndroidInjector(modules = [(ShowItemActivityProvideModule::class)])
    abstract fun bindShowItemActivityModule(): ShowItemActivity

    @ContributesAndroidInjector(modules = [(FreelancerDetailsActivityModule::class), (InfoFreelanceFragmentModule::class), (CardsFreelancerModule::class)])
    abstract fun bindShowFreelancerDetailsActivityModule(): FreelancerDetailsActivity

    @ContributesAndroidInjector(modules = [(ChatScreenActivityModule::class)])
    abstract fun bindShowChatScreenActivityModule(): ChatScreenActivity

    @ContributesAndroidInjector
    abstract fun fcmMessaging(): FirebaseInstanceIDService
}