package com.juansapplication.app.modules.friendsprofilepage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juansapplication.app.modules.friendsprofilepage.`data`.model.FriendsProfilePageModel
import org.koin.core.KoinComponent

class FriendsProfilePageVM : ViewModel(), KoinComponent {
  val friendsProfilePageModel: MutableLiveData<FriendsProfilePageModel> =
      MutableLiveData(FriendsProfilePageModel())

  var navArguments: Bundle? = null
}
