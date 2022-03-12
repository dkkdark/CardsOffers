package com.kseniabl.cardtasks.ui.dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.kseniabl.cardtasks.R
import kotlinx.android.synthetic.main.dialog_create_new_task.*
import java.text.SimpleDateFormat
import java.util.*

class CreateNewTaskDialog: BaseDialog(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private var calendar = Calendar.getInstance()

    var id = ""
    var title = ""
    var description = ""
    var date = ""
    var cost: Int? = null
    var active = false
    var agreement = false
    var createTime: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        id = arguments?.getString("id") ?: ""
        title = arguments?.getString("title") ?: ""
        description = arguments?.getString("description") ?: ""
        date = arguments?.getString("date") ?: ""
        cost = arguments?.getInt("cost")
        active = arguments?.getBoolean("active") ?: false
        agreement = arguments?.getBoolean("agreement") ?: false
        createTime = arguments?.getLong("createTime") ?: 0

        return inflater.inflate(R.layout.dialog_create_new_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataToDialog(title, description, date, cost, active, agreement)
        showInitialDate()
        clickOnButtonsListeners()
        setListener()
    }

    private fun setDataToDialog(title: String, description: String, date: String, cost: Int?, active: Boolean, agreement: Boolean) {
        dialogTaskTitleText.setText(title)
        dialogTaskDescriptionText.setText(description)
        if (cost != null)
            dialogTaskCostText.setText(cost.toString())
        dialogChooseDate.text = date
        dialogCheckBox.isChecked = active
        dialogByAgreementCheckBox.isChecked = agreement
    }

    private fun showInitialDate() {
        val date = Calendar.getInstance()
        date.add(Calendar.WEEK_OF_YEAR, 1)
        val dateToFormat = date.time
        val time = Calendar.getInstance().time.time
        val sdfDate = SimpleDateFormat("MMM dd yyyy", Locale.getDefault())
        val sdfTime = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val formattedDate = sdfDate.format(dateToFormat)
        val formattedTime = sdfTime.format(time)
        dialogChooseDate.text = "$formattedDate $formattedTime"
    }

    private fun clickOnButtonsListeners() {
        dialogCreateChangeButton.setOnClickListener {
            dialogTaskTitleText.error = null
            dialogTaskCostText.error = null
            var returnOrNot = false

            if (dialogTaskTitleText.text?.isEmpty() == true) {
                dialogTaskTitleText.error = "You didn't specify title"
                returnOrNot = true
            }
            if (dialogTaskCostText.text?.isEmpty() == true && !dialogByAgreementCheckBox.isChecked) {
                dialogTaskCostText.error = "You didn't specify cost"
                returnOrNot = true
            }
            if (!returnOrNot) {
                setFragmentResult("CreateNewTaskDialog", bundleOf(
                    "resId" to id, "resTitle" to dialogTaskTitleText.text.toString(), "resDescription" to dialogTaskDescriptionText.text.toString(),
                    "resActive" to dialogCheckBox.isChecked, "resDate" to dialogChooseDate.text.toString(), "resCost" to dialogTaskCostText.text.toString(),
                    "resByAgreementValue" to dialogByAgreementCheckBox.isChecked, "resCreateTime" to createTime))
                dialog?.cancel()
            }
        }
        dialogCreateCloseButton.setOnClickListener { dialog?.cancel() }
    }

    private fun setListener() {
        dialogByAgreementCheckBox.setOnClickListener {
            if (dialogByAgreementCheckBox.isChecked) {
                dialogTaskCostField.visibility = View.GONE
                dollarText.visibility = View.GONE
            }
            else {
                dialogTaskCostField.visibility = View.VISIBLE
                dollarText.visibility = View.VISIBLE
            }
        }

        dialogChangeDateIcon.setOnClickListener {
            setDate()
        }
    }


    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateDateText()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minutes: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minutes)
        updateTimeText()
    }

    private fun setDate() {
        context?.let {
            val datePickerDialog = DatePickerDialog(context!!, this, Calendar.getInstance()[Calendar.YEAR],
                Calendar.getInstance()[Calendar.MONTH], Calendar.getInstance()[Calendar.DAY_OF_MONTH])
            datePickerDialog.show()
        }
    }

    private fun setTime() {
        TimePickerDialog(context, this,
            Calendar.getInstance()[Calendar.HOUR_OF_DAY],
            Calendar.getInstance()[Calendar.MINUTE], false).show()
    }

    private fun updateDateText() {
        val sdf = SimpleDateFormat("MMM dd yyyy", Locale.getDefault())
        date = sdf.format(calendar.time)
        setTime()
    }

    private fun updateTimeText() {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        var time = sdf.format(calendar.time.time)

        dialogChooseDate.text = "$date $time"
    }
}