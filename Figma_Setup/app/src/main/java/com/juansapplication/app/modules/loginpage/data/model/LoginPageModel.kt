package com.juansapplication.app.modules.loginpage.`data`.model

import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class LoginPageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtNewUser: String? = MyApp.getInstance().resources.getString(R.string.lbl_new_user)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etUsernameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etPasswordValue: String? = null
)
