package com.ericampire.android.androidstudycase.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity
data class Animator(
  @PrimaryKey
  var name: String = "",
  var avatarUrl: String = "",
)

@Serializable
data class FeaturedAnimators(val results: List<Animator>)

@Serializable
data class AnimatorData(
  val featuredAnimators: FeaturedAnimators
)

@Serializable
data class AnimatorApiResponse(
  @SerialName("data") val animatorAnimatorData: AnimatorData
)