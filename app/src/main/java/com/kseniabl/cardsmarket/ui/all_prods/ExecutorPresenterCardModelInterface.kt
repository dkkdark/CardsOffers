package com.kseniabl.cardsmarket.ui.all_prods

import com.kseniabl.cardsmarket.ui.base.AdapterFunctionsCardModelInterface
import com.kseniabl.cardsmarket.ui.base.PresenterInterface

interface ExecutorPresenterCardModelInterface<V: ExecutorView>: PresenterInterface<V>, AdapterFunctionsExecutorModelInterface {
}