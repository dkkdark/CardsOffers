package com.kseniabl.cardsmarket.ui.show_item

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.google.android.material.transition.MaterialContainerTransform
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.BaseFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_show_prod.*
import javax.inject.Inject

class ShowItemFragment: BaseFragment(), ShowItemView {

    @Inject
    lateinit var presenter: ShowItemPresenterInterface<ShowItemView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_show_prod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        val img = arguments?.getString("img")
        val name = arguments?.getString("name")
        val descr = arguments?.getString("descr")

        showProdText.text = name
        showProdDescr.text = descr
        if (img.isNullOrEmpty()) {
            Picasso.with(context).load(R.drawable.default_placeholder).fit().centerCrop().into(showProdImage, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError() {
                    startPostponedEnterTransition()
                }
            })
        } else {
            Picasso.with(context).load(img).fit().centerCrop().into(showProdImage, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError() {
                    startPostponedEnterTransition()
                }
            })
        }

        ViewCompat.setTransitionName(showProdLayout, "itemProdContainer")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            setAllContainerColors(requireContext().getColor(R.color.white))
            scrimColor = Color.TRANSPARENT
            containerColor = requireContext().getColor(R.color.white)
        }
    }


    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): ShowItemFragment = ShowItemFragment()
    }
}