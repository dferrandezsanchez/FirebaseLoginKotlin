package danielferrandez.com.firebaseloginkotlin

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*


/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    var fbAuth = FirebaseAuth.getInstance()
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //val email_sign_in_button = findViewById<Button>(R.id.email_sign_in_button)
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

    private fun signIn(view: View, email: String, pass: String) {
        showMessage(view, "Autenticando...")

        fbAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, OnCompleteListener<AuthResult>
        { task ->
            if (task.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("id", fbAuth.currentUser?.email)
                startActivity(intent)
            } else {
                showMessage(view, "Error: ${task.exception?.message}")
            }
        })
    }

    private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }
}
