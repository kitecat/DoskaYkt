package com.aital.doskaykt.models

data class Rubric(
    val count: Int,
    val id: Int,
    val isPriority: Boolean,
    val isShowMap: Boolean,
    val name: String
):java.io.Serializable