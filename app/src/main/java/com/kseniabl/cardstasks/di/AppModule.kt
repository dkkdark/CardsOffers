package com.kseniabl.cardstasks.di

import android.app.Application
import android.content.Context
import com.kseniabl.cardstasks.ui.base.CurrentUserClass
import com.kseniabl.cardstasks.ui.base.CurrentUserClassInterface
import com.kseniabl.cardstasks.ui.base.MessageSaveAndLoadInterface
import com.kseniabl.cardstasks.ui.base.MessagesSaveAndLoad
import com.kseniabl.cardstasks.ui.firebase_cloud_messaging.FirebaseInstanceIDService
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("http:///192.168.1.64/")
            .build()
    }

    @Provides
    fun provideMessageSaveAndLoad(context: Context): MessageSaveAndLoadInterface = MessagesSaveAndLoad(context)

    @Provides
    fun provideCurrentUserClass(context: Context): CurrentUserClassInterface = CurrentUserClass(context)
}