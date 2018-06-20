package danielferrandez.com.firebaseloginkotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import danielferrandez.com.firebaseloginkotlin.model.User
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*


class RegisterActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    private lateinit var mDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar)

        tv_have_account.setOnClickListener { view ->
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        btn_signup.setOnClickListener { view ->
            if (!txt_email.text.isEmpty() && !txt_password.text.isEmpty() && !txt_firstname.text.isEmpty()) {
                signup(txt_email.text.toString(), txt_password.text.toString(), txt_firstname.text.toString(), txt_lastname.text.toString())
            }
        }
    }

    private fun signup(email: String, pass: String, firstname: String, lastname: String?) {
        fbAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, OnCompleteListener<AuthResult>
        { task ->
            if (task.isSuccessful) {
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fbAuth.currentUser!!.uid)
                //setDataToProfile(email, firstname, lastname);
                setDataToProfileWithPOJO(email, firstname, lastname);
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("id", fbAuth.currentUser!!.email)
                startActivity(intent)
            }
        })
    }

//    private fun setDataToProfile(email: String, firstname: String, lastname: String?) {
//        mDatabase.child("email").setValue(email)
//        mDatabase.child("firstname").setValue(firstname)
//        mDatabase.child("lastname").setValue(lastname)
//        mDatabase.child("imageurl").setValue("https://homewoodfamilyaz.org/wp-content/uploads/2017/04/square_profile_pic_male.png")
//        mDatabase.child("userid").setValue(fbAuth.currentUser!!.uid)
//    }

    private fun setDataToProfileWithPOJO(email: String, firstname: String, lastname: String?) {
        mDatabase.setValue(User(fbAuth.currentUser!!.uid, email, firstname, lastname))
    }

}
