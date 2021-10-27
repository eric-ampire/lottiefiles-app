package com.ericampire.android.androidstudycase.presentation.screen.home.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.app.App
import com.ericampire.android.androidstudycase.domain.entity.User
import com.ericampire.android.androidstudycase.presentation.custom.CustomImageView
import com.ericampire.android.androidstudycase.presentation.custom.LoadingAnimation
import com.ericampire.android.androidstudycase.presentation.custom.TopActionBar
import com.ericampire.android.androidstudycase.presentation.screen.home.business.HomeAction
import com.ericampire.android.androidstudycase.presentation.screen.home.business.HomeContentData
import com.ericampire.android.androidstudycase.presentation.screen.home.business.HomeViewModel
import com.ericampire.android.androidstudycase.presentation.screen.home.business.HomeViewState
import com.ericampire.android.androidstudycase.presentation.theme.AppColor
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
  navController: NavController,
  viewModel: HomeViewModel = mavericksViewModel()
) {

  val state by viewModel.collectAsState(HomeViewState::contentData)
  val loginState by viewModel.collectAsState(HomeViewState::login)
  val userState by viewModel.collectAsState(HomeViewState::currentUser)

  val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
  val coroutineScope = rememberCoroutineScope()

  val context = LocalContext.current

  LaunchedEffect(viewModel) {
    viewModel.submitAction(HomeAction.FetchData)
    viewModel.submitAction(HomeAction.FetchCurrentUser)
  }

  LaunchedEffect(loginState) {
    if (loginState is Success) {
      App.restart(context)
    }
  }

  ModalBottomSheetLayout(
    sheetShape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
    sheetState = bottomSheetState,
    sheetContent = {
      LoginBottomSheet(
        onLoginClick = {
          viewModel.submitAction(HomeAction.Login)
        },
        isLoading = loginState is Loading
      )
    },
    content = {
      Scaffold(
        topBar = {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .background(color = AppColor.Black001),
            content = {
              TopActionBar()
            }
          )
        },
        content = { contentPadding ->
          Crossfade(modifier = Modifier.padding(contentPadding), targetState = state) {
            Box(
              modifier = Modifier.fillMaxSize(),
              contentAlignment = Alignment.Center,
              content = {
                when (it) {
                  Uninitialized -> {
                    LoadingAnimation(
                      waveColor = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                      arcColor = MaterialTheme.colors.primaryVariant
                    )
                  }
                  is Loading -> {
                    LoadingAnimation(
                      waveColor = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                      arcColor = MaterialTheme.colors.primaryVariant
                    )
                  }
                  is Success -> {
                    HomeContent(
                      state = it.invoke(),
                      currentUser = userState.invoke(),
                      onLoginClick = {
                        coroutineScope.launch {
                          bottomSheetState.animateTo(targetValue = ModalBottomSheetValue.Expanded)
                        }
                      }
                    )
                  }
                  is Fail -> {
                    Timber.e(it.error.localizedMessage)
                  }
                }
              }
            )
          }
        }
      )
    }
  )
}

@ExperimentalMaterialApi
@Composable
fun HomeContent(
  modifier: Modifier = Modifier,
  state: HomeContentData,
  currentUser: User? = null,
  onLoginClick: () -> Unit
) {
  LazyColumn(
    state = rememberLazyListState(),
    modifier = modifier
      .fillMaxSize()
      .animateContentSize(),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    content = {
      item {
        if (currentUser == null) {
          UnLoggedUserHeaderView(onLoginClick = onLoginClick)
        } else {
          LoggedUserHeaderView(user = currentUser)
        }
      }

      item {
        Text(
          modifier = Modifier.padding(horizontal = 16.dp),
          maxLines = 1,
          text = stringResource(id = R.string.txt_featured_animation),
          style = MaterialTheme.typography.h4.copy(
            color = MaterialTheme.colors.onSurface
          ),
        )
      }

      item {
        LazyRow(
          contentPadding = PaddingValues(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(16.dp),
          content = {
            items(items = state.featuredLottieFile, key = { it.toString() }) { animation ->
              FeaturedLottieFileView(
                lottiefile = animation,
                onClick = {
                  // Todo:
                }
              )
            }
          }
        )
      }
      item {
        Text(
          modifier = Modifier.padding(horizontal = 16.dp),
          maxLines = 1,
          text = stringResource(id = org.zxconnect.android.beserve.i18n.R.string.txt_featured_animator),
          style = MaterialTheme.typography.h4.copy(
            color = MaterialTheme.colors.onSurface
          ),
        )
      }

      item {
        LazyRow(
          contentPadding = PaddingValues(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(16.dp),
          content = {
            items(items = state.featuredAnimators) { animator ->
              CustomImageView(
                modifier = Modifier
                  .size(70.dp)
                  .clip(CircleShape),
                data = animator.avatarUrl,
              )
            }
          }
        )
      }

      item {
        Text(
          modifier = Modifier.padding(horizontal = 16.dp),
          maxLines = 1,
          text = stringResource(id = org.zxconnect.android.beserve.i18n.R.string.txt_latest_story),
          style = MaterialTheme.typography.h4.copy(
            color = MaterialTheme.colors.onSurface
          ),
        )
      }

      items(items = state.blog) { blog ->
        BlogItemView(
          modifier = Modifier.padding(horizontal = 16.dp),
          blog = blog,
          onClick = { }
        )
      }

      item {
        BrowseAllItemView()
      }
    }
  )
}
