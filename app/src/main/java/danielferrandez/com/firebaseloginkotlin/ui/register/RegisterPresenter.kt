package danielferrandez.com.firebaseloginkotlin.ui.register

import android.view.View
import com.google.firebase.auth.FirebaseUser
import danielferrandez.com.firebaseloginkotlin.api.FireBaseApiServices

class RegisterPresenter (private val registerActivity: RegisterActivity) : RegisterContract.Presenter {

    private var fireBaseApiServices = FireBaseApiServices(registerActivity)

    override fun signUp(view: View, email: String, password: String, firstname: String, lastname: String?) {
        fireBaseApiServices.signUp(view, email, password, firstname, lastname, this)
    }

    override fun registerSuccess(currentUser: FirebaseUser?) {
        registerActivity.registerSuccess(currentUser)
    }

    override fun registerError(view: View, message: String?) {
        registerActivity.registerError(view, message)
    }

}