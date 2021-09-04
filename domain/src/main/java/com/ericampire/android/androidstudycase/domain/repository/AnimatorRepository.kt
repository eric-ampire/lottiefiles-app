package com.ericampire.android.androidstudycase.domain.repository

import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.util.Result
import kotlinx.coroutines.flow.Flow


interface AnimatorRepository {
  fun findAll(): Flow<Result<List<Animator>>>
}