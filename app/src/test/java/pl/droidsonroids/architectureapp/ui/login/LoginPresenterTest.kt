package pl.droidsonroids.architectureapp.ui.login

import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import pl.droidsonroids.architectureapp.model.AuthService
import pl.droidsonroids.architectureapp.model.LoginServiceCallback
import pl.droidsonroids.architectureapp.model.Validator

/**
 * Created by Karol on 2017-09-19.
 */
class LoginPresenterTest {

    lateinit var loginPresenter: LoginPresenter
    @Mock lateinit var view: LoginView
    @Mock lateinit var validator: Validator
    lateinit var service: AuthService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        service = mock()

        loginPresenter = LoginPresenter(view, validator, service)
    }

    @Test
    fun enableLoginWhenCredentialsValid() {
        //given
        whenever(validator.areCredentialsValid(any(), any())).thenReturn(true)
        //when
        loginPresenter.onCredentialsChanged("", "")
        //then
        verify(view).enableLoginButton()
        verify(view, never()).disableLoginButton()
    }

    @Test
    fun disableLoginButtonWhenCredentialsInvalid() {
        whenever(validator.areCredentialsValid(any(), any())).thenReturn(false)
        loginPresenter.onCredentialsChanged("", "")
        verify(view).disableLoginButton()
        verify(view, never()).enableLoginButton()
    }

    @Test
    fun disableLoginButtonWhenLoginClicked() {
        loginPresenter.onLoginClick("", "")

        verify(view).disableLoginButton()
    }

    @Test
    fun goToMainScreenWhenLoginIsSuccess() {
        whenever(service.login(any(), any(), any())).doAnswer {
            (it.arguments[2] as LoginServiceCallback).onLoginSuccess()
        }
        loginPresenter.onLoginClick("", "")

        verify(view).goToMainScreen()
    }

    @Test
    fun launchViewEventsWhenLoginFailure() {
        whenever(service.login(any(), any(), any())).doAnswer {
            (it.arguments[2] as LoginServiceCallback).onLoginFailure()
        }
        loginPresenter.onLoginClick("", "")
        inOrder(view) {
            verify(view).enableLoginButton()
            verify(view).hideProgressBar()
            verify(view).showLoginError()
        }
    }

}