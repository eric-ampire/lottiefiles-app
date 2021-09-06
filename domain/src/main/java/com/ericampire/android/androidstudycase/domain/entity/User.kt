package com.ericampire.android.androidstudycase.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
  @PrimaryKey
  val id: Long? = null,
  val displayName: String,
  val imageUrl: String,
)
