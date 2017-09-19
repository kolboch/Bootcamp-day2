package pl.droidsonroids.architectureapp.ui.login

import pl.droidsonroids.architectureapp.model.AuthService
import pl.droidsonroids.architectureapp.model.LoginServiceCallback
import pl.droidsonroids.architectureapp.model.Validator

class LoginPresenter(
        private val view: LoginView,
        private val validator: Validator,
        private val loginService: AuthService)
{


    fun onCredentialsChanged(login: String, password: String) {
        validateCredentials(login, password)
    }

    private fun validateCredentials(login: String, password: String) {
        if (validator.areCredentialsValid(login, password)) {
            view.enableLoginButton()
        } else {
            view.disableLoginButton()
        }
    }

    fun onLoginClick(login: String, password: String) {
        view.disableLoginButton()
        view.hideKeyboard()
        view.showProgressBar()

        loginService.login(login, password, object : LoginServiceCallback {
            override fun onLoginSuccess() {
                view.goToMainScreen()
            }

            override fun onLoginFailure() {
                view.enableLoginButton()
                view.hideProgressBar()
                view.showLoginError()
            }
        })
    }
}