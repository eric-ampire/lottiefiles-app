package com.ericampire.android.androidstudycase.presentation.screen.explore.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.presentation.custom.TopActionBar
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreAction
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreEffect
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreViewModel
import com.ericampire.android.androidstudycase.presentation.theme.AppColor
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalPagerApi
@Composable
fun ExploreScreen(
  navController: NavController,
  viewModel: ExploreViewModel
) {

  val effects by viewModel.container.sideEffectFlow.collectAsState(ExploreEffect.Idle)
  val state by viewModel.container.stateFlow.collectAsState()

  val tabItems = stringArrayResource(id = R.array.explore_item)
  val pagerState = rememberPagerState(pageCount = tabItems.size)
  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(viewModel) {
    viewModel.submitAction(ExploreAction.FindRecentFile)
  }

  LaunchedEffect(viewModel) {
    snapshotFlow { pagerState.currentPage }.collect { page ->
      when (page) {
        0 -> viewModel.submitAction(ExploreAction.FindRecentFile)
        1 -> viewModel.submitAction(ExploreAction.FindFeaturedFile)
        else -> viewModel.submitAction(ExploreAction.FindPopularFile)
      }
    }
  }

  Scaffold(
    topBar = {
      Column(
        modifier = Modifier.fillMaxWidth().background(color = AppColor.Black001),
        content = {
          TopActionBar()
          TabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Transparent,
            indicator = { tabPositions ->
              TabRowDefaults.Indicator(
                modifier = Modifier
                  .pagerTabIndicatorOffset(pagerState, tabPositions)
                  .padding(horizontal = 32.dp)
                  .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                height = 3.dp,
                color = MaterialTheme.colors.primary
              )
            },
            tabs = {
              tabItems.forEachIndexed { index, title ->
                Tab(
                  selected = index == pagerState.currentPage,
                  selectedContentColor = MaterialTheme.colors.primary,
                  unselectedContentColor = Color.White,
                  onClick = {
                    coroutineScope.launch {
                      pagerState.animateScrollToPage(index)
                    }
                  },
                  content = {
                    Text(
                      modifier = Modifier.padding(vertical = 12.dp),
                      text = title,
                      style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Normal
                      ),
                      textAlign = TextAlign.Center,
                    )
                  }
                )
              }
            }
          )
        }
      )
    },
    content = { contentPadding ->
      Crossfade(modifier = Modifier.padding(contentPadding), targetState = effects) {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center,
          content = {
            when(it) {
              ExploreEffect.Idle -> {
                Timber.e("Idel")
              }
              ExploreEffect.Loading -> {

              }
              is ExploreEffect.ShowErrorMessage -> {

              }
              ExploreEffect.Success -> {
                val data = state.files
                Timber.e(data.toString())
              }
            }
          }
        )
      }
    }
  )
}