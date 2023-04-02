package com.juansapplication.app.modules.homepage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juansapplication.app.modules.homepage.`data`.model.HomePageModel
import org.koin.core.KoinComponent

class HomePageVM : ViewModel(), KoinComponent {
  val homePageModel: MutableLiveData<HomePageModel> = MutableLiveData(HomePageModel())

  var navArguments: Bundle? = null
}
