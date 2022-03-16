package com.kseniabl.cardstasks.ui.freelancer_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.base.BaseFragment
import com.kseniabl.cardtasks.ui.freelancer_details.InfoFreelancePresenterInterface
import com.kseniabl.cardtasks.ui.freelancer_details.InfoFreelanceView
import com.kseniabl.cardtasks.utils.CardTasksUtils
import kotlinx.android.synthetic.main.fragment_freelance_details_info.*
import javax.inject.Inject

class InfoFreelancerFragment: BaseFragment(), InfoFreelanceView {

    @Inject
    lateinit var presenter: InfoFreelancePresenterInterface<InfoFreelanceView>

    private var freelancer: UserModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_freelance_details_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        freelancer = arguments?.getSerializable("item") as UserModel

        setOnClickListeners()
        setTextFields()
        setGradient()
    }

    companion object {
        fun newInstance(): InfoFreelancerFragment = InfoFreelancerFragment()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    private fun setGradient() {
        activity?.let {
            val shader = CardTasksUtils.getTextGradient(it)
            professionTextFreelancerDetails.paint.shader = shader
            additionalInfoTextFreelancerDetails.paint.shader = shader
            writeToFreelancerButton.paint.shader = shader
        }
    }

    private fun setTextFields() {
        if (freelancer != null) {
            tagContainerLayoutFreelancerDetails.tag = freelancer!!.profession.tags
            if (freelancer!!.profession.specialization != "")
                specializationChangeTextFreelancerDetails.text = freelancer!!.profession.specialization
            else
                specializationChangeTextFreelancerDetails.text = "—"
            if (freelancer!!.profession.description != "")
                descriptionSpecializationChangeTextFreelancerDetails.text = freelancer!!.profession.description
            else
                descriptionSpecializationChangeTextFreelancerDetails.text = "—"
            if (freelancer!!.additionalInfo.description != "")
                descriptionAddInfoChangeTextFreelancerDetails.text = freelancer!!.additionalInfo.description
            else
                descriptionAddInfoChangeTextFreelancerDetails.text = "—"
            if (freelancer!!.additionalInfo.country != "")
                countryChangeTextFreelancerDetails.text = freelancer!!.additionalInfo.country
            else
                countryChangeTextFreelancerDetails.text = "—"
            if (freelancer!!.additionalInfo.city != "")
                cityChangeTextFreelancerDetails.text = freelancer!!.additionalInfo.city
            else
                cityChangeTextFreelancerDetails.text = "—"
            if (freelancer!!.additionalInfo.typeOfWork != "")
                typeOfWorkChangeTextFreelancerDetails.text = freelancer!!.additionalInfo.typeOfWork
            else
                typeOfWorkChangeTextFreelancerDetails.text = "—"
        }
    }

    private fun setOnClickListeners() {
        writeToFreelancerButton.setOnClickListener {
            (activity as FreelancerDetailsActivity).openChatScreenActivity()
        }
    }
}