package com.ericampire.android.androidstudycase.room

import app.cash.turbine.test
import com.ericampire.android.androidstudycase.data.room.BlogDao
import com.ericampire.android.androidstudycase.room.common.DatabaseTest
import com.ericampire.android.androidstudycase.util.PreviewData
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime


@ExperimentalTime
class BlogDoaTest : DatabaseTest() {

  private lateinit var blogDoa: BlogDao

  @BeforeEach
  fun setup() {
    blogDoa = db.blogDao
  }

  @Test
  fun saveBlog() = runBlocking {
    blogDoa.save(PreviewData.Blog.data.first())
    blogDoa.findAll().test {
      Assertions.assertEquals(awaitItem().first(), PreviewData.Blog.data.first())
    }
  }
}