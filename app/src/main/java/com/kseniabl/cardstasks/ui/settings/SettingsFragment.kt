package com.kseniabl.cardstasks.ui.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.github.drjacky.imagepicker.ImagePicker
import com.kseniabl.cardstasks.ui.base.BaseFragment
import com.kseniabl.cardstasks.ui.base.CurrentUserClassInterface
import com.kseniabl.cardstasks.ui.dialogs.ChangeAdditionalInfoDialog
import com.kseniabl.cardstasks.ui.dialogs.ChangeNameDialogFragment
import com.kseniabl.cardstasks.ui.login.LoginActivity
import com.kseniabl.cardstasks.utils.CardTasksUtils
import com.kseniabl.cardtasks.databinding.ActivityProfileBinding
import com.kseniabl.cardstasks.ui.dialogs.ChangeProfessionDialogFragment
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject


class SettingsFragment: BaseFragment(), SettingsView {

    @Inject
    lateinit var presenter: SettingsPresenterInterface<SettingsView>
    @Inject
    lateinit var currentUserClass: CurrentUserClassInterface

    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ActivityProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            presenter.setupUserBaseInfo(profileNameText, ratingBarSettings, beFreelancerCheckBox, emailChangeText)
            presenter.setupUserProfession(tagContainerLayout, specializationChangeText, descriptionSpecializationChangeText)
            presenter.setupUserAdditionalInfo(descriptionAddInfoChangeText, countryChangeText, cityChangeText, typeOfWorkChangeText)
            presenter.setupProfileImage(imageViewProfile)

            presenter.sendProfileInfoToServer()

            changeNameImage.setOnClickListener { showChangeNameDialog() }
            editProfessionImage.setOnClickListener { showChangeProfessionDialog() }
            editAdditionalInfoImage.setOnClickListener { showChangeAdditionalInfoDialog() }

            logoutButton.setOnClickListener { presenter.logoutUser() }

            changePasswordButton.setOnClickListener {  }

            imageViewProfile.setOnClickListener { chooseImg() }

            setGradient()

            beFreelancerCheckBox.setOnCheckedChangeListener { _, b ->
                currentUserClass.readSharedPref()?.id?.let { presenter.changeIsFreelancerState(it, b) }
                presenter.sendProfileInfoToServer()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.setFragmentResultListener("ChangeNameDialogFragment", this) { _, bundle ->
            val result = bundle.getString("resName")
            if (result != null && currentUserClass.readSharedPref() != null) {
                presenter.changeName(currentUserClass.readSharedPref()!!.id, result)
                presenter.sendProfileInfoToServer()
                presenter.showUserName(binding.profileNameText, result)
            }
        }

        childFragmentManager.setFragmentResultListener("ChangeProfessionDialogFragment", this) { _, bundle ->
            val resSpecialization = bundle.getString("resSpecialization")
            val resDescription = bundle.getString("resDescription")
            val resTagList = bundle.getStringArrayList("resTagList")
            if (resSpecialization != null && resDescription != null && resTagList != null && currentUserClass.readSharedPref() != null) {
                presenter.changeProfessionField(currentUserClass.readSharedPref()!!.id, resSpecialization, resDescription, resTagList)
                presenter.sendProfileInfoToServer()
                presenter.showUserProfession(binding.tagContainerLayout, binding.specializationChangeText, binding.descriptionSpecializationChangeText, resSpecialization, resDescription, resTagList)
            }
        }

        childFragmentManager.setFragmentResultListener("ChangeAdditionalInfoDialog", this) { _, bundle ->
            val resDescription = bundle.getString("resDescription")
            val resCountry = bundle.getString("resCountry")
            val resCity = bundle.getString("resCity")
            val resTypeOfWork = bundle.getString("resTypeOfWork")
            if (resDescription != null && resCountry != null && resCity != null && resTypeOfWork != null && currentUserClass.readSharedPref() != null) {
                presenter.changeAdditionalInfo(currentUserClass.readSharedPref()!!.id, resDescription, resCountry, resCity, resTypeOfWork)
                presenter.sendProfileInfoToServer()
                presenter.showUserAdditionalInfo(binding.descriptionAddInfoChangeText, binding.countryChangeText, binding.cityChangeText, binding.typeOfWorkChangeText, resDescription, resCountry, resCity, resTypeOfWork)
            }
        }
    }

    private fun setGradient() {
        activity?.let {
            binding.apply {
                val shader = CardTasksUtils.getTextGradient(it)
                professionText.paint.shader = shader
                additionalInfoText.paint.shader = shader
                otherInfoText.paint.shader = shader
                changePasswordButton.paint.shader = shader
                logoutButton.paint.shader = shader
            }
        }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
        _binding = null
    }

    override fun openLoginActivity() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun showChangeNameDialog() {
        val args = Bundle()
        args.putString("name", binding.profileNameText.text.toString())
        val dialog = ChangeNameDialogFragment()
        dialog.arguments = args
        dialog.show(childFragmentManager, "ChangeNameDialogFragment")
    }

    private fun showChangeProfessionDialog() {
        val args = Bundle()
        args.putString("specialization", binding.specializationChangeText.text.toString())
        args.putString("description", binding.descriptionSpecializationChangeText.text.toString())
        val tagsArray = arrayListOf<String>()
        tagsArray.addAll(binding.tagContainerLayout.tags)
        args.putStringArrayList("tagList", tagsArray)

        val dialog = ChangeProfessionDialogFragment()
        dialog.arguments = args
        dialog.show(childFragmentManager, "ChangeProfessionDialogFragment")
    }

    private fun showChangeAdditionalInfoDialog() {
        val args = Bundle()
        args.putString("description", binding.descriptionAddInfoChangeText.text.toString())
        args.putString("country", binding.countryChangeText.text.toString())
        args.putString("city", binding.cityChangeText.text.toString())
        args.putString("typeOfWork", binding.typeOfWorkChangeText.text.toString())

        val dialog = ChangeAdditionalInfoDialog()
        dialog.arguments = args
        dialog.show(childFragmentManager, "ChangeAdditionalInfoDialog")
    }

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }


    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data!!
            if (currentUserClass.readSharedPref()?.id != null && uri.path != null) {
                val file = File(uri.path!!)
                val bytes = file.readBytes()
                val part = MultipartBody.Part.createFormData("pic", file.name, RequestBody.create(MediaType.parse("image/*"), file))
                presenter.uploadImage(currentUserClass.readSharedPref()!!.id, part, binding.imageViewProfile, uri, bytes)
            }
        }
    }

    private fun chooseImg() {
        ImagePicker.with(requireActivity())
            .crop(1F, 1F)
            .cropOval()
            .cropFreeStyle()
            .galleryMimeTypes(
                mimeTypes = arrayOf(
                    "image/png",
                    "image/jpg",
                    "image/jpeg"
                )
            )
            .createIntentFromDialog { launcher.launch(it) }
    }
}