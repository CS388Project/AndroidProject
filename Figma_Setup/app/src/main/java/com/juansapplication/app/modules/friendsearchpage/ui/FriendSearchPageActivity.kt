package com.juansapplication.app.modules.friendsearchpage.ui

import androidx.activity.viewModels
import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.base.BaseActivity
import com.juansapplication.app.databinding.ActivityFriendSearchPageBinding
import com.juansapplication.app.modules.friendsearchpage.`data`.viewmodel.FriendSearchPageVM
import kotlin.String
import kotlin.Unit

class FriendSearchPageActivity :
    BaseActivity<ActivityFriendSearchPageBinding>(R.layout.activity_friend_search_page) {
  private val viewModel: FriendSearchPageVM by viewModels<FriendSearchPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.friendSearchPageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "FRIEND_SEARCH_PAGE_ACTIVITY"

  }
}
