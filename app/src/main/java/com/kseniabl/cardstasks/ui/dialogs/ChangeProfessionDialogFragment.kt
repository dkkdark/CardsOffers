package com.kseniabl.cardtasks.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import com.kseniabl.cardtasks.R

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import co.lujun.androidtagview.TagView
import com.kseniabl.cardstasks.ui.dialogs.BaseDialog
import com.kseniabl.cardstasks.utils.CardTasksUtils

import kotlinx.android.synthetic.main.dialog_change_profession.*


class ChangeProfessionDialogFragment: BaseDialog() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return inflater.inflate(R.layout.dialog_change_profession, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val shader = CardTasksUtils.getTextGradient(it)
            dialogProfessionText.paint.shader = shader
        }

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