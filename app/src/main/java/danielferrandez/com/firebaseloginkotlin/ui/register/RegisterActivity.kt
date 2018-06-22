package danielferrandez.com.firebaseloginkotlin.ui.register

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.auth.FirebaseUser
import danielferrandez.com.firebaseloginkotlin.R
import danielferrandez.com.firebaseloginkotlin.ui.login.LoginActivity
import danielferrandez.com.firebaseloginkotlin.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*


class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private val registerPresenter = RegisterPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar)

        tv_have_account.setOnClickListener {
            goLogin()
        }

        btn_signup.setOnClickListener { view ->
            if (!txt_email.text.isEmpty() && !txt_password.text.isEmpty() && !txt_firstname.text.isEmpty()) {
                signUp(view, txt_email.text.toString(), txt_password.text.toString(), txt_firstname.text.toString(), txt_lastname.text.toString())
            }
        }
    }

    override fun signUp(view: View, email: String, password: String, firstname: String, lastname: String?) {
        registerPresenter.signUp(view, email, password, firstname, lastname)
    }

    override fun registerSuccess(currentUser: FirebaseUser?) {
        goHome(currentUser)
    }

    override fun registerError(view: View, message: String?) {
        showMessage(view, message!!)
    }

    private fun goHome(currentUser: FirebaseUser?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id", currentUser!!.email)
        startActivity(intent)
    }

    private fun goLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun showProgress(visible: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

}
