package com.kseniabl.cardtasks.ui.freelancer_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_freelance_details_info.*
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

        setOnClickListeners()
    }

    companion object {
        fun newInstance(): InfoFreelancerFragment = InfoFreelancerFragment()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    private fun setOnClickListeners() {
        writeToFreelancerButton.setOnClickListener {
            (activity as FreelancerDetailsActivity).openChatScreenActivity()
        }
    }
}