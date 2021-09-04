package com.ericampire.android.androidstudycase.presentation.screen.explore.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.presentation.custom.LoadingView
import com.ericampire.android.androidstudycase.presentation.custom.TopActionBar
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreAction
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreEffect
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreViewModel
import com.ericampire.android.androidstudycase.presentation.theme.AppColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun ExploreScreen(
  navController: NavController,
  viewModel: ExploreViewModel
) {

  val coroutineScope = rememberCoroutineScope()
  val state by viewModel.container.stateFlow.collectAsState()
  val context = LocalContext.current

  val tabItems = stringArrayResource(id = R.array.explore_item)
  val pagerState = rememberPagerState(pageCount = tabItems.size)

  LaunchedEffect(viewModel) {
    viewModel.container.sideEffectFlow.collect {
      when (it) {
        is ExploreEffect.ShowErrorMessage -> {
          Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
      }
    }
  }

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
        modifier = Modifier
          .fillMaxWidth()
          .background(color = AppColor.Black001),
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
      Box(
        modifier = Modifier
          .padding(contentPadding)
          .fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
          if (state.isLoading) {
            LoadingView()
          }
          if (state.files.isNotEmpty()) {
            ExploreContent(files = state.files)
          }
        }
      )
    }
  )
}

@ExperimentalMaterialApi
@Composable
fun ExploreContent(
  modifier: Modifier = Modifier,
  files: List<Lottiefile>
) {
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    content = {
      items(items = files, key = { it.toString() }) { lottieFile ->
        LottieFileItemView(
          lottiefile = lottieFile,
          onClick = {}
        )
      }
    }
  )
}