package com.ericampire.android.androidstudycase.data.datasource.animator

import com.ericampire.android.androidstudycase.data.room.AnimatorDao
import com.ericampire.android.androidstudycase.domain.entity.Animator
import javax.inject.Inject

class LocalAnimatorDataSource @Inject constructor(
  private val animatorDao: AnimatorDao
) : AnimatorDataSource {

  override suspend fun findAll(): List<Animator> {
    return animatorDao.findAll()
  }

  override suspend fun save(animator: Animator) {
    animatorDao.save(animator)
  }
}