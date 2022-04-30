package com.kseniabl.cardstasks

import android.app.Application
import android.util.Log
import com.kseniabl.cardtasks.di.AppComponent
import com.kseniabl.cardtasks.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import javax.inject.Inject

class CardTasksApplication : Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>


    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)

        RxJavaPlugins.setErrorHandler {
            Log.e("CardMarketApplication error", "RxJava error handler ${it.message}")
        }

    }
}
