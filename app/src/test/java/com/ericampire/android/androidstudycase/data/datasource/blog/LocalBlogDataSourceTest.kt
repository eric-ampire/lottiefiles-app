package com.ericampire.android.androidstudycase.data.datasource.blog

import com.ericampire.android.androidstudycase.common.MainCoroutineExtension
import com.ericampire.android.androidstudycase.data.room.BlogDao
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.data
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MainCoroutineExtension::class)
class LocalBlogDataSourceTest {

  // SUT
  private lateinit var dataSource: BlogDataSource

  // DOC's
  private val blogDao = mockk<BlogDao>(relaxed = true)

  @BeforeEach
  fun setup() {
    dataSource = LocalBlogDataSource(blogDao)
  }

  @Test
  fun findAllStories() = runBlockingTest {
    every { blogDao.findAll() } returns flowOf(PreviewData.Blog.data)

    dataSource.findAll().collect {
      Assertions.assertEquals(it.data, PreviewData.Blog.data)
    }

    verify(exactly = 1) {
      blogDao.findAll()
    }
  }

  @Test
  fun saveBlog() = runBlockingTest {
    coEvery { blogDao.save(PreviewData.Blog.data.first()) } just Runs
    dataSource.save(PreviewData.Blog.data.first())
    coVerify {
      blogDao.save(eq(PreviewData.Blog.data.first()))
    }
  }
}