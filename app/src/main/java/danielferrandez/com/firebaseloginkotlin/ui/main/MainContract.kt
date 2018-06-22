package danielferrandez.com.firebaseloginkotlin.ui.main

import danielferrandez.com.firebaseloginkotlin.model.UserModel

class MainContract{
    interface View{
        fun listenChanges()
        fun populateChanges(user: UserModel)
        fun logOut()
        fun finishActivity()
    }

    interface Presenter{
        fun listenChanges()
        fun logOut(firstname: String?, email: String?, imageUrl: String)
        fun finishActivity()
        fun populateChanges(user: UserModel)
    }
}