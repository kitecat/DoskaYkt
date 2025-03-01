package com.aital.doskaykt.models

data class Category(
    val count: Int,
    val id: Int,
    val isPriority: Boolean,
    val name: String,
    val subcategories: List<Subcategory>?
):java.io.Serializable