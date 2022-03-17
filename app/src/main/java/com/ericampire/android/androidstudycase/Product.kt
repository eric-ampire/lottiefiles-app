package com.ericampire.android.androidstudycase

import androidx.compose.ui.text.font.FontWeight
import java.util.*

/**
 * - Pending : Commande faite mais en attente de validation
 * - Cancelled : Order cancelled by the user
 * - Declined : Order cancelled by the store
 * - Validated : Order validated by the store
 * - Delivered : Order delivered
 * - In Shipping : Order in shipping
 */

data class Order(
    val uid: String = "",
    val products: List<ProductCart>,
    val fees: Double,
    val refund: Any? = null
)

/**
 * Une copy de [Product] plus quantity et shipping in express or not
 */
data class ProductCart(
    val product: Product, // Le product modifier avec un one meta data value
    val quantity: Long,
    val expressShipping: Boolean = false,
    val refund: Any? = null
)


data class ProductTag(
    val uid: String, // Unique
    val title: String,
    val createdAt: Date,
    val updatedAt: Date,
    val description: String,
)


data class Product(
    val uid: String = "",
    val createdAt: Date? = null,
    val price: Double = 0.0,
    val name: String = "",

    // A ajouter
    val tags: List<String>, // list of uid tag
    val uidStore: String,
    val uidGroup: String,
    val shippingDetail: ProductShippingPrice,
    val metaDatas: List<MetaData> = emptyList(),
    val productSpecs: Map<String, String> = mapOf(),
    val productDimension: ProductDimension
)

class ProductShippingPrice(
    val freeShipping: Boolean = false,
    val defaultShippingPrice: Double = 0.0,
    val expressShippingPrice: Double = 0.0,
    val expressShippingDetail: String = "",
)

data class ProductDimension(
    val weight: Double = 0.0,
    val length: Double = 0.0,
    val width: Double = 0.0,
    val height: Double = 0.0,
    val fragileBox: Boolean = false,
)

data class MetaData(
    val uid: String = "",
    val name: String = "",
    val description: String = "",
    val type: MetaDataValueType = MetaDataValueType.TEXT,
    val metaDataValues: List<MetaDataValue> = emptyList(),
)

enum class MetaDataValueType {
    TEXT, COLOR, IMAGE_URL
}

data class MetaDataValue(
    val uid: String = "",
    val value: String = "",
    val extraPrice: Double = 0.0,
    val available: Boolean = false,
)

fun main() {
    val capacityMetaData = MetaData(
        uid = "sdfdf",
        name = "Capacity",
        description = "The storage of the phone",
        type = MetaDataValueType.TEXT,
        metaDataValues = listOf(
            MetaDataValue(
                uid = "12142",
                value = "16GB",
                extraPrice = 0.0,
                available = false,
            ),
            MetaDataValue(
                uid = "1214sdf2",
                value = "32GB",
                extraPrice = 23.5,
                available = true,
            ),
        )
    )
    val colorMetaData = MetaData(
        uid = "sdfdf",
        name = "Color",
        description = "The color of the phone",
        type = MetaDataValueType.COLOR,
        metaDataValues = listOf(
            MetaDataValue(
                uid = "12142",
                value = "#FF2335",
                extraPrice = 0.0,
                available = false
            ),
            MetaDataValue(
                uid = "1214sdf2",
                value = "#FF2335",
                extraPrice = 0.0,
                available = true
            ),
            MetaDataValue(
                uid = "1214sdf2",
                value = "#FF2335",
                extraPrice = 0.0,
                available = true
            ),
        )
    )

    val product = Product(
        uid = "dsfdf",
        name = "Iphone 13",
        price = 120.0,
        productSpecs = mapOf(
            "Model" to "12K23",
            "Mark" to "Apple",
            "Year" to "2021",
            "Material" to "Plastic",
            "Recycler" to "Oui"
        ),
        metaDatas = listOf(capacityMetaData, colorMetaData),
        productDimension = ProductDimension(
            height = 12.0,
            length = 12.9,
            fragileBox = true,
            weight = 12.0,
            width = 12.0
        ),
        shippingDetail = ProductShippingDetail(
            freeShipping = false,
            defaultShippingPrice = 12.0,
            expressShippingPrice = 10.0,
            expressShippingDetail = "Ajouter 10$ pour etre livre en 2 jours",
        )
    )
}