package com.ericampire.android.androidstudycase.data.repository

import com.ericampire.android.androidstudycase.common.CoroutineDispatcherExtension
import com.ericampire.android.androidstudycase.common.CoroutineScopeExtension
import com.ericampire.android.androidstudycase.common.MainCoroutineExtension
import com.ericampire.android.androidstudycase.data.datasource.lottiefiles.LottieFileDataSource
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.Result
import com.ericampire.android.androidstudycase.util.data
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExtendWith(
  value = [
    MainCoroutineExtension::class,
    CoroutineDispatcherExtension::class,
    CoroutineScopeExtension::class
  ]
)
class LottieFileRepositoryTest(
  private val coroutineScope: TestCoroutineScope,
  private val dispatcher: TestCoroutineDispatcher
) {

  // SUT
  private lateinit var repository: LottieFileRepositoryImpl

  // DOC's
  private val remoteDataSource = mockk<LottieFileDataSource>(relaxed = true)
  private val localDataSource = mockk<LottieFileDataSource>(relaxed = true)

  @BeforeEach
  fun setup() {
    repository = LottieFileRepositoryImpl(
      remoteDataSource = remoteDataSource,
      localDataSource = localDataSource,
      coroutineScope = coroutineScope,
      coroutineDispatcher = dispatcher
    )
  }

  @ExperimentalTime
  @Test
  fun findingFeaturedAnimation() = runBlockingTest {
    every { remoteDataSource.findFeatured() } returns flowOf(Result.Success(PreviewData.Lottiefile.data))
    coEvery { localDataSource.save(any()) } just Runs

    repository.findFeatured().collect {
      Assert.assertEquals(it.data, PreviewData.Lottiefile.data)
    }

    verify(exactly = 1) { remoteDataSource.findFeatured() }
    coVerify(exactly = PreviewData.Lottiefile.data.size) {
      localDataSource.save(any())
    }
  }

  @ExperimentalTime
  @Test
  fun findingRecentAnimation() = runBlockingTest {
    every { remoteDataSource.findRecent() } returns flowOf(Result.Success(PreviewData.Lottiefile.data))
    coEvery { localDataSource.save(any()) } just Runs

    repository.findRecent().collect {
      Assert.assertEquals(it.data, PreviewData.Lottiefile.data)
    }

    verify(exactly = 1) { remoteDataSource.findRecent() }
    coVerify(exactly = PreviewData.Lottiefile.data.size) {
      localDataSource.save(any())
    }
  }

  @ExperimentalTime
  @Test
  fun findingPopularAnimation() = runBlockingTest {
    every { remoteDataSource.findPopular() } returns flowOf(Result.Success(PreviewData.Lottiefile.data))
    coEvery { localDataSource.save(any()) } just Runs

    repository.findPopular().collect {
      Assert.assertEquals(it.data, PreviewData.Lottiefile.data)
    }

    verify(exactly = 1) { remoteDataSource.findPopular() }
    coVerify(exactly = PreviewData.Lottiefile.data.size) {
      localDataSource.save(any())
    }
  }
}