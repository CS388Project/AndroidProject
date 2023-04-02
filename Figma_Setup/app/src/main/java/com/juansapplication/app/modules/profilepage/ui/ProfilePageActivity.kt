package com.juansapplication.app.modules.profilepage.ui

import androidx.activity.viewModels
import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.base.BaseActivity
import com.juansapplication.app.databinding.ActivityProfilePageBinding
import com.juansapplication.app.modules.profilepage.`data`.viewmodel.ProfilePageVM
import kotlin.String
import kotlin.Unit

class ProfilePageActivity : BaseActivity<ActivityProfilePageBinding>(R.layout.activity_profile_page)
    {
  private val viewModel: ProfilePageVM by viewModels<ProfilePageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.profilePageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "PROFILE_PAGE_ACTIVITY"

  }
}
