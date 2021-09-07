package com.ericampire.android.androidstudycase.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity
data class Animator(
  @ColumnInfo(name = "id_animator")
  @PrimaryKey
  val id: Long? = null,
  val name: String = "",
  var avatarUrl: String = "",
)

@Serializable
data class FeaturedAnimators(val results: List<Animator>)

@Serializable
data class AnimatorData(
  @SerialName("featuredAnimators")
  val animators: FeaturedAnimators
)

@Serializable
data class AnimatorApiResponse(
  @SerialName("data") val data: AnimatorData
)