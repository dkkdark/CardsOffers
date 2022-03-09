package com.kseniabl.cardsmarket.ui.settings

import android.content.Context
import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.BaseFragment
import com.kseniabl.cardsmarket.ui.dialogs.ChangeAdditionalInfoDialog
import com.kseniabl.cardsmarket.ui.dialogs.ChangeNameDialogFragment
import com.kseniabl.cardsmarket.ui.dialogs.ChangeProfessionDialogFragment
import com.kseniabl.cardsmarket.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_settings.logoutButton
import javax.inject.Inject
import android.widget.Toast
import com.kseniabl.cardsmarket.ui.base.CurrentUser


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException
import okhttp3.RequestBody

class SettingsFragmnet: BaseFragment(), SettingsView {

    @Inject
    lateinit var presenter: SettingsPresenterInterface<SettingsView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        presenter.setupUserBaseInfo(profileNameText, ratingBarSettings, beFreelancerCheckBox, emailChangeText)
        presenter.setupUserProfession(tagContainerLayout, specializationChangeText, descriptionSpecializationChangeText)
        presenter.setupUserAdditionalInfo(descriptionAddInfoChangeText, countryChangeText, cityChangeText, typeOfWorkChangeText)

        changeNameImage.setOnClickListener { showChangeNameDialog() }
        editProfessionImage.setOnClickListener { showChangeProfessionDialog() }
        editAdditionalInfoImage.setOnClickListener { showChangeAdditionalInfoDialog() }

        logoutButton.setOnClickListener { presenter.logoutUser() }

        changePasswordButton.setOnClickListener {  }

        val shader = getTextGradient()
        professionText.paint.shader = shader
        additionalInfoText.paint.shader = shader
        otherInfoText.paint.shader = shader
        changePasswordButton.paint.shader = shader
        logoutButton.paint.shader = shader
    }

    override fun onPause() {
        if (CurrentUser.getUser()?.id != null)
            presenter.changeIsFreelancerState(CurrentUser.getUser()!!.id, beFreelancerCheckBox.isChecked)
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.setFragmentResultListener("ChangeNameDialogFragment", this) { _, bundle ->
            val result = bundle.getString("resName")
            if (result != null && CurrentUser.getUser()?.id != null) {
                presenter.changeName(CurrentUser.getUser()!!.id, result)
                presenter.showUserName(profileNameText, result)
            }
        }

        childFragmentManager.setFragmentResultListener("ChangeProfessionDialogFragment", this) { _, bundle ->
            val resSpecialization = bundle.getString("resSpecialization")
            val resDescription = bundle.getString("resDescription")
            val resTagList = bundle.getStringArrayList("resTagList")
            if (resSpecialization != null && resDescription != null && resTagList != null && CurrentUser.getUser()?.id != null) {
                presenter.changeProfessionField(CurrentUser.getUser()!!.id, resSpecialization, resDescription, resTagList)
                presenter.showUserProfession(tagContainerLayout, specializationChangeText, descriptionSpecializationChangeText, resSpecialization, resDescription, resTagList)
            }
        }

        childFragmentManager.setFragmentResultListener("ChangeAdditionalInfoDialog", this) { _, bundle ->
            val resDescription = bundle.getString("resDescription")
            val resCountry = bundle.getString("resCountry")
            val resCity = bundle.getString("resCity")
            val resTypeOfWork = bundle.getString("resTypeOfWork")
            if (resDescription != null && resCountry != null && resCity != null && resTypeOfWork != null && CurrentUser.getUser()?.id != null) {
                presenter.changeAdditionalInfo(CurrentUser.getUser()!!.id, resDescription, resCountry, resCity, resTypeOfWork)
                presenter.showUserAdditionalInfo(descriptionAddInfoChangeText, countryChangeText, cityChangeText, typeOfWorkChangeText, resDescription, resCountry, resCity, resTypeOfWork)
            }
        }
    }

    private fun getTextGradient(): Shader {
        val textShader: Shader = LinearGradient(
            0F, 0F, 0f, 70F,
            intArrayOf(activity?.getColor(R.color.purple)!!, activity?.getColor(R.color.blue)!!),
            floatArrayOf(0f, 1f),
            TileMode.CLAMP
        )
        return textShader
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

    override fun editToken() {
        val sharedPref = activity?.getSharedPreferences("tokenSave", Context.MODE_PRIVATE)
        sharedPref?.edit()?.putString(getString(R.string.token_shared_pref), "")?.apply()
    }

    companion object {
        fun newInstance(): SettingsFragmnet = SettingsFragmnet()
    }

}