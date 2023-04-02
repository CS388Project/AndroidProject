package com.juansapplication.app.modules.gamesearchpage.`data`.model

import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ListrectanglefifteenRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtGameTitle: String? = MyApp.getInstance().resources.getString(R.string.lbl_game_title)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_lorem_ipsum_dol)

)
