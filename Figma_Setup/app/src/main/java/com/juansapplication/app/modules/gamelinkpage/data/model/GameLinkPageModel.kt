package com.juansapplication.app.modules.gamelinkpage.`data`.model

import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class GameLinkPageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtRemoveGame: String? = MyApp.getInstance().resources.getString(R.string.lbl_remove_game)
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
