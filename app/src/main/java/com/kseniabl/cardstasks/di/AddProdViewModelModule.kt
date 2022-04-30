package com.kseniabl.cardstasks.di

import androidx.lifecycle.ViewModel
import com.kseniabl.cardstasks.db.view_models.AddProdViewModel
import com.kseniabl.cardstasks.db.view_models.AllCardsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AddProdViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddProdViewModel::class)
    abstract fun bindAllProdsViewModel(addProdViewModel: AddProdViewModel): ViewModel
}