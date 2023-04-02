package com.juansapplication.app.modules.profilepage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juansapplication.app.modules.profilepage.`data`.model.ProfilePageModel
import org.koin.core.KoinComponent

class ProfilePageVM : ViewModel(), KoinComponent {
  val profilePageModel: MutableLiveData<ProfilePageModel> = MutableLiveData(ProfilePageModel())

  var navArguments: Bundle? = null
}
