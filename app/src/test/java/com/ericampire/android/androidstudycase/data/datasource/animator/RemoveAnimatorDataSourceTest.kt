package com.ericampire.android.androidstudycase.data.datasource.animator

import com.ericampire.android.androidstudycase.domain.entity.AnimatorApiResponse
import com.ericampire.android.androidstudycase.util.ApiUrl
import com.ericampire.android.androidstudycase.util.PreviewData
import com.ericampire.android.androidstudycase.util.test.MainCoroutineExtension
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MainCoroutineExtension::class)
class RemoveAnimatorDataSourceTest(

) {

  // SUT
  private lateinit var dataSource: RemoteAnimatorDataSource

  // DOC's
  private lateinit var client: HttpClient

  @BeforeEach
  fun setup() {
    val engine = MockEngine.config {
      addHandler { respondOk("first") }
    }
    client = HttpClient(engine)
    dataSource = RemoteAnimatorDataSource(client)
  }

  @Test
  fun saveAnimator() = runBlockingTest {
    assertThrows<NotImplementedError> {
      dataSource.save(PreviewData.Animator.data.first())
    }
  }

  @Test
  @Disabled
  fun findAnimator() = runBlockingTest {
    runBlocking {
      val data = client.get<AnimatorApiResponse>(ApiUrl.Animator.featured)
    }


    //assertEquals(data, "first")
  }
}