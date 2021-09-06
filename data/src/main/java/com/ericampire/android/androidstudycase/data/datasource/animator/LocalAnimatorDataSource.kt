package com.ericampire.android.androidstudycase.data.datasource.animator

import com.ericampire.android.androidstudycase.data.room.AnimatorDao
import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalAnimatorDataSource @Inject constructor(
  private val animatorDao: AnimatorDao
) : AnimatorDataSource {

  override fun findAll(): Flow<Result<List<Animator>>> {
    return animatorDao.findAll().map {
      Result.Success(it)
    }
  }

  override suspend fun save(animator: Animator) {
    animatorDao.save(animator)
  }
}