package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.freelancer_details.FreelancerDetailsActivity
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

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (AddProdFragmentModule::class), (AllOffersFragmentModule::class), (SettingsFragmentModule::class), (AllProdsFragmentModule::class), (FreelancerFragmentModule::class), (AddTasksFragmentModule::class), (DraftFragmentModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(SplashScreenModule::class)])
    abstract fun bindSplashActivity(): SplashScreenActivity

    @ContributesAndroidInjector(modules = [(ShowItemActivityProvideModule::class)])
    abstract fun bindShowItemActivityModule(): ShowItemActivity

    @ContributesAndroidInjector(modules = [(FreelancerDetailsActivityModule::class), (InfoFreelanceFragmentModule::class), (CardsFreelancerModule::class)])
    abstract fun bindShowFreelancerDetailsActivityModule(): FreelancerDetailsActivity
}