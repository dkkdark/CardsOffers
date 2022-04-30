package com.kseniabl.cardstasks.di

import androidx.lifecycle.ViewModelProvider
import com.kseniabl.cardstasks.db.view_models.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelProvideModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}