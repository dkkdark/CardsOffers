package com.kseniabl.cardsmarket.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.hardware.display.DisplayManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment
import com.kseniabl.cardsmarket.R
import kotlinx.android.synthetic.main.activity_profile.*
import android.view.Gravity

import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import co.lujun.androidtagview.TagView
import com.kseniabl.cardsmarket.ui.base.BaseFragment
import kotlinx.android.synthetic.main.dialog_change_name.*

import kotlinx.android.synthetic.main.dialog_change_profession.*


class ChangeProfessionDialogFragment: BaseDialog() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return inflater.inflate(R.layout.dialog_change_profession, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val specialization = arguments?.getString("specialization").toString()
        val description = arguments?.getString("description").toString()
        val tagsList = arguments?.getStringArrayList("tagList") as ArrayList<String>

        if (specialization != "—")
            dialogSpecializationText.setText(specialization)
        if (description != "—")
            dialogDescriptionText.setText(description)
        dialogTagContainer.tags = tagsList

        setTagsListeners()
        setButtonsClickListeners()
    }

    private fun setTagsListeners() {
        dialogTagContainer.setOnTagClickListener(object : TagView.OnTagClickListener {
            override fun onSelectedTagDrag(position: Int, text: String?) {
                TODO("Not yet implemented")
            }

            override fun onTagClick(position: Int, text: String?) {
                TODO("Not yet implemented")
            }

            override fun onTagCrossClick(position: Int) {
                dialogTagContainer.removeTag(position)
            }

            override fun onTagLongClick(position: Int, text: String?) {
                TODO("Not yet implemented")
            }
        })

        dialogAddTagButton.setOnClickListener {
            dialogTagContainer.addTag(dialogTagAddText.text.toString())
            dialogTagAddText.setText("")
        }
    }

    private fun setButtonsClickListeners() {
        dialogProfChangeButton.setOnClickListener {
            val tagsArray = arrayListOf<String>()
            tagsArray.addAll(dialogTagContainer.tags)
            setFragmentResult("ChangeProfessionDialogFragment", bundleOf(
                "resSpecialization" to dialogSpecializationText.text.toString(),
                "resDescription" to dialogDescriptionText.text.toString(),
                "resTagList" to tagsArray))
            dialog?.cancel()
        }
        dialogProfCloseButton.setOnClickListener { dialog?.cancel() }
    }

}