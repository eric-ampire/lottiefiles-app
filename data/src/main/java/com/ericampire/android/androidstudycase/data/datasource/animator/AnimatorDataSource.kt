package com.ericampire.android.androidstudycase.data.datasource.animator

import com.ericampire.android.androidstudycase.domain.entity.Animator


interface AnimatorDataSource {
  suspend fun findAll(): List<Animator>
  suspend fun save(animator: Animator)
}