package com.ericampire.android.androidstudycase.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ericampire.android.androidstudycase.domain.entity.Animator
import com.ericampire.android.androidstudycase.domain.entity.Blog
import com.ericampire.android.androidstudycase.domain.entity.Lottiefile


class BlogProvider : PreviewParameterProvider<Blog> {
  override val values: Sequence<Blog>
    get() = PreviewData.Blog.data.asSequence()
}


class AnimatorProvider : PreviewParameterProvider<Animator> {
  override val values: Sequence<Animator>
    get() = PreviewData.Animator.data.asSequence()
}


class LottieFileProvider : PreviewParameterProvider<Lottiefile> {
  override val values: Sequence<Lottiefile>
    get() = PreviewData.Lottiefile.data.asSequence()
}