package com.ericampire.android.androidstudycase.domain.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.joda.time.DateTime
import java.util.*

object DateSerializer : KSerializer<Date> {
  override val descriptor: SerialDescriptor
    get() = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: Date) {
    encoder.encodeString(DateTime(value).toString())
  }

  override fun deserialize(decoder: Decoder): Date {
    return DateTime(decoder.decodeString()).toDate()
  }
}