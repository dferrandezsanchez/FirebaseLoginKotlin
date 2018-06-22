package danielferrandez.com.firebaseloginkotlin.ui.login

import com.google.firebase.auth.FirebaseUser

class LoginContract {

    interface View {
        fun signIn(view: android.view.View, email: String, password: String)
        fun loginSuccess(currentUser: FirebaseUser?)
        fun loginError(view: android.view.View, message: String?)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun signIn(view: android.view.View, email: String, password: String)
        fun loginSuccess(currentUser: FirebaseUser)
        fun loginError(view: android.view.View, message: String?)
    }
}