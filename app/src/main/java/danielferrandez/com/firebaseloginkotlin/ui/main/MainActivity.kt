package danielferrandez.com.firebaseloginkotlin.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import danielferrandez.com.firebaseloginkotlin.R
import danielferrandez.com.firebaseloginkotlin.model.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), MainContract.View {

    private val mainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        listenChanges()
    }

    override fun listenChanges() {
        mainPresenter.listenChanges()
    }

    override fun populateChanges(user: UserModel) {
        tv_userename.text = user?.firstname ?: ""
        tv_useremail.text = user?.email ?: ""
        Glide.with(applicationContext)
                .asBitmap()
                .load(user!!.imageurl)
                .into(iv_user)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.logout -> {
                logOut();
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun logOut() {
        this.finish()
    }

    override fun finishActivity() {
        this.finish()
    }
}
