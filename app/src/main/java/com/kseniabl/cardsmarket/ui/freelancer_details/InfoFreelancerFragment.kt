package com.kseniabl.cardsmarket.ui.freelancer_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.BaseFragment
import com.kseniabl.cardsmarket.ui.settings.SettingsFragmnet
import com.kseniabl.cardsmarket.ui.settings.SettingsPresenterInterface
import com.kseniabl.cardsmarket.ui.settings.SettingsView
import javax.inject.Inject

class InfoFreelancerFragment: BaseFragment(), InfoFreelanceView {

    @Inject
    lateinit var presenter: InfoFreelancePresenterInterface<InfoFreelanceView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_freelance_details_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): InfoFreelancerFragment = InfoFreelancerFragment()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }
}