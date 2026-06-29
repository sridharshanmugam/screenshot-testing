package com.sridhar.screenshottesting.image

data class Image(
    val id: Int,
    val title: String,
    val url: String?,
    val widthPx: Int,
    val heightPx: Int,
    val tags: List<String>,
)
