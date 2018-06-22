package danielferrandez.com.firebaseloginkotlin.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.transition.Visibility
import android.view.View
import com.google.firebase.auth.FirebaseUser
import danielferrandez.com.firebaseloginkotlin.R
import danielferrandez.com.firebaseloginkotlin.ui.main.MainActivity
import danielferrandez.com.firebaseloginkotlin.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val loginPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        email_sign_in_button.setOnClickListener { view ->
            if (!email.text.isEmpty() && !password.text.isEmpty()) {
                signIn(view, email.text.toString(), password.text.toString())
            }
        }

        tv_create_account.setOnClickListener { view ->
            val intent = Intent(this, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun signIn(view: View, email: String, password: String) {
        loginPresenter.signIn(view, email.trim(), password.trim())
    }

    override fun loginSuccess(currentUser: FirebaseUser?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id", currentUser?.email)
        startActivity(intent)
    }

    override fun loginError(view: View, message: String?) {
        showMessage(view, "Error: ${message}")
    }

    override fun showProgress() {
        login_progress.visibility = View.VISIBLE
        login_form.visibility = View.GONE
    }

    override fun hideProgress() {
        login_progress.visibility = View.GONE
        login_form.visibility = View.VISIBLE

    }

    private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }
}
