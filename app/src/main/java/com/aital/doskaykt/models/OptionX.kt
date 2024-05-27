package com.aital.doskaykt.models

data class OptionX(
    val id: Int,
    val multiselect: Boolean,
    val name: String,
    val required: Boolean,
    val type: String,
    val unit: String,
    val values: List<Value>
):java.io.Serializable