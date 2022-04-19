package com.kseniabl.cardstasks.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.kseniabl.cardstasks.utils.CardTasksUtils
import com.kseniabl.cardtasks.R
import kotlinx.android.synthetic.main.dialog_change_additional_info.*

class ChangeAdditionalInfoDialog: BaseDialog() {

    private val listForSpinner = arrayListOf("—", "Online", "Offline")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return inflater.inflate(R.layout.dialog_change_additional_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val shader = CardTasksUtils.getTextGradient(it)
            additionalInfoText.paint.shader = shader
        }

        setTypeOfWWorkSpinner()
        setButtonsClickListeners()

        val description = arguments?.getString("description")
        val country = arguments?.getString("country")
        val city = arguments?.getString("city")
        val typeOfWork = arguments?.getString("typeOfWork")

        if (description != "—")
            dialogAdditionalDescriptionText.setText(description)
        if (country != "—")
            dialogCountryText.setText(country)
        if (city != "—")
            dialogCityText.setText(city)
        if (typeOfWork != "—")
            dialogTypeOfWorkField.setSelection(listForSpinner.indexOf(typeOfWork))
    }

    private fun setButtonsClickListeners() {
        dialogAdditionalChangeButton.setOnClickListener {
            setFragmentResult("ChangeAdditionalInfoDialog", bundleOf(
                "resDescription" to dialogAdditionalDescriptionText.text.toString(),
                "resCountry" to dialogCountryText.text.toString(),
                "resCity" to dialogCityText.text.toString(),
                "resTypeOfWork" to dialogTypeOfWorkField.selectedItem.toString())
            )
            dialog?.cancel()
            dialog?.dismiss()
        }
        dialogAdditionalCloseButton.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun setTypeOfWWorkSpinner() {
        val adapter = object : ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, listForSpinner) {}
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dialogTypeOfWorkField?.adapter = adapter

        dialogTypeOfWorkField?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, itemSelected: View?, selectedItemPos: Int, selectedId: Long) {
                when (listForSpinner[selectedItemPos]) {
                    "—" -> {}
                    "Online" -> {}
                    "Offline" -> {}
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}