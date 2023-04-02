package com.juansapplication.app.modules.registrationpage.ui

import androidx.activity.viewModels
import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.base.BaseActivity
import com.juansapplication.app.databinding.ActivityRegistrationPageBinding
import com.juansapplication.app.modules.registrationpage.`data`.viewmodel.RegistrationPageVM
import kotlin.String
import kotlin.Unit

class RegistrationPageActivity :
    BaseActivity<ActivityRegistrationPageBinding>(R.layout.activity_registration_page) {
  private val viewModel: RegistrationPageVM by viewModels<RegistrationPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.registrationPageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "REGISTRATION_PAGE_ACTIVITY"

  }
}
