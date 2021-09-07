package com.ericampire.android.androidstudycase.presentation.screen.login.business

import androidx.lifecycle.ViewModel
import com.ericampire.android.androidstudycase.domain.usecase.SaveUserUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(
  private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
}