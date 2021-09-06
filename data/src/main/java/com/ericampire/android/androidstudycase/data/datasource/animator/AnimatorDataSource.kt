package com.ericampire.android.androidstudycase.data.datasource.animator

import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow


interface AnimatorDataSource {
  fun findAll(): Flow<Result<List<Animator>>>
  suspend fun save(animator: Animator)
}