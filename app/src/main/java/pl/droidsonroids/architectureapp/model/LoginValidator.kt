package pl.droidsonroids.architectureapp.model

import android.support.annotation.VisibleForTesting

interface Validator {
    fun areCredentialsValid(login: String, password: String): Boolean
}

class LoginValidator : Validator {

    private val MIN_LOGIN_LENGTH = 3
    private val MIN_PASSWORD_LENGTH = 6

    override fun areCredentialsValid(login: String, password: String): Boolean
            = isLoginValid(login) && isPasswordValid(password)

    @VisibleForTesting
    fun isLoginValid(login: String): Boolean {
        val trimmedLogin = login.trim()
        return trimmedLogin.length >= MIN_LOGIN_LENGTH &&
                trimmedLogin.none { char: Char -> char.isWhitespace() || char.isDigit() }
    }

    @VisibleForTesting
    fun isPasswordValid(password: String): Boolean {
        val trimmedPassword = password.trim()
        return trimmedPassword.length >= MIN_PASSWORD_LENGTH &&
                trimmedPassword.any { char: Char -> char.isDigit() } &&
                trimmedPassword.any { char: Char -> char.isUpperCase() }
    }
}