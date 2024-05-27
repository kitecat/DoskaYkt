package com.aital.doskaykt.models

data class SubOption(
    val id: Int,
    val multiselect: Boolean,
    val name: String,
    val required: Boolean,
    val type: String,
    val values: List<ValueX>
):java.io.Serializable