package com.kseniabl.cardstasks.ui.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.dialogs.ChangeProfessionDialogFragment
import kotlinx.android.synthetic.main.activity_profile.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import javax.inject.Inject


class SettingsFragment: BaseFragment(), SettingsView {

    @Inject
    lateinit var presenter: SettingsPresenterInterface<SettingsView>
    @Inject
    lateinit var currentUserClass: CurrentUserClassInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        presenter.setupUserBaseInfo(profileNameText, ratingBarSettings, beFreelancerCheckBox, emailChangeText)
        presenter.setupUserProfession(tagContainerLayout, specializationChangeText, descriptionSpecializationChangeText)
        presenter.setupUserAdditionalInfo(descriptionAddInfoChangeText, countryChangeText, cityChangeText, typeOfWorkChangeText)
        presenter.setupProfileImage(imageViewProfile)

        changeNameImage.setOnClickListener { showChangeNameDialog() }
        editProfessionImage.setOnClickListener { showChangeProfessionDialog() }
        editAdditionalInfoImage.setOnClickListener { showChangeAdditionalInfoDialog() }

        logoutButton.setOnClickListener { presenter.logoutUser() }

        changePasswordButton.setOnClickListener {  }

        imageViewProfile.setOnClickListener { chooseImg() }

        setGradient()
    }

    override fun onPause() {
        currentUserClass.readSharedPref()?.id?.let { presenter.changeIsFreelancerState(it, beFreelancerCheckBox.isChecked) }
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.setFragmentResultListener("ChangeNameDialogFragment", this) { _, bundle ->
            val result = bundle.getString("resName")
            if (result != null && currentUserClass.readSharedPref() != null) {
                presenter.changeName(currentUserClass.readSharedPref()!!.id, result)
                presenter.showUserName(profileNameText, result)
            }
        }

        childFragmentManager.setFragmentResultListener("ChangeProfessionDialogFragment", this) { _, bundle ->
            val resSpecialization = bundle.getString("resSpecialization")
            val resDescription = bundle.getString("resDescription")
            val resTagList = bundle.getStringArrayList("resTagList")
            if (resSpecialization != null && resDescription != null && resTagList != null && currentUserClass.readSharedPref() != null) {
                presenter.changeProfessionField(currentUserClass.readSharedPref()!!.id, resSpecialization, resDescription, resTagList)
                presenter.showUserProfession(tagContainerLayout, specializationChangeText, descriptionSpecializationChangeText, resSpecialization, resDescription, resTagList)
            }
        }

        childFragmentManager.setFragmentResultListener("ChangeAdditionalInfoDialog", this) { _, bundle ->
            val resDescription = bundle.getString("resDescription")
            val resCountry = bundle.getString("resCountry")
            val resCity = bundle.getString("resCity")
            val resTypeOfWork = bundle.getString("resTypeOfWork")
            if (resDescription != null && resCountry != null && resCity != null && resTypeOfWork != null && currentUserClass.readSharedPref() != null) {
                presenter.changeAdditionalInfo(currentUserClass.readSharedPref()!!.id, resDescription, resCountry, resCity, resTypeOfWork)
                presenter.showUserAdditionalInfo(descriptionAddInfoChangeText, countryChangeText, cityChangeText, typeOfWorkChangeText, resDescription, resCountry, resCity, resTypeOfWork)
            }
        }
    }

    private fun setGradient() {
        activity?.let {
            val shader = CardTasksUtils.getTextGradient(it)
            professionText.paint.shader = shader
            additionalInfoText.paint.shader = shader
            otherInfoText.paint.shader = shader
            changePasswordButton.paint.shader = shader
            logoutButton.paint.shader = shader
        }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun openLoginActivity() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun showChangeNameDialog() {
        val args = Bundle()
        args.putString("name", profileNameText.text.toString())
        val dialog = ChangeNameDialogFragment()
        dialog.arguments = args
        dialog.show(childFragmentManager, "ChangeNameDialogFragment")
    }

    private fun showChangeProfessionDialog() {
        val args = Bundle()
        args.putString("specialization", specializationChangeText.text.toString())
        args.putString("description", descriptionSpecializationChangeText.text.toString())
        val tagsArray = arrayListOf<String>()
        tagsArray.addAll(tagContainerLayout.tags)
        args.putStringArrayList("tagList", tagsArray)

        val dialog = ChangeProfessionDialogFragment()
        dialog.arguments = args
        dialog.show(childFragmentManager, "ChangeProfessionDialogFragment")
    }

    private fun showChangeAdditionalInfoDialog() {
        val args = Bundle()
        args.putString("description", descriptionAddInfoChangeText.text.toString())
        args.putString("country", countryChangeText.text.toString())
        args.putString("city", cityChangeText.text.toString())
        args.putString("typeOfWork", typeOfWorkChangeText.text.toString())

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
                val part = MultipartBody.Part.createFormData("pic", file.name, RequestBody.create(MediaType.parse("image/*"), file))
                presenter.uploadImage(currentUserClass.readSharedPref()!!.id, part, imageViewProfile, uri)
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