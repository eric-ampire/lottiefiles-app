package com.ericampire.android.androidstudycase.util.test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

@ExperimentalCoroutinesApi
class CoroutineScopeExtension : ParameterResolver {
  override fun supportsParameter(
    parameterContext: ParameterContext,
    extensionContext: ExtensionContext?
  ): Boolean {
    return parameterContext.parameter.type.equals(TestCoroutineScope::class.java)
  }

  override fun resolveParameter(
    parameterContext: ParameterContext?,
    extensionContext: ExtensionContext?
  ): Any {
    return TestCoroutineScope()
  }
}