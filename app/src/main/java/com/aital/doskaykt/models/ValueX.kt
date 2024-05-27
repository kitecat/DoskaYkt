package com.aital.doskaykt.models

data class ValueX(
    val activeCount: Int,
    val id: Int,
    val isPriority: Boolean,
    val name: String,
    val subOptions: List<Any>
):java.io.Serializable