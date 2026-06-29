package com.sridhar.screenshottesting.profile

data class Profile(
    val name: String,
    val email: String,
    val avatarUrl: String?,
    val bio: String,
    val memberSince: String,
    val completedCourses: Int,
    val inProgressCourses: Int,
)
