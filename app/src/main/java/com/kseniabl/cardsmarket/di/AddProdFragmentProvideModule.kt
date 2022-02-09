package com.kseniabl.cardsmarket.di

import android.content.Context
import com.google.android.flexbox.FlexboxLayoutManager
import com.kseniabl.cardsmarket.di.scope.AddProdFragmentScope
import com.kseniabl.cardsmarket.ui.add_prod.*
import dagger.Module
import dagger.Provides

@Module
class AddProdFragmentProvideModule {

    @Provides
    @AddProdFragmentScope
    fun provideActivity(addProdFragment: AddProdFragment) : AddProdFragment = addProdFragment

    @Provides
    fun provideAddProdInteractor(addProdInteractor: AddProdInteractor): AddProdInteractorInterface = addProdInteractor

    @Provides
    fun provideAddProdPresenter(addProdPresenter: AddProdPresenter<AddProdView, AddProdInteractorInterface>)
            : AddProdPresenterCardModelInterface<AddProdView> = addProdPresenter

    @Provides
    fun provideAddProdsAdapter(addProdPresenter: AddProdPresenter<AddProdView, AddProdInteractorInterface>, context: Context, addProdFragment: AddProdFragment)
            : AddProdsAdapter = AddProdsAdapter(addProdPresenter, context, addProdFragment)

    @Provides
    fun provideLayoutManager(fragment: AddProdFragment): FlexboxLayoutManager = FlexboxLayoutManager(fragment.context)

}