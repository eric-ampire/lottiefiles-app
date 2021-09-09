package com.ericampire.android.androidstudycase.util

import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile
import com.ericampire.android.androidstudycase.domain.entity.User

object PreviewData {
  object User {
    val data = listOf(
      User(1, "Eric Ampire", "https://avatars.githubusercontent.com/u/32017617?v=4")
    )
  }

  object Blog {
    val data = listOf(
      Blog(
        id = 1,
        postedAt = "2021-07-08T00:00:00.000Z",
        imageUrl = "https://d3jl769oy69y7b.cloudfront.net/2021/07/Blog-Visual---The-Key-to-An-Immersive-UX_-Animation.png",
        title = "The Key to An Immersive UX: Animation"
      ),
      Blog(
        id = 2,
        title = "LottieFiles Animations are accessible across 25,000+ everyday tools with the embed feature",
        postedAt = "2021-06-18T00:00:00.000Z",
        imageUrl = "https://d3jl769oy69y7b.cloudfront.net/2021/06/Embed-Blog-OG.png"
      ),
      Blog(
        id = 3,
        postedAt = "2021-07-08T00:00:00.000Z",
        imageUrl = "https://d3jl769oy69y7b.cloudfront.net/2021/07/Blog-Visual---The-Key-to-An-Immersive-UX_-Animation.png",
        title = "The Key to An Immersive UX: Animation"
      ),
      Blog(
        id = 4,
        title = "LottieFiles Animations are accessible across 25,000+ everyday tools with the embed feature",
        postedAt = "2021-06-18T00:00:00.000Z",
        imageUrl = "https://d3jl769oy69y7b.cloudfront.net/2021/06/Embed-Blog-OG.png"
      ),
    )
  }
  object Lottiefile {
    val data = listOf(
      Lottiefile(
        id = 1370,
        bgColor = "#ffffff",
        lottieUrl = "https://assets4.lottiefiles.com/datafiles/U1I3rWEyksM9cCH/data.json",
        gifUrl = "https://assets3.lottiefiles.com/render/julivspr.gif",
        videoUrl = "https://assets3.lottiefiles.com/render/julivspr.mp4",
        imageUrl = "https://assets8.lottiefiles.com/render/julivspr.png",
        createdAt = "2018-02-02T15:53:12.000Z",
        name = "confetti",
        createdBy = PreviewData.Animator.data.first()
      ),
      Lottiefile(
        id = 427,
        bgColor = "#ffffff",
        lottieUrl = "https://assets8.lottiefiles.com/datafiles/zc3XRzudyWE36ZBJr7PIkkqq0PFIrIBgp4ojqShI/newAnimation.json",
        gifUrl = "https://assets10.lottiefiles.com/render/juml9ngj.gif",
        videoUrl = "https://assets10.lottiefiles.com/render/juml9ngj.mp4",
        imageUrl = "https://assets4.lottiefiles.com/render/juml9ngj.png",
        createdAt = "2017-07-28T14:25:49.000Z",
        name = "Happy Birthday!",
        createdBy = PreviewData.Animator.data[1]
      ),
      Lottiefile(
        id = 782,
        bgColor = "#ffffff",
        lottieUrl = "https://assets5.lottiefiles.com/datafiles/8UjWgBkqvEF5jNoFcXV4sdJ6PXpS6DwF7cK4tzpi/Check Mark Success/Check Mark Success Data.json",
        gifUrl = "https://assets2.lottiefiles.com/render/jum1r6it.gif",
        videoUrl = "https://assets2.lottiefiles.com/render/jum1r6it.mp4",
        imageUrl = "https://assets1.lottiefiles.com/render/jum1r6it.png",
        createdAt = "2017-10-04T18:16:21.000Z",
        name = "Check Mark - Success",
        createdBy = PreviewData.Animator.data[2]
      ),
    )
  }
  object Animator {
    val data = listOf(
      Animator(
        name = "Kevin Correa",
        avatarUrl = "https://assets4.lottiefiles.com/avatars/300_60abcb0579641.jpg"
      ),
      Animator(
        name = "Alexander Plaksin",
        avatarUrl = "https://assets5.lottiefiles.com/avatars/300_487756-994436705.jpg"
      ),
      Animator(
        name = "Vanessa Urrunaga",
        avatarUrl = "https://assets2.lottiefiles.com/avatars/300_60df0a5dbd4f5.jpg"
      ),
      Animator(
        name = "Kevin Correa",
        avatarUrl = "https://assets4.lottiefiles.com/avatars/300_60abcb0579641.jpg"
      ),
      Animator(
        name = "Alexander Plaksin",
        avatarUrl = "https://assets5.lottiefiles.com/avatars/300_487756-994436705.jpg"
      ),
      Animator(
        name = "Vanessa Urrunaga",
        avatarUrl = "https://assets2.lottiefiles.com/avatars/300_60df0a5dbd4f5.jpg"
      ),
    )
  }
}