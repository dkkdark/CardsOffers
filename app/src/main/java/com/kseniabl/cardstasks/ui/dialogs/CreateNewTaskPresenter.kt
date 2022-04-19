package com.kseniabl.cardstasks.ui.dialogs

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.core.FlowableSubscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

class CreateNewTaskPresenter @Inject constructor(val context: Context, val interactor: CreateNewTaskInteractorInterface): CreateNewTaskPresenterInterface {

    override fun deleteTask(dialog: CreateNewTaskDialog, alert: DialogInterface, id: String, cardId: String) {
        interactor.deleteTaskFromServer(id, cardId)
            .subscribe(object : FlowableSubscriber<MessageModel> {
            override fun onSubscribe(s: Subscription) {
                s.request(Long.MAX_VALUE)
            }

            override fun onNext(m: MessageModel) {
                if (m.message == "success") {
                    Log.e("qqq", "card deleted")
                    dialog.setFragmentResult("deleteResultCreateTask", bundleOf("userId" to id, "cardId" to cardId))
                    alert.dismiss()
                    dialog.dismiss()
                }
                else {
                    Log.e("qqq", "deleteTaskFromServer onNext: response from server is not success")
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onError(t: Throwable) {
                Log.e("qqq", "deleteTaskFromServer onError: ${t.message}")
            }

            override fun onComplete() {
            }

        })
    }
}