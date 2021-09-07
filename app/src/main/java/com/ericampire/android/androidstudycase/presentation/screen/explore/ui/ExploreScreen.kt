package com.ericampire.android.androidstudycase.presentation.screen.explore.ui

import androidx.compose.animation.Crossfade
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
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.ericampire.android.androidstudycase.R
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.presentation.custom.LoadingAnimation
import com.ericampire.android.androidstudycase.presentation.custom.TopActionBar
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreAction
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreViewModel
import com.ericampire.android.androidstudycase.presentation.screen.explore.business.ExploreViewState
import com.ericampire.android.androidstudycase.presentation.theme.AppColor
import com.ericampire.android.androidstudycase.util.extension.copyTextToClipboard
import com.ericampire.android.androidstudycase.util.extension.downloadAndShare
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun ExploreScreen(
  navController: NavController,
  viewModel: ExploreViewModel = mavericksViewModel()
) {

  val coroutineScope = rememberCoroutineScope()
  val state by viewModel.collectAsState(ExploreViewState::files)
  val context = LocalContext.current

  val tabItems = stringArrayResource(id = R.array.explore_item)
  val pagerState = rememberPagerState(pageCount = tabItems.size)


  val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
  var selectedAnimation by remember { mutableStateOf<Lottiefile?>(null) }
  var fileUrl by remember { mutableStateOf<String?>(null) }

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

  LaunchedEffect(fileUrl) {
    if (fileUrl != null) {
      context.downloadAndShare(
        url = fileUrl!!,
        onError = {
          Timber.e(it)
        },
        onSuccess = {
          selectedAnimation = null
          fileUrl = null
          coroutineScope.launch {
            bottomSheetState.hide()
          }
        }
      )
    }
  }


  ModalBottomSheetLayout(
    sheetShape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
    sheetState = bottomSheetState,
    sheetContent = {
      ShareBottomSheet(
        lottiefile = selectedAnimation,
        isLoading = fileUrl != null,
        onCopyLink = {
          context.copyTextToClipboard(it)
          coroutineScope.launch {
            bottomSheetState.hide()
          }
        },
        onShareGifFile = { fileUrl = it },
        onShareJsonFile = { fileUrl = it },
        onShareVideoFile = { fileUrl = it }
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
                    val animations = it.invoke()
                    if (animations.isEmpty()) {
                      // Todo: Empty instead of Loading View
                      LoadingAnimation(
                        waveColor = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                        arcColor = MaterialTheme.colors.primaryVariant
                      )
                    } else {
                      HorizontalPager(state = pagerState) {
                        ExploreContent(
                          files = animations,
                          onShareClick = {
                            selectedAnimation = it
                            coroutineScope.launch {
                              bottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                            }
                          }
                        )
                      }
                    }
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
private fun ExploreContent(
  modifier: Modifier = Modifier,
  onShareClick: (Lottiefile) -> Unit,
  files: List<Lottiefile>
) {
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    content = {

      item {
        Divider(
          modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .height(18.dp)
        )
      }

      items(items = files, key = { it.toString() }) { lottieFile ->
        LottieFileItemView(
          lottiefile = lottieFile,
          onClick = {},
          onShareClick = onShareClick,
        )

        Divider(
          modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .height(18.dp)
        )
      }
    }
  )
}