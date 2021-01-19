package com.acquiresdk.activities.data.model

data class Cart(
    val name: String,
    val image: Int,
    val price: Float,
    val qty: Int,
    val isWishList: Boolean
)