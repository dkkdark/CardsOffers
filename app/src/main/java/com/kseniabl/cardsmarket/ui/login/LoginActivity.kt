package com.kseniabl.cardsmarket.ui.login

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.transition.*
import android.view.Gravity
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.BaseActivity
import com.kseniabl.cardsmarket.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.scene_login.*
import android.graphics.Shader.TileMode


class LoginActivity : BaseActivity(), LoginView {

    @Inject
    lateinit var auth: FirebaseAuth
    @Inject
    lateinit var presenter: LoginPresenterInterface<LoginView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.attachView(this)

        // для доступа при запуске
        val loginScene = Scene.getSceneForLayout(scene_root, R.layout.scene_login, this)
        loginScene.enter()

        val shader = getTextGradient()
        enterButton.paint.shader = shader
        registerButton.paint.shader = shader

        setupClickListeners()
    }

    private fun getTextGradient(): Shader {
        val textShader: Shader = LinearGradient(0F, 0F, 0f, 70F,
            intArrayOf(getColor(R.color.purple), getColor(R.color.blue)),
            floatArrayOf(0f, 1f),
            TileMode.CLAMP)
        return textShader
    }

    override fun onBackPressed() {
        // только выход
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setupClickListeners() {
        enterButton.setOnClickListener {
            presenter.singIn(loginText.text.toString(), passwordText.text.toString())
        }

        registerButton.setOnClickListener {
            changeScene()
        }

    }

    private fun changeScene() {
        val registrationScene = Scene.getSceneForLayout(scene_root, R.layout.scene_registration, this)

        val trSet = TransitionSet()
        trSet.addTransition(Slide(Gravity.TOP).addTarget(R.id.relLayLog))
        trSet.addTransition(Fade().addTarget(R.id.relLayLog))
        trSet.addTransition(Slide(Gravity.BOTTOM).addTarget(R.id.relLayReg))
        trSet.duration = 1000

        TransitionManager.go(registrationScene, trSet)

        registrationScene.sceneRoot.findViewById<TextView>(R.id.logButtonText).setOnClickListener {
            changeSceneToLogin()
        }

        val name = registrationScene.sceneRoot.findViewById<TextInputEditText>(R.id.nicknameText)
        val email = registrationScene.sceneRoot.findViewById<TextInputEditText>(R.id.emailRegisterText)
        val password = registrationScene.sceneRoot.findViewById<TextInputEditText>(R.id.passwordRegText)
        val passwordRep = registrationScene.sceneRoot.findViewById<TextInputEditText>(R.id.repeatPasswordText)
        registrationScene.sceneRoot.findViewById<Button>(R.id.regButton).setOnClickListener {
            presenter.createUser(name.text.toString(), email.text.toString(), password.text.toString(), passwordRep.text.toString())
        }

        val regButton = registrationScene.sceneRoot.findViewById<Button>(R.id.regButton)
        val logButtonText = registrationScene.sceneRoot.findViewById<TextView>(R.id.logButtonText)
        val shader = getTextGradient()
        regButton.paint.shader = shader
        logButtonText.paint.shader = shader
    }

    private fun changeSceneToLogin() {
        val loginScene = Scene.getSceneForLayout(scene_root, R.layout.scene_login, this)

        val trSet = TransitionSet()
        trSet.addTransition(Slide(Gravity.TOP).addTarget(R.id.relLayReg))
        trSet.addTransition(Fade().addTarget(R.id.relLayReg))
        trSet.addTransition(Slide(Gravity.BOTTOM).addTarget(R.id.relLayLog))
        trSet.duration = 1000

        TransitionManager.go(loginScene, trSet)


        loginScene.sceneRoot.findViewById<TextView>(R.id.registerButton).setOnClickListener {
            changeScene()
        }
        val login = loginScene.sceneRoot.findViewById<TextInputEditText>(R.id.loginText)
        val password = loginScene.sceneRoot.findViewById<TextInputEditText>(R.id.passwordText)
        loginScene.sceneRoot.findViewById<Button>(R.id.enterButton).setOnClickListener {
            presenter.singIn(login.text.toString(), password.text.toString())
        }

        val enterButton = loginScene.sceneRoot.findViewById<Button>(R.id.enterButton)
        val registerButton = loginScene.sceneRoot.findViewById<TextView>(R.id.registerButton)
        val shader = getTextGradient()
        enterButton.paint.shader = shader
        registerButton.paint.shader = shader
    }
}
