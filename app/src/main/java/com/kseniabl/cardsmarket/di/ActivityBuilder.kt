package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.create_prod.CreateProdActivity
import com.kseniabl.cardsmarket.ui.main.MainActivity
import com.kseniabl.cardsmarket.ui.login.LoginActivity
import com.kseniabl.cardsmarket.ui.show_item.ShowItemActivity
import com.kseniabl.cardsmarket.ui.splash.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (AddProdFragmentModule::class), (AllOffersFragmentModule::class), (SettingsFragmentModule::class), (AllProdsFragmentModule::class), (ExecutorFragmentModule::class), (AddTasksFragmentModule::class), (DraftFragmentModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(SplashScreenModule::class)])
    abstract fun bindSplashActivity(): SplashScreenActivity

    @ContributesAndroidInjector(modules = [(CreateProdActivityModule::class)])
    abstract fun bindCreateProdActivity(): CreateProdActivity

    @ContributesAndroidInjector(modules = [(ShowItemActivityProvideModule::class)])
    abstract fun bindShowItemFragmentModule(): ShowItemActivity
}