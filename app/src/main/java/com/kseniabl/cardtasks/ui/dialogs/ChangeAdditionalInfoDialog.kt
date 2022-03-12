package com.kseniabl.cardtasks.ui.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.kseniabl.cardtasks.R
import kotlinx.android.synthetic.main.dialog_change_additional_info.*

class ChangeAdditionalInfoDialog: DialogFragment() {

    override fun onResume() {
        val wm = activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val window = dialog!!.window
        var width = 0

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = wm.currentWindowMetrics
            val windowInsets: WindowInsets = windowMetrics.windowInsets

            val insets = windowInsets.getInsetsIgnoringVisibility(
                WindowInsets.Type.navigationBars() or WindowInsets.Type.displayCutout()
            )
            val insetsWidth = insets.right + insets.left
            val b = windowMetrics.bounds
            width = b.width() - insetsWidth
        } else {
            val size = Point()
            @Suppress("DEPRECATION")
            val display = window!!.windowManager.defaultDisplay
            @Suppress("DEPRECATION")
            display.getSize(size)
            width = size.x
        }

        window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
        window?.setGravity(Gravity.CENTER)
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return inflater.inflate(R.layout.dialog_change_additional_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            dialogTypeOfWorkText.setText(typeOfWork)

        setButtonsClickListeners()
    }

    private fun setButtonsClickListeners() {
        dialogAdditionalChangeButton.setOnClickListener {
            setFragmentResult("ChangeAdditionalInfoDialog", bundleOf(
                "resDescription" to dialogAdditionalDescriptionText.text.toString(),
                "resCountry" to dialogCountryText.text.toString(),
                "resCity" to dialogCityText.text.toString(),
                "resTypeOfWork" to dialogTypeOfWorkText.text.toString())
            )
            dialog?.cancel()
            dialog?.dismiss()
        }
        dialogAdditionalCloseButton.setOnClickListener {
            dialog?.dismiss()
        }
    }
}