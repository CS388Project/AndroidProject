package com.juansapplication.app.modules.gamelinkpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juansapplication.app.modules.gamelinkpage.`data`.model.GameLinkPageModel
import org.koin.core.KoinComponent

class GameLinkPageVM : ViewModel(), KoinComponent {
  val gameLinkPageModel: MutableLiveData<GameLinkPageModel> = MutableLiveData(GameLinkPageModel())

  var navArguments: Bundle? = null
}
