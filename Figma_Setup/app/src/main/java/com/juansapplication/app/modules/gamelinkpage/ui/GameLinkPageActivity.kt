package com.juansapplication.app.modules.gamelinkpage.ui

import androidx.activity.viewModels
import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.base.BaseActivity
import com.juansapplication.app.databinding.ActivityGameLinkPageBinding
import com.juansapplication.app.modules.gamelinkpage.`data`.viewmodel.GameLinkPageVM
import kotlin.String
import kotlin.Unit

class GameLinkPageActivity :
    BaseActivity<ActivityGameLinkPageBinding>(R.layout.activity_game_link_page) {
  private val viewModel: GameLinkPageVM by viewModels<GameLinkPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.gameLinkPageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "GAME_LINK_PAGE_ACTIVITY"

  }
}
