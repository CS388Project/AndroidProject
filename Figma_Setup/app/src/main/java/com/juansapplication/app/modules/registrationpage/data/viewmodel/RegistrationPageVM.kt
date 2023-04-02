package com.juansapplication.app.modules.registrationpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juansapplication.app.modules.registrationpage.`data`.model.RegistrationPageModel
import org.koin.core.KoinComponent

class RegistrationPageVM : ViewModel(), KoinComponent {
  val registrationPageModel: MutableLiveData<RegistrationPageModel> =
      MutableLiveData(RegistrationPageModel())

  var navArguments: Bundle? = null
}
