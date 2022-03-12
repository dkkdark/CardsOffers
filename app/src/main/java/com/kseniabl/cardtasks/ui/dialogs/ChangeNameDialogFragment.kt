package com.kseniabl.cardtasks.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.kseniabl.cardtasks.R
import kotlinx.android.synthetic.main.dialog_change_name.*

class ChangeNameDialogFragment: BaseDialog() {

    private var name = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        name = arguments?.getString("name").toString()

        return inflater.inflate(R.layout.dialog_change_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogNewNameText.setText(name)
        dialogNewNameField.requestFocus()
        dialogNewNameText.setSelection(name.length)

        changeButton.setOnClickListener {
            setFragmentResult("ChangeNameDialogFragment", bundleOf("resName" to dialogNewNameText.text.toString()))
            dialog?.cancel()
        }
        closeButton.setOnClickListener { dialog?.cancel() }
    }
}