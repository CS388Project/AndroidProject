package com.juansapplication.app.modules.friendsprofilepage.ui

import androidx.activity.viewModels
import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.base.BaseActivity
import com.juansapplication.app.databinding.ActivityFriendsProfilePageBinding
import com.juansapplication.app.modules.friendsprofilepage.`data`.viewmodel.FriendsProfilePageVM
import kotlin.String
import kotlin.Unit

class FriendsProfilePageActivity :
    BaseActivity<ActivityFriendsProfilePageBinding>(R.layout.activity_friends_profile_page) {
  private val viewModel: FriendsProfilePageVM by viewModels<FriendsProfilePageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.friendsProfilePageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "FRIENDS_PROFILE_PAGE_ACTIVITY"

  }
}
