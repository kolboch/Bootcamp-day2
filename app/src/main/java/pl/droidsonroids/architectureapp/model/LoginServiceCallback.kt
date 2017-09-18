package pl.droidsonroids.architectureapp.model

interface LoginServiceCallback {
    fun onLoginSuccess()
    fun onLoginFailure()
}