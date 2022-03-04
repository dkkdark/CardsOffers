package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.di.scope.ShowItemScope
import com.kseniabl.cardsmarket.ui.show_item.*
import dagger.Module
import dagger.Provides

@Module
class ShowItemActivityProvideModule {

    @Provides
    @ShowItemScope
    fun provideActivity(showItemActivity: ShowItemActivity) : ShowItemActivity = showItemActivity

    @Provides
    fun provideShowItemInteractor(showItemInteractor: ShowItemInteractor): ShowItemInteractorInterface = showItemInteractor

    @Provides
    fun provideShowItemPresenter(showItemPresenter: ShowItemPresenter<ShowItemView, ShowItemInteractorInterface>)
            : ShowItemPresenterInterface<ShowItemView> = showItemPresenter
}