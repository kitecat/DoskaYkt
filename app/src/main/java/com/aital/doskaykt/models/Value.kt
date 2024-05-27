package com.aital.doskaykt.models

data class Value(
    val activeCount: Int,
    val id: Int,
    val isPriority: Boolean,
    val name: String,
    val subOptions: List<SubOption>
):java.io.Serializable