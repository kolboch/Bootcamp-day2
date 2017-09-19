package pl.droidsonroids.architectureapp.model

import org.junit.Assert.assertFalse
import org.junit.Test

/**
 * Created by Karol on 2017-09-19.
 */
class LoginValidatorTest {
    /*
    GIVEN, WHEN, THEN
     */
    private val loginValidator = LoginValidator()

    @Test
    fun isLoginAndPasswordValid() {
        assert(areCredentialsValid())
    }

    @Test
    fun isPasswordTooShortInvalid() {
        assertFalse(areCredentialsValid(password = "Pass"))
    }

    @Test
    fun isPasswordInvalidWhenNoNumber() {
        assertFalse(areCredentialsValid(password = "PasswordNoNumber"))
    }

    @Test
    fun isPasswordInvalidWhenNoCapital() {
        assertFalse(areCredentialsValid(password = "88password1no1capital"))
    }

    @Test
    fun isLoginWithSpaceInvalid() {
        assertFalse(areCredentialsValid(login = "log with spaces"))
    }

    @Test
    fun isLoginTooShort() {
        assertFalse(areCredentialsValid(login = "a"))
    }

    @Test
    fun isLoginInvalidWithDigit() {
        assertFalse(areCredentialsValid(login = "login891"))
    }

    @Test
    fun isPasswordTooShortWithSpaces() {
        assertFalse(areCredentialsValid(password = " 89aS   "))
    }

    private fun areCredentialsValid(login: String = "Login", password: String = "Password7") =
            loginValidator.areCredentialsValid(login, password)
}