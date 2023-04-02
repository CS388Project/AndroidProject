package com.juansapplication.app.modules.registrationpage.`data`.model

import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class RegistrationPageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtUsername: String? = MyApp.getInstance().resources.getString(R.string.lbl_username)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etPasswordValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etPasswordOneValue: String? = null
)
