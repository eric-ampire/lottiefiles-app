package com.ericampire.android.androidstudycase.util.test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

@ExperimentalCoroutinesApi
class CoroutineDispatcherExtension : ParameterResolver {

  override fun supportsParameter(
    parameterContext: ParameterContext,
    extensionContext: ExtensionContext?
  ): Boolean {
    return parameterContext.parameter.type.equals(TestCoroutineDispatcher::class.java)
  }

  override fun resolveParameter(
    parameterContext: ParameterContext?,
    extensionContext: ExtensionContext?
  ): Any {
    return TestCoroutineDispatcher()
  }
}