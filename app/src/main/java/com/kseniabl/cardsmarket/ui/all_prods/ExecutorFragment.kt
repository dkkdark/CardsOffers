package com.kseniabl.cardsmarket.ui.all_prods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_active_tasks.*
import kotlinx.android.synthetic.main.fragment_executors.*
import javax.inject.Inject
import javax.inject.Provider

class ExecutorFragment: BaseFragment(), ExecutorView {

    @Inject
    lateinit var presenter: ExecutorPresenterCardModelInterface<ExecutorView>
    @Inject
    lateinit var layoutManager: Provider<FlexboxLayoutManager>
    @Inject
    lateinit var adapter: ExecutorsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_executors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadExecutors()
    }

    private fun setupRecyclerView() {
        val flexlayoutManager = layoutManager.get()
        flexlayoutManager.flexDirection = FlexDirection.ROW;
        flexlayoutManager.justifyContent = JustifyContent.SPACE_AROUND;

        executors_recycler_view.layoutManager = flexlayoutManager
        executors_recycler_view.adapter = adapter
        executors_recycler_view.setHasFixedSize(true)
        executors_recycler_view.setItemViewCacheSize(20)
    }

    override fun provideAdapter(): ExecutorsAdapter {
        return adapter
    }

    companion object {
        fun newInstance(): ExecutorFragment = ExecutorFragment()
    }
}