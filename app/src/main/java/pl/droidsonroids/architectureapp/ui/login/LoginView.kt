package pl.droidsonroids.architectureapp.ui.login

interface LoginView {
    fun enableLoginButton()
    fun disableLoginButton()
    fun goToMainScreen()
    fun showLoginError()
    fun showProgressBar()
    fun hideProgressBar()
    fun hideKeyboard()
}