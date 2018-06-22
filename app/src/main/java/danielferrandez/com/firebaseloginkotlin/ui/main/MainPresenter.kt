package danielferrandez.com.firebaseloginkotlin.ui.main

import danielferrandez.com.firebaseloginkotlin.api.FireBaseApiServices
import danielferrandez.com.firebaseloginkotlin.model.UserModel

class MainPresenter (private val mainActivity: MainActivity) : MainContract.Presenter {

    private var fireBaseApiServices = FireBaseApiServices(mainActivity)

    override fun listenChanges() {
        fireBaseApiServices.listenChanges(this)
    }

    override fun logOut(firstname: String?, email: String?, imageUrl: String) {
        fireBaseApiServices.logOut()
    }

    override fun finishActivity() {
        mainActivity.finishActivity()
    }

    override fun populateChanges(user: UserModel) {
        mainActivity.populateChanges(user)
    }

}