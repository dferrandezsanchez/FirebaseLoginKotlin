package danielferrandez.com.firebaseloginkotlin.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
        val userid: String? = null,
        val email: String? = null,
        val firstname: String? = null,
        val lastname: String? = null,
        val imageurl: String? = "https://homewoodfamilyaz.org/wp-content/uploads/2017/04/square_profile_pic_male.png")