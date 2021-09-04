package com.ericampire.android.androidstudycase.domain.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LottieFilesApiResponse(
  @SerialName("data")
  val lottieFilesLottieFilesData: LottieFilesData
)

@Serializable
data class LottieFilesData(
  @SerialName("recent")
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
data class Lottiefile(
  @PrimaryKey var id: Long? = null,
  var bgColor: String = "",
  var lottieUrl: String = "",
  var gifUrl: String? = "",
  var videoUrl: String? = "",
  var imageUrl: String? = "",
  var name: String = "",
//  @TypeConverters(DateConverter::class)
//  @Serializable(with = DateSerializer::class)
  var createdAt: String = "",
  @Ignore var createdBy: Animator? = null
)


