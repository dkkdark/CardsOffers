package com.kseniabl.cardtasks.ui.add_prod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.base.BaseFragment
import com.kseniabl.cardtasks.ui.base.CurrentUser
import com.kseniabl.cardtasks.ui.dialogs.CreateNewTaskDialog
import kotlinx.android.synthetic.main.fragment_add_card.*
import java.util.*
import javax.inject.Inject

class AddTasksFragment: BaseFragment(), AddTasksView {

    @Inject
    lateinit var presenter: AddTasksPresenterInterface<AddTasksView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        openActiveFragment()
        setButtonsClickListener()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.setFragmentResultListener("CreateNewTaskDialog", this) { _, bundle ->
            val resTitle = bundle.getString("resTitle")
            val resDescription = bundle.getString("resDescription")
            val resActive = bundle.getBoolean("resActive")
            val resDate = bundle.getString("resDate")
            val resCost = bundle.getString("resCost")
            val resByAgreementValue = bundle.getBoolean("resByAgreementValue")
            val currentTime = Calendar.getInstance().time.time

            if (resTitle != null && resDescription != null && resDate != null && resCost != null && CurrentUser.getUser()?.id != null) {
                val cost = try {
                    resCost.toInt()
                } catch (e: NumberFormatException) {
                    0
                }
                presenter.addUsersNewCard(CurrentUser.getUser()!!.id, resTitle, resDescription, resActive, resDate,
                    cost, resByAgreementValue, currentTime)
            }
        }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    private fun openActiveFragment() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(R.id.fragmentContainerAddCard, AddProdFragment.newInstance())
        }
    }

    private fun openDraftFragment() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(R.id.fragmentContainerAddCard, DraftFragment.newInstance())
        }
    }

    private fun showCreateTaskDialog() {
        val args = Bundle()
        //args.putString("name", profileNameText.text.toString())
        val dialog = CreateNewTaskDialog()
        dialog.arguments = args
        dialog.show(childFragmentManager, "CreateNewTaskDialog")
    }

    private fun setButtonsClickListener() {
        activeButton.setOnClickListener { openActiveFragment() }
        draftButton.setOnClickListener { openDraftFragment() }
        addCardFab.setOnClickListener { showCreateTaskDialog() }
    }

    companion object {
        fun newInstance(): AddTasksFragment = AddTasksFragment()
    }
}