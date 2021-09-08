/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ericampire.android.androidstudycase.testshared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

@ExperimentalCoroutinesApi
class MainCoroutineExtension(
  val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : BeforeAllCallback, AfterAllCallback {

  override fun beforeAll(context: ExtensionContext?) {
    Dispatchers.setMain(testDispatcher)
  }

  override fun afterAll(context: ExtensionContext?) {
    Dispatchers.resetMain()
    testDispatcher.cleanupTestCoroutines()
  }
}

//fun MainCoroutineRule.runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
//  this.testDispatcher.runBlockingTest {
//    block()
//  }
//
///**
// * Creates a new [CoroutineScope] with the rule's testDispatcher
// */
//fun MainCoroutineRule.CoroutineScope(): CoroutineScope = CoroutineScope(testDispatcher)
