package com.ericampire.android.androidstudycase.util.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

@ExperimentalCoroutinesApi
class MainCoroutineExtension : BeforeAllCallback, AfterAllCallback {

  private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

  override fun beforeAll(context: ExtensionContext?) {
    Dispatchers.setMain(testDispatcher)
  }

  override fun afterAll(context: ExtensionContext?) {
    Dispatchers.resetMain()
    testDispatcher.cleanupTestCoroutines()
  }
}