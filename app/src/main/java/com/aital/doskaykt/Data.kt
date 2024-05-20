package com.aital.doskaykt

data class Data(
    val categories: List<Category>,
    val count: Int,
    val organizations: List<Any>,
    val posts: List<Post>,
    val token: String
)