package danielferrandez.com.firebaseloginkotlin.ui.register

import com.google.firebase.auth.FirebaseUser

class RegisterContract {

    interface View {
        fun signUp(view: android.view.View, email: String, password: String, firstname: String, lastname: String?)
        fun registerSuccess(currentUser: FirebaseUser?)
        fun registerError(view: android.view.View, message: String?)
        fun showProgress(visible: Boolean)
    }

    interface Presenter {
        fun signUp(view: android.view.View, email: String, password: String, firstname: String, lastname: String?)
        fun registerSuccess(currentUser: FirebaseUser?)
        fun registerError(view: android.view.View, message: String?)
    }
}