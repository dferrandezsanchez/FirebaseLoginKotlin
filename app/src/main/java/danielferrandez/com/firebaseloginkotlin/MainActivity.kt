package danielferrandez.com.firebaseloginkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import danielferrandez.com.firebaseloginkotlin.model.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    private lateinit var mDatabase: DatabaseReference

    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fbAuth.addAuthStateListener {
            if(fbAuth.currentUser == null){
                this.finish()
            } else{
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fbAuth.currentUser!!.uid)
                mDatabase.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            user = dataSnapshot.getValue(User::class.java)
                            tv_userename.text = user?.firstname ?: ""
                            tv_useremail.text = user?.email ?: ""
                            Glide.with(applicationContext)
                                    .asBitmap()
                                    .load(user!!.imageurl)
                                    .into(iv_user)
                        }
                    }
                })
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.logout->{
                logOut();
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun logOut() {
        fbAuth.signOut()
    }
}
