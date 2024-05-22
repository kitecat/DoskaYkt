package com.aital.doskaykt.models

data class Data(
    val categories: List<Category>,
    val count: Int,
    val organizations: List<Any>,
    val posts: List<Post>,
    val token: String
)