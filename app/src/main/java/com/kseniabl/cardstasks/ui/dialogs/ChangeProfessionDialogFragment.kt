package com.kseniabl.cardstasks.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import co.lujun.androidtagview.TagView
import com.kseniabl.cardstasks.utils.CardTasksUtils
import com.kseniabl.cardtasks.databinding.DialogChangeProfessionBinding


class ChangeProfessionDialogFragment: BaseDialog() {

    private var _binding: DialogChangeProfessionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        _binding = DialogChangeProfessionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val shader = CardTasksUtils.getTextGradient(it)
            binding.dialogProfessionText.paint.shader = shader
        }

        val specialization = arguments?.getString("specialization").toString()
        val description = arguments?.getString("description").toString()
        val tagsList = arguments?.getStringArrayList("tagList") as ArrayList<String>

        if (specialization != "—")
            binding.dialogSpecializationText.setText(specialization)
        if (description != "—")
            binding.dialogDescriptionText.setText(description)
        binding.dialogTagContainer.tags = tagsList

        setTagsListeners()
        setButtonsClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setTagsListeners() {
        binding.dialogTagContainer.setOnTagClickListener(object : TagView.OnTagClickListener {
            override fun onSelectedTagDrag(position: Int, text: String?) {
                TODO("Not yet implemented")
            }

            override fun onTagClick(position: Int, text: String?) {
                TODO("Not yet implemented")
            }

            override fun onTagCrossClick(position: Int) {
                binding.dialogTagContainer.removeTag(position)
            }

            override fun onTagLongClick(position: Int, text: String?) {
                TODO("Not yet implemented")
            }
        })

        binding.apply {
            dialogAddTagButton.setOnClickListener {
                dialogTagContainer.addTag(dialogTagAddText.text.toString())
                dialogTagAddText.setText("")
            }
        }
    }

    private fun setButtonsClickListeners() {
        binding.apply {
            dialogProfChangeButton.setOnClickListener {
                val tagsArray = arrayListOf<String>()
                tagsArray.addAll(dialogTagContainer.tags)
                setFragmentResult(
                    "ChangeProfessionDialogFragment", bundleOf(
                        "resSpecialization" to dialogSpecializationText.text.toString(),
                        "resDescription" to dialogDescriptionText.text.toString(),
                        "resTagList" to tagsArray
                    )
                )
                dialog?.cancel()
            }
            dialogProfCloseButton.setOnClickListener { dialog?.cancel() }
        }
    }

}