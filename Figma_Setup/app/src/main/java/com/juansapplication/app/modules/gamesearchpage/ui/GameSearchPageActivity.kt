package com.juansapplication.app.modules.gamesearchpage.ui

import android.view.View
import androidx.activity.viewModels
import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.base.BaseActivity
import com.juansapplication.app.databinding.ActivityGameSearchPageBinding
import com.juansapplication.app.modules.gamesearchpage.`data`.model.ListrectanglefifteenRowModel
import com.juansapplication.app.modules.gamesearchpage.`data`.viewmodel.GameSearchPageVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class GameSearchPageActivity :
    BaseActivity<ActivityGameSearchPageBinding>(R.layout.activity_game_search_page) {
  private val viewModel: GameSearchPageVM by viewModels<GameSearchPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listrectanglefifteenAdapter =
    ListrectanglefifteenAdapter(viewModel.listrectanglefifteenList.value?:mutableListOf())
    binding.recyclerListrectanglefifteen.adapter = listrectanglefifteenAdapter
    listrectanglefifteenAdapter.setOnItemClickListener(
    object : ListrectanglefifteenAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListrectanglefifteenRowModel) {
        onClickRecyclerListrectanglefifteen(view, position, item)
      }
    }
    )
    viewModel.listrectanglefifteenList.observe(this) {
      listrectanglefifteenAdapter.updateData(it)
    }
    binding.gameSearchPageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerListrectanglefifteen(
    view: View,
    position: Int,
    item: ListrectanglefifteenRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "GAME_SEARCH_PAGE_ACTIVITY"

  }
}
