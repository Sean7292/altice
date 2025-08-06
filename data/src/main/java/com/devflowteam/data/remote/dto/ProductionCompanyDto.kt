package com.devflowteam.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompanyDto(
    val id: Long,
    @SerialName("logo_path")
    val logoPath: String,
    val name: String,
    @SerialName("origin_country")
    val originCountry: String,
)
