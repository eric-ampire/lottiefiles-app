package com.ericampire.android.androidstudycase.domain.mapper

interface Mapper<I, O> {
  fun map(input: I): O
}