package com.ericampire.android.androidstudycase.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import java.util.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import java.util.*

@Serializable
data class LottieFilesApiResponse(
  @SerialName("data")
  val lottieFilesLottieFilesData: LottieFilesData
)

@ExperimentalSerializationApi
@Serializable
data class LottieFilesData(
  @JsonNames("recent", "popular")
  val page: LottieFilesPage
)


@Serializable
data class LottieFilesPage(
  val currentPage: Int,
  val from: Int,
  val perPage: Int,
  val results: List<Lottiefile>,
  val to: Int,
  val total: Int,
  val totalPages: Int
)


@Serializable
@Entity
class Lottiefile(
  @PrimaryKey val id: Long,
  val bgColor: String,
  val lottieUrl: String,
  val gifUrl: String,
  val videoUrl: String,
  val imageUrl: String,
  val name: String,
  @Contextual val createdAt: Date,
  val createdBy: Animator
)


