package pl.droidsonroids.architectureapp.model

import java.util.*
import kotlin.concurrent.timerTask

interface AuthService {
    fun login(login: String, password: String, loginServiceCallback: LoginServiceCallback)
}

class LoginService : AuthService {
    private val CORRECT_LOGIN = "login"
    private val CORRECT_PASSWORD = "password"

    private val FAKE_REQUEST_DURATION_MILLIS: Long = 5000
    override fun login(login: String, password: String, loginServiceCallback: LoginServiceCallback) {
        val timer = Timer()
        timer.schedule(timerTask {
            if (login == CORRECT_LOGIN && password == CORRECT_PASSWORD) {
                loginServiceCallback.onLoginSuccess()
            } else {
                loginServiceCallback.onLoginFailure()
            }
        }, FAKE_REQUEST_DURATION_MILLIS)
    }
}
