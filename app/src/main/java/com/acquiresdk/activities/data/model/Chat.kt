package com.acquiresdk.activities.data.model

const val TYPE_SELF = 0
const val TYPE_SENDER = 1 // SELF
const val TYPE_TIME = 2
const val TYPE_IMAGE = 3
const val TYPE_FILE = 4

data class Chat(
    val title: String,
    val subTitle: String,
    val url: String
)