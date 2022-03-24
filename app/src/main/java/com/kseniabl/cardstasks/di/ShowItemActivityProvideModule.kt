package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.ui.show_item.ShowItemInteractorInterface
import com.kseniabl.cardtasks.di.scope.ShowItemScope
import com.kseniabl.cardtasks.ui.show_item.*
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