package com.ericampire.android.androidstudycase.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class BlogPage(
    val currentPage: Int,
    val from: Int,
    val perPage: Int,
    val blogs: List<Blog>,
    val to: Int,
    val total: Int,
    val totalPages: Int
)

@Serializable
data class BlogData(
    @SerialName("blogs") val blogPage: BlogPage
)

@Serializable
data class BlogApiResponse(
    @SerialName("data") val blogBlogData: BlogData
)

@Serializable
@Entity
data class Blog(
    @Contextual val postedAt: Date,
    @PrimaryKey val imageUrl: String,
    val title: String
)