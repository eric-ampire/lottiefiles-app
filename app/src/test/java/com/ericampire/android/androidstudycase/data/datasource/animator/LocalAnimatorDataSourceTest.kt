package com.ericampire.android.androidstudycase.data.datasource.animator

import com.ericampire.android.androidstudycase.data.room.AnimatorDao
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.data
import com.ericampire.android.androidstudycase.util.test.MainCoroutineExtension
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MainCoroutineExtension::class)
class LocalAnimatorDataSourceTest {

  // SUT
  private lateinit var dataSource: LocalAnimatorDataSource

  private val animatorDao = mockk<AnimatorDao>(relaxed = true)

  @BeforeEach
  fun setup() {
    dataSource = LocalAnimatorDataSource(animatorDao)
  }

  @Test
  fun findAllAnimators() = runBlockingTest {
    every { animatorDao.findAll() } returns flowOf(PreviewData.Animator.data)

    dataSource.findAll().collect {
      assertEquals(it.data, PreviewData.Animator.data)
    }

    verify(exactly = 1) {
      animatorDao.findAll()
    }
  }

  @Test
  fun saveAnimator() = runBlockingTest {
    coEvery { animatorDao.save(PreviewData.Animator.data.first()) } just Runs
    dataSource.save(PreviewData.Animator.data.first())
    coVerify {
      animatorDao.save(eq(PreviewData.Animator.data.first()))
    }
  }
}