package danielferrandez.com.firebaseloginkotlin.ui.login

import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import danielferrandez.com.firebaseloginkotlin.api.FireBaseApiServices

class LoginPresenter (private val loginActivity: LoginActivity) : LoginContract.Presenter {
    private var fireBaseApiServices = FireBaseApiServices(loginActivity)


    override fun signIn(view: View, email: String, password: String) {
        loginActivity.showProgress()
        fireBaseApiServices.signIn(view, email, password, this)
    }

    override fun loginSuccess(currentUser: FirebaseUser) {
        loginActivity.hideProgress()
        loginActivity.loginSuccess(currentUser)
    }

    override fun loginError(view: View, message: String?) {
        loginActivity.hideProgress()
        loginActivity.loginError(view, message)
    }
}