package pl.droidsonroids.architectureapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_login.*
import pl.droidsonroids.architectureapp.R
import pl.droidsonroids.architectureapp.model.LoginService
import pl.droidsonroids.architectureapp.model.LoginValidator
import pl.droidsonroids.architectureapp.ui.main.MainActivity


class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this, LoginValidator(), LoginService())

        setTextChangedListeners()
        setLoginButtonClickListener()
    }

    private fun setTextChangedListeners() {
        editLogin.setTextChangedListener { onTextChanged() }
        editPassword.setTextChangedListener { onTextChanged() }
    }

    private fun onTextChanged() = presenter.onCredentialsChanged(editLogin.text.toString(), editPassword.text.toString())

    private fun setLoginButtonClickListener() {
        buttonLogin.setOnClickListener {
            presenter.onLoginClick(editLogin.text.toString(), editPassword.text.toString())
        }
    }

    override fun enableLoginButton() {
        buttonLogin.post { buttonLogin.isEnabled = true }
    }

    override fun disableLoginButton() {
        buttonLogin.post { buttonLogin.isEnabled = false }
    }

    override fun goToMainScreen() = startActivity(Intent(this, MainActivity::class.java))

    override fun showLoginError() {
        Snackbar.make(layoutContainer, getString(R.string.login_error), Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        layoutProgressBar.post { layoutProgressBar.visibility = VISIBLE }
    }

    override fun hideProgressBar() {
        layoutProgressBar.post { layoutProgressBar.visibility = GONE }
    }

    override fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun EditText.setTextChangedListener(onTextChanged: () -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) = Unit

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = onTextChanged()

        })
    }
}
