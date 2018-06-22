package danielferrandez.com.firebaseloginkotlin.api

import android.app.Activity
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import danielferrandez.com.firebaseloginkotlin.model.UserModel
import danielferrandez.com.firebaseloginkotlin.ui.login.LoginPresenter
import danielferrandez.com.firebaseloginkotlin.ui.main.MainPresenter
import danielferrandez.com.firebaseloginkotlin.ui.register.RegisterPresenter

class FireBaseApiServices(private val callingActivity: Activity) {
    private var fbAuth = FirebaseAuth.getInstance()
    private lateinit var mDatabase: DatabaseReference
    private var user: UserModel? = null


    fun signIn(view: View, email: String, password: String, presenter: LoginPresenter) {
        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(callingActivity,
                { task ->
                    if (task.isSuccessful) {
                        presenter.loginSuccess(fbAuth.currentUser!!)
                    } else {
                        presenter.loginError(view, task.exception?.message)
                    }
                })
    }

    fun signUp(view: View, email: String, password: String, firstname: String, lastname: String?, registerPresenter: RegisterPresenter) {
        fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(callingActivity,
                { task ->
                    if (task.isSuccessful) {
                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fbAuth.currentUser!!.uid)
                        setDataToProfile(email, firstname, lastname);
                        registerPresenter.registerSuccess(fbAuth.currentUser)
                    } else {
                        registerPresenter.registerError(view, task.exception?.message)
                    }
                })
    }

    private fun setDataToProfile(email: String, firstname: String, lastname: String?) {
        mDatabase.setValue(UserModel(fbAuth.currentUser!!.uid, email, firstname, lastname))
    }

    fun listenChanges(mainPresenter: MainPresenter) {
        fbAuth.addAuthStateListener {
            if (fbAuth.currentUser == null) {
                mainPresenter.finishActivity()
            } else {
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fbAuth.currentUser!!.uid)
                mDatabase.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        mainPresenter.finishActivity()
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            user = dataSnapshot.getValue(UserModel::class.java)
                            mainPresenter.populateChanges(user!!)
                        }
                    }
                })
            }
        }
    }

    fun logOut() {
        fbAuth.signOut()
    }
}