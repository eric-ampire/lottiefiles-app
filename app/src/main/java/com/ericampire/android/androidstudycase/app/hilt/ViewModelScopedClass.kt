package com.ericampire.android.androidstudycase.app.hilt

import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@MavericksViewModelScoped
class ViewModelScopedClass @Inject constructor() {
  val id = instanceId.incrementAndGet()

  companion object {
    private val instanceId = AtomicInteger(0)
  }
}