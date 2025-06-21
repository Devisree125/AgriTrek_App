package com.example.agritrek

import java.io.Serializable

data class Disease(
    val name: String,
    val scientificName: String,
    val imageResId: Int,
    val description: String,
    val solution: String
) : Serializable
