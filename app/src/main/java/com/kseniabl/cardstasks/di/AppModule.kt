package com.kseniabl.cardstasks.di

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import com.kseniabl.cardstasks.db.ChatRepository
import com.kseniabl.cardstasks.db.RepositoryInterface
import com.kseniabl.cardstasks.ui.base.*
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    fun createRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("http://kseniadk.pythonanywhere.com/")
            //.baseUrl("http:///192.168.1.67/")
            .build()
    }

    @Provides
    fun provideMessageSaveAndLoad(context: Context, repository: ChatRepository): MessageSaveAndLoadInterface = MessagesSaveAndLoad(context, repository)

    @Provides
    fun provideCurrentUserClass(context: Context): CurrentUserClassInterface = CurrentUserClass(context)

    @Provides
    fun provideChatListSaving(context: Context): ChatListSavingInterface = ChatListSaving(context)

    @Provides
    fun providesRepo(context: Context, retrofit: Retrofit, apiHolder: RetrofitApiHolder): RepositoryInterface = ChatRepository(context, retrofit, apiHolder)

    @Provides
    fun providesApiHolder(retrofit: Retrofit): RetrofitApiHolder = retrofit.create(RetrofitApiHolder::class.java)
}