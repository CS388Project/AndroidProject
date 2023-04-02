package com.juansapplication.app.modules.friendsprofilepage.`data`.model

import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class FriendsProfilePageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtFriends: String? = MyApp.getInstance().resources.getString(R.string.lbl_friends)

)
