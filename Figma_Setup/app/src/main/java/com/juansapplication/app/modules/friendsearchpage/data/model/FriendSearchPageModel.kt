package com.juansapplication.app.modules.friendsearchpage.`data`.model

import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class FriendSearchPageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtUsername: String? = MyApp.getInstance().resources.getString(R.string.lbl_username2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_lorem_ipsum_dol)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUsernameOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_username2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescriptionOne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_lorem_ipsum_dol)

)
