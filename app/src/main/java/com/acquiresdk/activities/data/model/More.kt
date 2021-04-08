package com.acquiresdk.activities.data.model

import com.acquire.sdk.enums.CallType

data class More(
    val title: String,
    val subTitle: String,
    val icon: Int,
    val nextScreen: CallType
)