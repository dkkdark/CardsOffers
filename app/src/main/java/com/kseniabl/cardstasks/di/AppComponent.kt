package com.kseniabl.cardtasks.di

import android.app.Application
import com.kseniabl.cardstasks.di.ActivityBuilder
import com.kseniabl.cardstasks.di.AppModule
import com.kseniabl.cardtasks.CardTasksApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: CardTasksApplication)
}