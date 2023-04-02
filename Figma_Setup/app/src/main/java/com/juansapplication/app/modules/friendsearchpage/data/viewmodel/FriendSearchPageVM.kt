package com.juansapplication.app.modules.friendsearchpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juansapplication.app.modules.friendsearchpage.`data`.model.FriendSearchPageModel
import org.koin.core.KoinComponent

class FriendSearchPageVM : ViewModel(), KoinComponent {
  val friendSearchPageModel: MutableLiveData<FriendSearchPageModel> =
      MutableLiveData(FriendSearchPageModel())

  var navArguments: Bundle? = null
}
