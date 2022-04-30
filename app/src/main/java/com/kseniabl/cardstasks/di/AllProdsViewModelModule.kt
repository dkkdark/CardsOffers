package com.kseniabl.cardstasks.di

import androidx.lifecycle.ViewModel
import com.kseniabl.cardstasks.db.view_models.AllCardsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AllProdsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AllCardsViewModel::class)
    abstract fun bindAllProdsViewModel(allCardsViewModel: AllCardsViewModel): ViewModel
}