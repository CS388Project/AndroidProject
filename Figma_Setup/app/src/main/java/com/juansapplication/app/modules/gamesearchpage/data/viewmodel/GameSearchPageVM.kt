package com.juansapplication.app.modules.gamesearchpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juansapplication.app.modules.gamesearchpage.`data`.model.GameSearchPageModel
import com.juansapplication.app.modules.gamesearchpage.`data`.model.ListrectanglefifteenRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class GameSearchPageVM : ViewModel(), KoinComponent {
  val gameSearchPageModel: MutableLiveData<GameSearchPageModel> =
      MutableLiveData(GameSearchPageModel())

  var navArguments: Bundle? = null

  val listrectanglefifteenList: MutableLiveData<MutableList<ListrectanglefifteenRowModel>> =
      MutableLiveData(mutableListOf())
}
