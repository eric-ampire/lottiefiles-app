package com.ericampire.android.androidstudycase.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class BlogPage(
  val currentPage: Int,
  val from: Int,
  val perPage: Int,
  val results: List<Blog>,
  val to: Int,
  val total: Int,
  val totalPages: Int
)

@Serializable
data class BlogData(
  val blogs: BlogPage
)

@Serializable
data class BlogApiResponse(
  val data: BlogData
)

@Serializable
@Entity
data class Blog(
  @PrimaryKey val id: Long? = null,
  val postedAt: String = "",
  val imageUrl: String = "",
  val title: String
)