package com.aital.doskaykt.models

data class Subcategory(
    val count: Int,
    val id: Int,
    val isPriority: Boolean,
    val isShowMap: Boolean,
    val name: String,
    val options: List<OptionX>,
    val rubrics: List<Rubric>
):java.io.Serializable