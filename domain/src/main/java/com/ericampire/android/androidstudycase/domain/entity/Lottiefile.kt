package com.ericampire.android.androidstudycase.domain.entity

import androidx.room.*
import com.ericampire.android.androidstudycase.domain.util.DateSerializer
import com.ericampire.android.androidstudycase.util.room.DateConverter
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import java.util.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

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
class Lottiefile(
  @PrimaryKey var id: Long? = null,
  var bgColor: String = "",
  var lottieUrl: String = "",
  var gifUrl: String = "",
  var videoUrl: String = "",
  var imageUrl: String = "",
  var name: String = "",
//  @TypeConverters(DateConverter::class)
//  @Serializable(with = DateSerializer::class)
  var createdAt: String = "",
  @Ignore var createdBy: Animator? = null
)


