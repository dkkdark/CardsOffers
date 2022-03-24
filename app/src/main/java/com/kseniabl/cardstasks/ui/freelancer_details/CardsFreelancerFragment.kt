package com.kseniabl.cardtasks.ui.freelancer_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kseniabl.cardtasks.R
import com.kseniabl.cardstasks.ui.base.BaseFragment
import javax.inject.Inject

class CardsFreelancerFragment: BaseFragment(), CardsFreelancerView {

    @Inject
    lateinit var presenter: CardsFreelancerPresenterInterface<CardsFreelancerView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cards_freelancer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): CardsFreelancerFragment = CardsFreelancerFragment()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }
}