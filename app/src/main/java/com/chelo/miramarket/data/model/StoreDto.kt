package com.chelo.miramarket.data.model


data class StoreDto(
    val id: String = "",
    val category: String = "",
    val state: String = "",
    val description: String = "",
    val ubication: String = "",
    val owner: String = "",
    val schedule: List<String> = emptyList(),
)

