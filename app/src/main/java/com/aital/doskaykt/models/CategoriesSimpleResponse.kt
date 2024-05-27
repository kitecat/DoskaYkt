package com.aital.doskaykt.models

data class CategoriesSimpleResponse(
    val code: Int,
    val `data`: List<Category>,
    val msg: String,
    val result: String
)