package com.juansapplication.app.modules.profilepage.`data`.model

import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ProfilePageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtFriends: String? = MyApp.getInstance().resources.getString(R.string.lbl_friends)

)
