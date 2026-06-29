package com.sridhar.screenshottesting.pdf

data class Pdf(
    val id: Int,
    val title: String,
    val pageCount: Int,
    val fileSizeKb: Long,
    val lastOpenedAt: String,
)
