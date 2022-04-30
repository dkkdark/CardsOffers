package com.kseniabl.cardstasks.ui.freelancer_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardtasks.R
import com.kseniabl.cardstasks.ui.base.BaseFragment
import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardtasks.ui.freelancer_details.InfoFreelancePresenterInterface
import com.kseniabl.cardtasks.ui.freelancer_details.InfoFreelanceView
import com.kseniabl.cardstasks.utils.CardTasksUtils
import com.kseniabl.cardtasks.databinding.FragmentActiveTasksBinding
import com.kseniabl.cardtasks.databinding.FragmentFreelanceDetailsInfoBinding
import javax.inject.Inject

class InfoFreelancerFragment: BaseFragment(), InfoFreelanceView {

    @Inject
    lateinit var presenter: InfoFreelancePresenterInterface<InfoFreelanceView>

    private var freelancer: FreelancerModel? = null

    private var _binding: FragmentFreelanceDetailsInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFreelanceDetailsInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        freelancer = arguments?.getSerializable("item") as FreelancerModel

        setTextFields()
        setGradient()
    }

    companion object {
        fun newInstance(): InfoFreelancerFragment = InfoFreelancerFragment()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
        _binding = null
    }

    private fun setGradient() {
        activity?.let {
            binding.apply {
                val shader = CardTasksUtils.getTextGradient(it)
                professionTextFreelancerDetails.paint.shader = shader
                additionalInfoTextFreelancerDetails.paint.shader = shader
            }
        }
    }

    private fun setTextFields() {
        if (freelancer != null) {
            binding.apply {
                tagContainerLayoutFreelancerDetails.tag = freelancer!!.profession.tags
                checkIsEmpty(specializationChangeTextFreelancerDetails, freelancer!!.profession.specialization)
                checkIsEmpty(descriptionSpecializationChangeTextFreelancerDetails, freelancer!!.profession.description)
                checkIsEmpty(descriptionAddInfoChangeTextFreelancerDetails, freelancer!!.additionalInfo.description)
                checkIsEmpty(countryChangeTextFreelancerDetails, freelancer!!.additionalInfo.country)
                checkIsEmpty(cityChangeTextFreelancerDetails, freelancer!!.additionalInfo.city)
                checkIsEmpty(typeOfWorkChangeTextFreelancerDetails, freelancer!!.additionalInfo.typeOfWork)
            }
        }
    }

    private fun checkIsEmpty(textView: TextView, value: String) {
        if (value.isNotEmpty())
            textView.text = value
        else
            textView.text = "—"
    }
}