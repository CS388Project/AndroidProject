package com.juansapplication.app.modules.loginpage.ui

import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.juansapplication.app.R
import com.juansapplication.app.appcomponents.base.BaseActivity
import com.juansapplication.app.databinding.ActivityLoginPageBinding
import com.juansapplication.app.modules.homepage.ui.HomePageActivity
import com.juansapplication.app.modules.loginpage.`data`.viewmodel.LoginPageVM
import kotlin.String
import kotlin.Unit

class LoginPageActivity : BaseActivity<ActivityLoginPageBinding>(R.layout.activity_login_page) {
  private val viewModel: LoginPageVM by viewModels<LoginPageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.loginPageVM = viewModel
    Handler(Looper.getMainLooper()).postDelayed( {
      val destIntent = HomePageActivity.getIntent(this, null)
      startActivity(destIntent)
      finish()
      }, 3000)
    }

    override fun setUpClicks(): Unit {
    }

    companion object {
      const val TAG: String = "LOGIN_PAGE_ACTIVITY"

    }
  }
